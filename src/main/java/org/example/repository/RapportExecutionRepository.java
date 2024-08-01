package org.example.repository;

import org.example.DB.DbConnector;

import org.example.Helpers.FlywayExtension;
import org.example.Helpers.HeplersFunctions;

import org.example.model.RapportExecution;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.*;
import java.util.Optional;


public class RapportExecutionRepository {

    private static RapportExecutionRepository instance = null;

    public static RapportExecutionRepository getInstance() {
        if (instance == null) {
            instance = new RapportExecutionRepository();
        }
        return instance;
    }

    private static final String SQL_CREATE_RAPPORT_EX = "INSERT INTO rapportexecution (callsnumber, successrate, errors, duration, tirperf_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_RAPPORT_EX = "UPDATE rapportexecution SET callsNumber = ?, successrate = ?, errors = ?, duration = ?, tirperf_id = ? WHERE id = ?";
    private static final String SQL_FIND_RAPPORT_EX_BY_ID = "SELECT * FROM rapportexecution WHERE id = ?";
    private static final String SQL_DELETE_RAPPORT_EX = "DELETE FROM rapportexecution WHERE id = ?";

    public Connection connection = null;

    private RapportExecutionRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createRapportEx(RapportExecution rapportEx) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_RAPPORT_EX, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, rapportEx.getCallsNumber());
            statement.setInt(2, rapportEx.getSuccessRate());
            statement.setString(3, rapportEx.getErrors());
            statement.setInt(4, rapportEx.getDuration());
            statement.setInt(5, rapportEx.getTirPerfId());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int UpdateRapportEx(RapportExecution rapportEx) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_RAPPORT_EX)) {
            statement.setInt(1, rapportEx.getCallsNumber());
            statement.setInt(2, rapportEx.getSuccessRate());
            statement.setString(3, rapportEx.getErrors());
            statement.setInt(4, rapportEx.getDuration());
            statement.setInt(5, rapportEx.getTirPerfId());
            statement.setInt(6, rapportEx.getId());
            return statement.executeUpdate();
        }
    }

    public int DeleteRapportEx(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RAPPORT_EX)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<RapportExecution> findRapportExById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_RAPPORT_EX_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                RapportExecution rapportEx = new RapportExecution();

                Integer callsNumber = resultSet.getInt("callsNumber");
                Integer succesRate = resultSet.getInt("successrate");
                String errors = resultSet.getString("errors");
                Integer duration = resultSet.getInt("duration");
                Integer TirPerf_id = resultSet.getInt("tirperf_id");

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
