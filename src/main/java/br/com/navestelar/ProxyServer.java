package br.com.navestelar;

import br.com.navestelar.database.DatabaseConfig;
import br.com.navestelar.proxy.ProxyConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa um servidor proxy que aceita conexões de clientes, consulta dados em bancos de dados e usa cache para armazenar resultados.
 *
 * <p>A classe {@code ProxyServer} configura e inicia um servidor que escuta em uma porta específica para conexões de clientes.
 * Quando um cliente se conecta e envia um ID, o servidor consulta um banco de dados configurado e retorna o resultado ao cliente.
 * O servidor usa um cache para armazenar os resultados de consultas recentes e evitar consultas repetidas ao banco de dados.</p>
 */
public class ProxyServer {
    private Map<Integer, CacheEntry> cache = new HashMap<>();
    private ProxyConfig config;

    /**
     * Constrói uma nova instância de {@code ProxyServer} com a configuração fornecida.
     *
     * @param config a configuração do proxy contendo informações sobre os bancos de dados e as propriedades do proxy.
     */
    public ProxyServer(ProxyConfig config) {
        this.config = config;
    }

    /**
     * Inicia o servidor proxy e começa a aceitar conexões de clientes.
     * <p>O método fica em um loop infinito, aceitando conexões e criando uma nova thread para cada conexão.</p>
     *
     * @throws IOException se houver um erro ao abrir a porta do servidor.
     */
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(config.getProxyPort())) {
            System.out.println("ProxyServer started on " + config.getProxyIp() + ":" + config.getProxyPort());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ProxyHandler(clientSocket)).start();
            }
        }
    }

    /**
     * Manipulador de conexão com o cliente. Processa a solicitação do cliente, consulta o banco de dados e envia o resultado de volta.
     */
    private class ProxyHandler implements Runnable {
        private Socket clientSocket;

        /**
         * Constrói um novo {@code ProxyHandler} para a conexão do cliente.
         *
         * @param clientSocket o socket de conexão com o cliente.
         */
        public ProxyHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

                int id = input.readInt();
                Map<String, String> result = getData(id);
                output.writeObject(result);
                output.flush();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Obtém os dados para o ID fornecido, utilizando o cache se possível.
         *
         * @param id o ID dos dados a serem recuperados.
         * @return um mapa contendo os dados associados ao ID, ou {@code null} se nenhum dado for encontrado.
         * @throws SQLException se houver um erro ao consultar o banco de dados.
         */
        private Map<String, String> getData(int id) throws SQLException {
            long currentTime = System.currentTimeMillis();
            CacheEntry entry = cache.get(id);

            if (entry == null || (currentTime - entry.getTimestamp() > config.getTimestamp())) {
                Map<String, String> result = queryDatabase(id);

                if (result != null) {
                    cache.put(id, new CacheEntry(result, currentTime));
                }

                return result;
            }

            System.out.println("Consulta realizada no cache");
            return entry.getValues();
        }

        /**
         * Consulta o banco de dados configurado para obter os dados para o ID fornecido.
         *
         * @param id o ID dos dados a serem consultados.
         * @return um mapa contendo os dados associados ao ID, ou {@code null} se nenhum dado for encontrado.
         * @throws SQLException se houver um erro ao consultar o banco de dados.
         */
        private Map<String, String> queryDatabase(int id) throws SQLException {
            for (DatabaseConfig dbConfig : config.getDatabaseConfigs()) {
                try (
                        Connection connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(),
                                dbConfig.getPassword());
                        PreparedStatement statement = connection.prepareStatement(buildQuery())) {

                    statement.setInt(1, id);
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        String[] columns = config.getColumns();
                        Map<String, String> result = new HashMap<>();

                        for (String column : columns) {
                            result.put(column, rs.getString(column));
                        }

                        System.out.println("Consulta realizada no banco de dados " + dbConfig.getUrl());

                        return result;
                    }
                } catch (SQLException e) {
                    System.err.println("Error querying database: " + e.getMessage());
                }
            }
            return null;
        }

        /**
         * Constrói a consulta SQL para recuperar os dados com base na configuração do proxy.
         *
         * @return a consulta SQL como uma {@code String}.
         */
        private String buildQuery() {
            return "SELECT " + String.join(", ", config.getColumns()) + " FROM " + config.getTableName() + " WHERE id = ?";
        }
    }
}
