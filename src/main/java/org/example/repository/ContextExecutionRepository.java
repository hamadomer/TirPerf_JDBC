package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.ContextExecution;

import java.sql.*;
import java.util.Optional;


public class ContextExecutionRepository {

    private static ContextExecutionRepository instance = null;

    public static ContextExecutionRepository getInstance() {
        if (instance == null) {
            instance = new ContextExecutionRepository();
        }
        return instance;
    }

    public Connection connection;

    private ContextExecutionRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createContextExecution(ContextExecution contextExecution) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO contextexecution (pansi_id, env, infocomp, tirperf_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, contextExecution.getPanSiId());
            statement.setString(2, contextExecution.getEnv());
            statement.setString(3, contextExecution.getInfoComplementaire());
            statement.setInt(4, contextExecution.getTirPerfId());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<ContextExecution> findContextExecutionById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM contextexecution WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ContextExecution contextExecution = new ContextExecution();
                    contextExecution.setId(resultSet.getInt("id"));
                    contextExecution.setPanSiId(resultSet.getInt("pansi_id"));
                    contextExecution.setEnv(resultSet.getString("env"));
                    contextExecution.setInfoComplementaire(resultSet.getString("infocomp"));
                    contextExecution.setTirPerfId(resultSet.getInt("tirperf_id"));

                    return Optional.of(contextExecution);
                } else {
                    return Optional.empty();
                }
            }
        }
    }
}