package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.RapportExecution;
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


    public Connection connection;

    private RapportExecutionRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createRapportEx(RapportExecution rapportEx) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO rapportexecution (callsnumber, successrate, errors, duration, tirperf_id) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, rapportEx.getCallsNumber());
            statement.setInt(2, rapportEx.getSuccessRate());
            statement.setString(3, rapportEx.getErrors());
            statement.setInt(4, rapportEx.getDuration());
            statement.setInt(5, rapportEx.getTirPerfId());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int UpdateRapportEx(RapportExecution rapportEx) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE rapportexecution SET callsNumber = ?, successrate = ?, errors = ?, duration = ?, tirperf_id = ? WHERE id = ?")) {
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
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM rapportexecution WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<RapportExecution> findRapportExById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rapportexecution WHERE id = ?");
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
