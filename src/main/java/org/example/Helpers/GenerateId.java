package org.example.Helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class GenerateId {

    public GenerateId() {}

    public Optional<Integer> getGeneratedKeys(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
        try (
                ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return Optional.of(generatedKeys.getInt(1));
            }
        }
        return Optional.empty();
    }
}
