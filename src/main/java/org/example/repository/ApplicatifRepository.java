package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Applicatif;
import org.example.model.Fonction;

import java.sql.*;
import java.util.Optional;


public class ApplicatifRepository {

    private  static ApplicatifRepository instance = null;

    // Note for future me :
    // Refer to https://enos.itcollege.ee/~jpoial/java/naited/Java-Design-Patterns.pdf, page 32 for more info
    public static ApplicatifRepository getInstance() {
        if (instance == null) {
            instance = new ApplicatifRepository();
        }
        return instance;
    }

    private static final String SQL_CREATE_APPLICATIF = "INSERT INTO applicatif (intitule, version, fonction_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_APPLICATIF_FONCTION = "UPDATE applicatif SET fonction_id = ? WHERE id = ?";
    private static final String SQL_FIND_APPLICATIF_BY_ID = "SELECT * FROM applicatif where id = ?";
    private static final String SQL_DELETE_APPLICATIF = "DELETE FROM applicatif WHERE id = ?";


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

    public int updateApplicatifFonction(int applicatif_id, int fonction_id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_APPLICATIF_FONCTION)){
            statement.setInt(1, fonction_id);
            statement.setInt(2, applicatif_id);
            return statement.executeUpdate();
        }
    }

    public int deleteApplicatif(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_APPLICATIF)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
