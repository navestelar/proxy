package database;


import br.com.navestelar.database.DatabaseConfig;
import br.com.navestelar.database.PostgresDatabaseConfig;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PostgresDatabaseConfigTest {
    @Test
    public void testPostgresDatabaseConfig() {
        DatabaseConfig config = new PostgresDatabaseConfig("password", "testdb")
                .user("testuser")
                .host("localhost")
                .port("5432");

        assertEquals("jdbc:postgresql://localhost:5432/testdb", config.getUrl());
        assertEquals("testuser", config.getUser());
        assertEquals("localhost", config.getHost());
        assertEquals("5432", config.getPort());
        assertEquals("password", config.getPassword());
        assertEquals("testdb", config.getDatabaseName());

        DatabaseConfig invalidPortConfig = new PostgresDatabaseConfig("password", "testdb")
                .user("testuser")
                .host("localhost")
                .port("invalid_port");

        assertEquals("jdbc:postgresql://localhost:invalid_port/testdb", invalidPortConfig.getUrl());
        assertEquals("testuser", invalidPortConfig.getUser());
        assertEquals("localhost", invalidPortConfig.getHost());
        assertEquals("invalid_port", invalidPortConfig.getPort());
        assertEquals("password", invalidPortConfig.getPassword());
        assertEquals("testdb", invalidPortConfig.getDatabaseName());
    }
}
