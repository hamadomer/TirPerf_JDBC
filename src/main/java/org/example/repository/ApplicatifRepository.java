package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Applicatif;
import org.example.model.Fonction;

import java.sql.*;
import java.util.Optional;


public class ApplicatifRepository {

    private  static final ApplicatifRepository instance = new ApplicatifRepository();

    public static ApplicatifRepository getInstance() {
        return instance;
    }

    private static final String SQL_CREATE_APPLICATIF = "INSERT INTO applicatif (intitule, version, fonction_id) VALUES (?, ?, ?)";
    private static final String SQL_FIND_APPLICATIF_BY_ID = "SELECT * FROM applicatif where id = ?";

    public Connection connection = null;

    private ApplicatifRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> createApplicatif(Applicatif applicatif) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_APPLICATIF, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, applicatif.getIntitule());
            statement.setString(2, applicatif.getVersion());
            statement.setInt(3, applicatif.getFonction());
            statement.executeUpdate();

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<Applicatif> findApplicatifById(Integer id) {
        try {

            PreparedStatement statement = connection.prepareStatement(SQL_FIND_APPLICATIF_BY_ID);
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
}
