package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Applicatif;

import java.sql.*;
import java.util.Optional;


public class ApplicatifRepository {

    private  static final ApplicatifRepository instance = new ApplicatifRepository();

    public static ApplicatifRepository getInstance() {
        return instance;
    }

    private static final String SQL_CREATE_APPLICATIF = "INSERT INTO applicatif (intitule, version, fonctions) VALUES (?, ?, ?)";
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
            statement.setString(3, applicatif.getFonctions());
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
                String applicatif_fonctions = resultSet.getString("fonctions");

                applicatif.setIntitule(intitule);
                applicatif.setVersion(version);
                applicatif.setFonctions(applicatif_fonctions);

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
