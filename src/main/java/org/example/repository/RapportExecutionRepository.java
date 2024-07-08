package org.example.repository;

import org.example.DB.DbConnector;

import org.example.Helpers.FlywayExtension;
import org.example.Helpers.HeplersFunctions;

import org.example.model.RapportExecution;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.*;
import java.util.Optional;


public class RapportExecutionRepository {

    private static final RapportExecutionRepository instance = new RapportExecutionRepository();

    public static RapportExecutionRepository getInstance() {
        return instance;
    }

    private static final String SQL_CREATE_RAPPORT_EX = "INSERT INTO rapport_ex (nbAppel, tauxSuccesKo, erreurs, duration, TirPerf_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_RAPPORT_EX_BY_ID = "SELECT * FROM rapport_ex WHERE id = ?";

    public Connection connection = null;

    private RapportExecutionRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> createRapportEx(RapportExecution rapportEx) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_RAPPORT_EX, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, rapportEx.getCallsNumber());
            statement.setInt(2, rapportEx.getSuccessRate());
            statement.setString(3, rapportEx.getErrors());
            statement.setInt(4, rapportEx.getDuration());
            statement.setInt(5, rapportEx.getTirPerfId());
            statement.executeUpdate();

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<RapportExecution> findRapportExById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_RAPPORT_EX_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                RapportExecution rapportEx = new RapportExecution();

                Integer callsNumber = resultSet.getInt("nbAppel");
                Integer succesRate = resultSet.getInt("tauxSuccesKo");
                String errors = resultSet.getString("erreurs");
                Integer duration = resultSet.getInt("duration");
                Integer TirPerf_id = resultSet.getInt("TirPerf_id");

                rapportEx.setCallsNumber(callsNumber);
                rapportEx.setSuccessRate(succesRate);
                rapportEx.setErrors(errors);
                rapportEx.setDuration(duration);
                rapportEx.setTirPerfId(TirPerf_id);

                return Optional.of(rapportEx);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
