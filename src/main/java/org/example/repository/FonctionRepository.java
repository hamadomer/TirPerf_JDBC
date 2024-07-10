package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.FlywayExtension;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Fonction;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.*;
import java.util.Optional;


public class FonctionRepository {

    private static FonctionRepository instance = null;

    public static FonctionRepository getInstance() {
        if (instance == null) {
            instance = new FonctionRepository();
        }
        return instance;
    }

    private static final String SQL_CREATE_FONCTION = "INSERT INTO fonction (name) VALUES (?)";
    private static final String SQL_UPDATE_FONCTION = "UPDATE fonction SET name = ? WHERE id = ?";
    private static final String SQL_FIND_FONCTION_BY_ID = "SELECT * FROM fonction WHERE id = ?";
    private static final String SQL_DELETE_FONCTION = "DELETE FROM fonction WHERE id = ?";

    public Connection connection = null;

    private FonctionRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> createFonction(Fonction fonction) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_FONCTION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, fonction.getName());
            statement.executeUpdate();

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int UpdateFonction(int id, String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_FONCTION)) {
           statement.setString(1, name);
           statement.setInt(2, id);
           return statement.executeUpdate();
        }
    }

    public int deleteFonction(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FONCTION)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<Fonction> findFonctionById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_FONCTION_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Fonction fonction = new Fonction();

                String name = resultSet.getString("name");
                fonction.setName(name);

                return Optional.of(fonction);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
