package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.FlywayExtension;
import org.example.Helpers.HeplersFunctions;
import org.example.model.FonctionScenario;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.*;
import java.util.Optional;


public class FonctionScenarioRepository {

    private static final FonctionScenarioRepository instance = new FonctionScenarioRepository();

    public static FonctionScenarioRepository getInstance() {
        return instance;
    }

    private static final String SQL_CREATE_FONCTION_SCENARIO = "INSERT INTO fonction_scenario (fonction_id, scenario_id) VALUES (?, ?)";
    private static final String SQL_FIND_FONCTION_SCENARIO_BY_ID = "SELECT * FROM fonction_scenario WHERE fonction_id = ? AND scenario_id = ?";

    public Connection connection = null;

    private FonctionScenarioRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> createFonctionScenario(FonctionScenario fonctionScenario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_FONCTION_SCENARIO, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, fonctionScenario.getFonction_id());
            statement.setInt(2, fonctionScenario.getScenario_id());
            statement.executeUpdate();

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<FonctionScenario> findFonctionScenarioById(Integer fonction_id, Integer scenario_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_FONCTION_SCENARIO_BY_ID);
            statement.setInt(1, fonction_id);
            statement.setInt(2, scenario_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                FonctionScenario fonctionScenario = new FonctionScenario();

                fonctionScenario.setFonction_id(fonction_id);
                fonctionScenario.setScenario_id(scenario_id);

                return Optional.of(fonctionScenario);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
