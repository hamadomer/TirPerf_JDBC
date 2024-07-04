package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Scenario;

import java.sql.*;
import java.util.Optional;

public class ScenarioRepository {

    private static final ScenarioRepository instance = new ScenarioRepository();
    public static ScenarioRepository getInstance() {
        return instance;
    }
    private static final String SQL_CREATE_SCENARIO = "INSERT INTO scenario (description, applicatif_id, scenario_fonctions) VALUES (?, ?, ?)";
    private static final String SQL_FIND_SCENARIO_BY_ID = "SELECT * FROM scenario WHERE id = ?";

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
            statement.setString(3, scenario.getScenario_fonctions());

            return HeplersFunctions.getGeneratedKeys(statement);
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
              scenario.setScenario_fonctions(resultSet.getString("scenario_fonctions"));
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
