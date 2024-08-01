package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Scenario;


import java.sql.*;
import java.util.Optional;


public class ScenarioRepository {

    private static ScenarioRepository instance = null;
    public static ScenarioRepository getInstance() {
        if (instance == null) {
            instance = new ScenarioRepository();
        }
        return instance;
    }


    public Connection connection;

    public ScenarioRepository () {
            connection = DbConnector.getConnection();
    }


    public Optional<Integer> createScenario(Scenario scenario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO scenario (description, applicatif_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, scenario.getDescription());
            statement.setInt(2, scenario.getApplicatif_id());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int updateScenario(Scenario scenario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE scenario SET description = ?, applicatif_id = ? WHERE id = ?")) {
            statement.setString(1, scenario.getDescription());
            statement.setInt(2, scenario.getApplicatif_id());
            statement.setInt(3, scenario.getId());
            return statement.executeUpdate();
        }
    }

    public int deleteScenario(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM scenario WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<Scenario> findScenarioById (Integer id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM scenario WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Scenario scenario = new Scenario();
                scenario.setDescription(resultSet.getString("description"));
                scenario.setApplicatif_id(resultSet.getInt("applicatif_id"));
                return Optional.of(scenario);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}