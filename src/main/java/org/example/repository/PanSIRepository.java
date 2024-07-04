package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.PanSI;

import java.sql.*;
import java.util.Optional;

public class PanSIRepository {
    private static final PanSIRepository instance = new PanSIRepository();
    public static PanSIRepository getInstance() { return instance; }

    private  static final String SQL_CREATE_PANSI = "INSERT INTO pansi (version) VALUES (?)";
    private  static final String SQL_GET_PANSI_BY_ID = "SELECT * FROM pansi WHERE id = ?";

    public Connection connection = null;

    private PanSIRepository () {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> createPanSI(PanSI panSI) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PANSI, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, panSI.getVersion());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<PanSI> findPanSIById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_PANSI_BY_ID)) {
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
