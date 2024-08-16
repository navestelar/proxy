package br.com.navestelar.database;

/**
 * Implementação da configuração de banco de dados para MySQL.
 */
public class MySQLDatabaseConfig implements DatabaseConfig {
    private String user = "root";
    private String host = "localhost";
    private String port = "3306";
    private String password;
    private String databaseName;

    /**
     * Construtor para a classe de configuração de MySQL.
     *
     * @param password A senha do banco de dados.
     * @param databaseName O nome do banco de dados.
     */
    public MySQLDatabaseConfig(String password, String databaseName) {
        this.password = password;
        this.databaseName = databaseName;
    }

    /**
     * Define o nome do usuário para a conexão com o banco de dados.
     *
     * @param user O nome do usuário a ser configurado.
     * @return A instância atual de MySQLDatabaseConfig, permitindo encadeamento de métodos.
     */
    @Override
    public DatabaseConfig user(String user) {
        this.user = user;
        return this;
    }

    /**
     * Define o host para a conexão com o banco de dados.
     *
     * @param host O endereço do host a ser configurado.
     * @return A instância atual de MySQLDatabaseConfig, permitindo encadeamento de métodos.
     */
    @Override
    public DatabaseConfig host(String host) {
        this.host = host;
        return this;
    }

    /**
     * Define a porta para a conexão com o banco de dados.
     *
     * @param port O número da porta a ser configurado.
     * @return A instância atual de MySQLDatabaseConfig, permitindo encadeamento de métodos.
     */
    @Override
    public DatabaseConfig port(String port) {
        this.port = port;
        return this;
    }

    /**
     * Define a senha para a conexão com o banco de dados.
     *
     * @param password A senha a ser configurada.
     * @return A instância atual de MySQLDatabaseConfig, permitindo encadeamento de métodos.
     */
    @Override
    public DatabaseConfig password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Define o nome do banco de dados para a conexão.
     *
     * @param databaseName O nome do banco de dados a ser configurado.
     * @return A instância atual de MySQLDatabaseConfig, permitindo encadeamento de métodos.
     */
    @Override
    public DatabaseConfig databaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    /**
     * Gera a URL de conexão JDBC para o banco de dados MySQL, baseada nos valores atuais
     * de host, porta e nome do banco de dados.
     *
     * @return A URL de conexão JDBC formatada.
     */
    @Override
    public String getUrl() {
        return "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDatabaseName() {
        return databaseName;
    }
}
