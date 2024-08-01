package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.FonctionScenario;

import java.sql.*;
import java.util.Optional;


public class FonctionScenarioRepository {

    private static FonctionScenarioRepository instance = null;

    public static FonctionScenarioRepository getInstance() {
        if (instance == null) {
            instance = new FonctionScenarioRepository();
        }
        return instance;
    }

    public Connection connection;

    private FonctionScenarioRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createFonctionScenario(FonctionScenario fonctionScenario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO fonction_scenario (fonction_id, scenario_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, fonctionScenario.getFonction_id());
            statement.setInt(2, fonctionScenario.getScenario_id());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<FonctionScenario> findFonctionScenarioById(Integer fonction_id, Integer scenario_id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fonction_scenario WHERE fonction_id = ? AND scenario_id = ?");
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
