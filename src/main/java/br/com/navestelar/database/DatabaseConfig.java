package br.com.navestelar.database;

/**
 * Interface para configurar a conexão com um banco de dados.
 */
public interface DatabaseConfig {

    /**
     * Define o usuário do banco de dados.
     *
     * @param user O nome do usuário.
     * @return A configuração do banco de dados atualizada.
     */
    DatabaseConfig user(String user);

    /**
     * Define o host do banco de dados.
     *
     * @param host O endereço do host.
     * @return A configuração do banco de dados atualizada.
     */
    DatabaseConfig host(String host);

    /**
     * Define a porta de conexão com o banco de dados.
     *
     * @param port O número da porta.
     * @return A configuração do banco de dados atualizada.
     */
    DatabaseConfig port(String port);

    /**
     * Define a senha do banco de dados.
     *
     * @param password A senha.
     * @return A configuração do banco de dados atualizada.
     */
    DatabaseConfig password(String password);

    /**
     * Define o nome do banco de dados.
     *
     * @param databaseName O nome do banco de dados.
     * @return A configuração do banco de dados atualizada.
     */
    DatabaseConfig databaseName(String databaseName);

    /**
     * Retorna a URL de conexão com o banco de dados.
     *
     * @return A URL de conexão.
     */
    String getUrl();

    /**
     * Retorna o nome do usuário do banco de dados.
     *
     * @return O nome do usuário.
     */
    String getUser();

    /**
     * Retorna o endereço do host do banco de dados.
     *
     * @return O endereço do host.
     */
    String getHost();

    /**
     * Retorna o número da porta de conexão com o banco de dados.
     *
     * @return O número da porta.
     */
    String getPort();

    /**
     * Retorna a senha do banco de dados.
     *
     * @return A senha.
     */
    String getPassword();

    /**
     * Retorna o nome do banco de dados.
     *
     * @return O nome do banco de dados.
     */
    String getDatabaseName();
}
