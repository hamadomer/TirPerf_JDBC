package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.FlywayExtension;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Scenario;
import org.junit.jupiter.api.extension.ExtendWith;

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
    private static final String SQL_CREATE_SCENARIO = "INSERT INTO scenario (description, applicatif_id) VALUES (?, ?)";
    private static final String SQL_UPDATE_SCENARIO = "UPDATE scenario SET description = ?, applicatif_id = ? WHERE id = ?";
    private static final String SQL_FIND_SCENARIO_BY_ID = "SELECT * FROM scenario WHERE id = ?";
    private static final String SQL_DELETE_SCENARIO = "DELETE FROM scenario WHERE id = ?";


    public Connection connection = null;

    public ScenarioRepository () {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Integer> createScenario(Scenario scenario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_SCENARIO, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, scenario.getDescription());
            statement.setInt(2, scenario.getApplicatif_id());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int updateScenario(Scenario scenario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SCENARIO)) {
            statement.setString(1, scenario.getDescription());
            statement.setInt(2, scenario.getApplicatif_id());
            statement.setInt(3, scenario.getId());
            return statement.executeUpdate();
        }
    }

    public int deleteScenario(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SCENARIO)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<Scenario> findScenarioById (Integer id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_SCENARIO_BY_ID);
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