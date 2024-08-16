package proxy;

import br.com.navestelar.database.DatabaseConfig;
import br.com.navestelar.database.MySQLDatabaseConfig;
import br.com.navestelar.proxy.ProxyConfig;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProxyConfigTest {
    private ProxyConfig proxyConfig;

    @Before
    public void setUp() {
        proxyConfig = ProxyConfig.builder()
                .tableName("testTable")
                .columns("id", "name", "value")
                .proxyIp("127.0.0.1")
                .addDatabaseConfig(new MySQLDatabaseConfig("password", "dbName")
                        .user("user")
                        .host("localhost")
                        .port("3306"))
                .build();
    }

    @Test
    public void testInitialConfig() {
        assertEquals("testTable", proxyConfig.getTableName());
        assertArrayEquals(new String[]{"id", "name", "value"}, proxyConfig.getColumns());
        assertEquals("127.0.0.1", proxyConfig.getProxyIp());

        assertEquals(8080, proxyConfig.getProxyPort());
        assertEquals(6000, proxyConfig.getTimestamp());
    }

    @Test
    public void testAddDatabaseConfig() {
        DatabaseConfig dbConfig = new MySQLDatabaseConfig("password", "dbName")
                .user("user")
                .host("localhost")
                .port("3306");

        proxyConfig = ProxyConfig.builder()
                .tableName("testTable")
                .columns("id", "name", "value")
                .proxyIp("127.0.0.1")
                .addDatabaseConfig(dbConfig)
                .build();

        List<DatabaseConfig> dbConfigs = proxyConfig.getDatabaseConfigs();
        assertEquals(1, dbConfigs.size());
        assertEquals(dbConfig, dbConfigs.get(0));
    }

    @Test
    public void testFluentSetters() {
        proxyConfig = ProxyConfig.builder()
                .tableName("testTable")
                .columns("id", "name", "value")
                .proxyIp("192.168.0.1")
                .proxyPort(9090)
                .timestamp(12000)
                .addDatabaseConfig(new MySQLDatabaseConfig("password", "dbName")
                        .user("user")
                        .host("localhost")
                        .port("3306"))
                .build();

        assertEquals("192.168.0.1", proxyConfig.getProxyIp());
        assertEquals(9090, proxyConfig.getProxyPort());
        assertEquals(12000, proxyConfig.getTimestamp());
    }
}
