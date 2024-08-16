package br.com.navestelar.database;

/**
 * Implementação da configuração de banco de dados para PostgreSQL.
 */
public class PostgresDatabaseConfig implements DatabaseConfig {
    private String user = "postgres";
    private String host = "localhost";
    private String port = "5432";
    private String password;
    private String databaseName;

    /**
     * Construtor para a classe de configuração de PostgreSQL.
     *
     * @param password A senha do banco de dados.
     * @param databaseName O nome do banco de dados.
     */
    public PostgresDatabaseConfig(String password, String databaseName) {
        this.password = password;
        this.databaseName = databaseName;
    }

    /**
     * Define o nome do usuário para a conexão com o banco de dados.
     *
     * @param user O nome do usuário a ser configurado.
     * @return A instância atual de PostgresDatabaseConfig, permitindo encadeamento de métodos.
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
     * @return A instância atual de PostgresDatabaseConfig, permitindo encadeamento de métodos.
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
     * @return A instância atual de PostgresDatabaseConfig, permitindo encadeamento de métodos.
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
     * @return A instância atual de PostgresDatabaseConfig, permitindo encadeamento de métodos.
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
     * @return A instância atual de PostgresDatabaseConfig, permitindo encadeamento de métodos.
     */
    @Override
    public DatabaseConfig databaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    /**
     * Gera a URL de conexão JDBC para o banco de dados PostgreSQL, baseada nos valores atuais
     * de host, porta e nome do banco de dados.
     *
     * @return A URL de conexão JDBC formatada.
     */
    @Override
    public String getUrl() {
        return "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
    }

    /**
     * Retorna o nome do usuário do banco de dados.
     *
     * @return O nome do usuário.
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * Retorna o endereço do host do banco de dados.
     *
     * @return O endereço do host.
     */
    @Override
    public String getHost() {
        return host;
    }

    /**
     * Retorna o número da porta de conexão com o banco de dados.
     *
     * @return O número da porta.
     */
    @Override
    public String getPort() {
        return port;
    }

    /**
     * Retorna a senha do banco de dados.
     *
     * @return A senha.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Retorna o nome do banco de dados.
     *
     * @return O nome do banco de dados.
     */
    @Override
    public String getDatabaseName() {
        return databaseName;
    }
}
