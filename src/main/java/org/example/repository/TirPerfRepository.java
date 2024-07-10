package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.TirPerf;
import java.sql.*;
import java.util.Optional;


public class TirPerfRepository {

    private static TirPerfRepository instance = null;

    public static TirPerfRepository getInstance() {
        if (instance == null) {
            instance = new TirPerfRepository();
        }
        return instance;
    }

    private static final String SQL_CREATE_TirPerf = "INSERT INTO tirperf (tirperf_date, scenario_id) VALUES (?, ?)";
    private static final String SQL_UPDATE_TirPerf = "UPDATE tirperf SET tirperf_date = ?, scenario_id = ? WHERE id = ?";
    private static final String SQL_FIND_TirPerf_BY_ID = "SELECT * FROM tirperf WHERE id = ?";
    private static final String SQL_DELETE_TirPerf = "DELETE FROM tirperf WHERE id = ?";

    public Connection connection = null;

    private TirPerfRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> createTirPerf(TirPerf tirPerf) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TirPerf, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, tirPerf.getDate());
            statement.setInt(2, tirPerf.getScenarioId());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int UpdateTirPerf(TirPerf tirPerf) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TirPerf)) {
            statement.setDate(1, tirPerf.getDate());
            statement.setInt(2, tirPerf.getScenarioId());
            statement.setInt(3, tirPerf.getId());
            return statement.executeUpdate();
        }
    }

    public int DeleteTirPerf(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TirPerf)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<TirPerf> findTirPerfById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TirPerf_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    TirPerf tirPerf = new TirPerf();
                    tirPerf.setId(resultSet.getInt("id"));
                    tirPerf.setDate(resultSet.getDate("tirperf_date"));
                    tirPerf.setScenarioId(resultSet.getInt("scenario_id"));

                    return Optional.of(tirPerf);
                } else {
                    return Optional.empty();
                }
            }
        }
    }
}
