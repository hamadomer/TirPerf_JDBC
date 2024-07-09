package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.FlywayExtension;
import org.example.Helpers.HeplersFunctions;
import org.example.model.ContextExecution;
import org.junit.jupiter.api.extension.ExtendWith;

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

    private static final String SQL_CREATE_ContextExecution = "INSERT INTO contextexecution (pansi_id, env, infocomp, tirperf_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_FIND_ContextExecution_BY_ID = "SELECT * FROM contextexecution WHERE id = ?";

    public Connection connection = null;

    private ContextExecutionRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> createContextExecution(ContextExecution contextExecution) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ContextExecution, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, contextExecution.getPanSiId());
            statement.setString(2, contextExecution.getEnv());
            statement.setString(3, contextExecution.getInfoComplementaire());
            statement.setInt(4, contextExecution.getTirPerfId());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<ContextExecution> findContextExecutionById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ContextExecution_BY_ID)) {
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