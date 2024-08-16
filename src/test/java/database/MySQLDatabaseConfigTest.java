package database;

import br.com.navestelar.database.DatabaseConfig;
import br.com.navestelar.database.MySQLDatabaseConfig;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MySQLDatabaseConfigTest {
    @Test
    public void testInvalidMySQLDatabaseConfig() {
        DatabaseConfig config = new MySQLDatabaseConfig("password", "teste")
                .user("testuser")
                .host("localhost")
                .port("3306");

        assertEquals("jdbc:mysql://localhost:3306/teste", config.getUrl());
        assertEquals("testuser", config.getUser());
        assertEquals("localhost", config.getHost());
        assertEquals("3306", config.getPort());
        assertEquals("password", config.getPassword());
        assertEquals("teste", config.getDatabaseName());

        DatabaseConfig invalidPortConfig = new MySQLDatabaseConfig("password", "testdb")
                .user("testuser")
                .host("localhost")
                .port("invalid_port");

        assertEquals("jdbc:mysql://localhost:invalid_port/testdb", invalidPortConfig.getUrl());
        assertEquals("testuser", invalidPortConfig.getUser());
        assertEquals("localhost", invalidPortConfig.getHost());
        assertEquals("invalid_port", invalidPortConfig.getPort());
        assertEquals("password", invalidPortConfig.getPassword());
        assertEquals("testdb", invalidPortConfig.getDatabaseName());
    }
}
