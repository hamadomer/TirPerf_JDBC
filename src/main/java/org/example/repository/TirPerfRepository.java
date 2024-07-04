package org.example.repository;

import org.example.DB.DbConnector;
import org.example.model.TirPerf;

import java.sql.*;
import java.util.Optional;

public class TirPerfRepository {

    private static final   TirPerfRepository instance = new TirPerfRepository();
    public static TirPerfRepository getInstance() { return instance; }

    private static final String SQL_CREATE_TirPerf = "INSERT INTO tirperf (tir_perf_date, scenario_id) VALUES (?, ?)";
    private static final String SQL_FIND_TirPerf_BY_ID = "SELECT * FROM tirperf WHERE id = ?";

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
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return Optional.of(generatedKeys.getInt(1));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<TirPerf> findTirPerfById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TirPerf_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    TirPerf tirPerf = new TirPerf();
                    tirPerf.setId(resultSet.getInt("id"));
                    tirPerf.setDate(resultSet.getDate("tir_perf_date"));
                    tirPerf.setScenarioId(resultSet.getInt("scenario_id"));

                    return Optional.of(tirPerf);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
