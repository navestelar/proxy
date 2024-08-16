package br.com.navestelar.proxy;

import br.com.navestelar.database.DatabaseConfig;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuração do Proxy que inclui informações sobre a tabela, colunas, IP do proxy,
 * porta do proxy, timestamp e configurações do banco de dados.
 *
 * <p>Para criar uma instância de ProxyConfig, use o padrão Builder. A ordem dos métodos
 * deve seguir o fluxo abaixo:</p>
 * <ol>
 *   <li>{@link #tableName(String tableName)}: Define o nome da tabela.</li>
 *   <li>{@link #columns(String... columns)}: Define as colunas.</li>
 *   <li>{@link #proxyIp(String proxyIp)}: Define o IP do proxy.</li>
 *   <li>{@link #proxyPort(int proxyPort)}: Define a porta do proxy (opcional, padrão é 8080).</li>
 *   <li>{@link #timestamp(int timestamp)}: Define o timestamp (opcional, padrão é 6000).</li>
 *   <li>{@link #addDatabaseConfig(DatabaseConfig databaseConfig)}: Adiciona uma configuração de banco de dados (opcional).</li>
 *   <li>{@link #build()}: Conclui a construção e retorna a instância de ProxyConfig.</li>
 * </ol>
 */
public class ProxyConfig {

    private final String tableName;
    private final String[] columns;
    private final String proxyIp;
    private final int proxyPort;
    private final int timestamp;
    private final List<DatabaseConfig> databaseConfigs;

    /**
     * Construtor privado da classe ProxyConfig, usado pelo Builder.
     *
     * @param builder O construtor Builder usado para construir a instância.
     */
    private ProxyConfig(Builder builder) {
        this.tableName = builder.tableName;
        this.columns = builder.columns;
        this.proxyIp = builder.proxyIp;
        this.proxyPort = builder.proxyPort;
        this.timestamp = builder.timestamp;
        this.databaseConfigs = builder.databaseConfigs;
    }

    /**
     * Retorna um novo construtor Builder para a configuração do Proxy.
     *
     * @return Um novo construtor Builder.
     */
    public static TableNameStep builder() {
        return new Builder();
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getColumns() {
        return columns;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public List<DatabaseConfig> getDatabaseConfigs() {
        return databaseConfigs;
    }

    /**
     * Interface para definir o nome da tabela no Builder.
     */
    public interface TableNameStep {
        ColumnsStep tableName(String tableName);
    }

    /**
     * Interface para definir as colunas no Builder.
     */
    public interface ColumnsStep {
        ProxyIpStep columns(String... columns);
    }

    /**
     * Interface para definir o IP do proxy no Builder.
     */
    public interface ProxyIpStep {
        BuildStep proxyIp(String proxyIp);
    }

    /**
     * Interface para definir as configurações finais no Builder.
     */
    public interface BuildStep {
        BuildStep proxyPort(int proxyPort);
        BuildStep timestamp(int timestamp);
        BuildStep addDatabaseConfig(DatabaseConfig databaseConfig);
        ProxyConfig build();
    }

    /**
     * Implementação do Builder para a configuração do Proxy.
     */
    public static class Builder implements TableNameStep, ColumnsStep, ProxyIpStep, BuildStep {

        private String tableName;
        private String[] columns;
        private String proxyIp;
        private int proxyPort = 8080;
        private int timestamp = 6000;
        private List<DatabaseConfig> databaseConfigs = new ArrayList<>();

        /**
         * Define o nome da tabela para a configuração do Proxy.
         *
         * @param tableName O nome da tabela.
         * @return A instância atual do construtor, para encadear chamadas.
         */
        @Override
        public ColumnsStep tableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        /**
         * Define as colunas para a configuração do Proxy.
         *
         * @param columns Um ou mais nomes de colunas.
         * @return A instância atual do construtor, para encadear chamadas.
         */
        @Override
        public ProxyIpStep columns(String... columns) {
            this.columns = columns;
            return this;
        }

        /**
         * Define o IP do proxy para a configuração do Proxy.
         *
         * @param proxyIp O IP do proxy.
         * @return A instância atual do construtor, para encadear chamadas.
         */
        @Override
        public BuildStep proxyIp(String proxyIp) {
            this.proxyIp = proxyIp;
            return this;
        }

        /**
         * Define a porta do proxy para a configuração do Proxy.
         *
         * @param proxyPort A porta do proxy. O valor padrão é 8080.
         * @return A instância atual do construtor, para encadear chamadas.
         */
        @Override
        public BuildStep proxyPort(int proxyPort) {
            this.proxyPort = proxyPort;
            return this;
        }

        /**
         * Define o timestamp para a configuração do Proxy.
         *
         * @param timestamp O timestamp. O valor padrão é 6000.
         * @return A instância atual do construtor, para encadear chamadas.
         */
        @Override
        public BuildStep timestamp(int timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        /**
         * Adiciona uma configuração de banco de dados à configuração do Proxy.
         *
         * @param databaseConfig A configuração do banco de dados a ser adicionada.
         * @return A instância atual do construtor, para encadear chamadas.
         */
        @Override
        public BuildStep addDatabaseConfig(DatabaseConfig databaseConfig) {
            this.databaseConfigs.add(databaseConfig);
            return this;
        }

        /**
         * Conclui a construção da configuração do Proxy e retorna uma instância de ProxyConfig.
         *
         * @return Uma nova instância de ProxyConfig com as configurações fornecidas.
         */
        @Override
        public ProxyConfig build() {
            return new ProxyConfig(this);
        }
    }
}
