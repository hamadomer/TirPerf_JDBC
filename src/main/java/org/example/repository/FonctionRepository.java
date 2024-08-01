package org.example.repository;

import org.example.DB.DbConnector;
import org.example.Helpers.HeplersFunctions;
import org.example.model.Fonction;

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



    public Connection connection = null;

    private FonctionRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createFonction(Fonction fonction) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO fonction (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, fonction.getName());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public int UpdateFonction(int id, String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE fonction SET name = ? WHERE id = ?")) {
           statement.setString(1, name);
           statement.setInt(2, id);
           return statement.executeUpdate();
        }
    }

    public int deleteFonction(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM fonction WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public Optional<Fonction> findFonctionById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fonction WHERE id = ?");
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
