package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.PanSI;

import java.sql.*;
import java.util.Optional;


public class PanSIRepository {
    private static PanSIRepository instance = null;

    public static PanSIRepository getInstance() {
        if (instance == null) {
            instance = new PanSIRepository();
        }
        return instance;
    }


    public Connection connection = null;

    private PanSIRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createPanSI(PanSI panSI) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO pansi (version) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, panSI.getVersion());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<PanSI> findPanSIById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM pansi WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    PanSI panSI = new PanSI();
                    panSI.setId(id);
                    panSI.setVersion(resultSet.getString("version"));

                    return Optional.of(panSI);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

}
