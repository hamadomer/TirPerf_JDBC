package org.example.repository;

import org.example.DB.DbConnector;

import org.example.Helpers.HeplersFunctions;
import org.example.model.AppPanSI;


import java.sql.*;
import java.util.Optional;


public class AppPanSIRepository {

    private static AppPanSIRepository instance = null;

    public static AppPanSIRepository getInstance() {
        if (instance == null) {
            instance = new AppPanSIRepository();
        }
        return instance;
    }


    public Connection connection;

    private AppPanSIRepository() {
            connection = DbConnector.getConnection();
    }

    public Optional<Integer> createAppPanSI(AppPanSI appPanSI) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO app_panSI (PanSI_id, app_id, app_version) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, appPanSI.getPansiId());
            statement.setInt(2, appPanSI.getAppId());
            statement.setString(3, appPanSI.getAppVersion());

            return HeplersFunctions.getGeneratedKeys(statement);
        }
    }

    public Optional<AppPanSI> findAppPanSIById(Integer PanSI_id, Integer app_id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM app_panSI WHERE PanSI_id = ? AND app_id = ?");
            statement.setInt(1, PanSI_id);
            statement.setInt(2, app_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                AppPanSI appPanSI = new AppPanSI();

                String app_version = resultSet.getString("app_version");
                appPanSI.setAppVersion(app_version);

                return Optional.of(appPanSI);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
