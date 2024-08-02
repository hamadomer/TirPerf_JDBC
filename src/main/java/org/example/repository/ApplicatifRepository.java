package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Applicatif;
import org.example.model.Scenario;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ApplicatifRepository {

    private static ApplicatifRepository instance = null;

    // Note for future me :
    // Refer to https://enos.itcollege.ee/~jpoial/java/naited/Java-Design-Patterns.pdf, page 32 for more info
    public static ApplicatifRepository getInstance() {
        if (instance == null) {
            instance = new ApplicatifRepository();
        }
        return instance;
    }


    public Connection connection;

    private ApplicatifRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createApplicatif(Applicatif applicatif) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO applicatif (intitule, version, fonction_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, applicatif.getIntitule());
            statement.setString(2, applicatif.getVersion());
            statement.setInt(3, applicatif.getFonction());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int updateApplicatifFonction(int applicatif_id, int fonction_id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE applicatif SET fonction_id = ? WHERE id = ?")) {
            statement.setInt(1, fonction_id);
            statement.setInt(2, applicatif_id);
            return statement.executeUpdate();
        }
    }

    public int deleteApplicatif(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM applicatif WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Optional<Applicatif> findApplicatifById(Integer id) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM applicatif where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                Applicatif applicatif = new Applicatif();

                String intitule = resultSet.getString("intitule");
                String version = resultSet.getString("version");
                Integer applicatif_fonctions = resultSet.getInt("fonction_id");

                applicatif.setIntitule(intitule);
                applicatif.setVersion(version);
                applicatif.setFonction(applicatif_fonctions);

                return Optional.of(applicatif);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Scenario> findAllScenarios(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Scenario where applicatif_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Scenario> scenarios = new ArrayList<>();

            while (resultSet.next()) {
                Scenario scenario = new Scenario();
                scenario.setId(resultSet.getInt("id"));
                scenario.setDescription(resultSet.getString("description"));
                scenario.setApplicatif_id(resultSet.getInt("applicatif_id"));
                scenarios.add(scenario);
            }

            return scenarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
