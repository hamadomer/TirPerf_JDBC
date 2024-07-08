package DbTests;

import org.example.DB.DbConnector;
import org.example.DB.DbUtil;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DbConnectorTest {



    @BeforeEach
    public void setup() throws SQLException, IOException {
        System.setProperty("JDBC_URL", "jdbc:postgresql://localhost:5432/beta");
        System.setProperty("JDBC_USERNAME", "postgres");
        System.setProperty("JDBC_PASSWORD", "postgres");
    }

    @AfterAll
    public static void tearDown() {
        System.clearProperty("JDBC_URL");
        System.clearProperty("JDBC_USERNAME");
        System.clearProperty("JDBC_PASSWORD");
    }

    @Test
    public void testGetConnection() throws SQLException, IOException {

        Connection connection = DbConnector.getConnection();
        assertNotNull(connection);
        assertTrue(connection.isValid(0));
    }
}