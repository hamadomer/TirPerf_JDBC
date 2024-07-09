package org.example.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DbUtil {

    public static void executeSqlScript(String filePath) throws SQLException, IOException {
        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    statement.execute(sql.toString());
                    sql = new StringBuilder();
                }
            }
            if (sql.length() > 0) {
                statement.execute(sql.toString());
            }
        }
    }
}
