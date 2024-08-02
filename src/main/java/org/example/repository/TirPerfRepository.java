package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.ContextExecution;
import org.example.model.TirPerf;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TirPerfRepository {

    private static TirPerfRepository instance = null;

    public static TirPerfRepository getInstance() {
        if (instance == null) {
            instance = new TirPerfRepository();
        }
        return instance;
    }

    public Connection connection;

    private TirPerfRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createTirPerf(TirPerf tirPerf) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO tirperf (tirperf_date, scenario_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, tirPerf.getDate());
            statement.setInt(2, tirPerf.getScenarioId());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int UpdateTirPerf(TirPerf tirPerf) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE tirperf SET tirperf_date = ?, scenario_id = ? WHERE id = ?")) {
            statement.setDate(1, tirPerf.getDate());
            statement.setInt(2, tirPerf.getScenarioId());
            statement.setInt(3, tirPerf.getId());
            return statement.executeUpdate();
        }
    }

    public int DeleteTirPerf(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM tirperf WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<TirPerf> findTirPerfById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tirperf WHERE id = ?")) {
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

    public List<ContextExecution> getAllContextExecutions(int id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.*\n" +
                    "FROM contextexecution c\n" +
                    "WHERE c.tirperf_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<ContextExecution> contextExecutions = new ArrayList<>();

            while (resultSet.next()) {
                ContextExecution contextExecution = new ContextExecution();
                contextExecution.setId(resultSet.getInt("id"));
                contextExecution.setTirPerfId(resultSet.getInt("tirperf_id"));
                contextExecution.setEnv(resultSet.getString("env"));
                contextExecution.setPanSiId(resultSet.getInt("pan_si_id"));
                contextExecution.setInfoComplementaire(resultSet.getString("info_complementaire"));
                contextExecutions.add(contextExecution);
            }
            return contextExecutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
