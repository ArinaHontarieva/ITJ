package org.example.demo.dao.mysql;

import org.example.demo.dao.DaoFactory;
import org.example.demo.dao.PlatformDao;
import org.example.demo.entity.Game;
import org.example.demo.entity.Genre;
import org.example.demo.entity.Platform;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlatformDao implements PlatformDao {

    private static final String SELECT_ALL = "SELECT * FROM platform;";
    public static final String  SELECT_PLATFORM_BY_ID = "SELECT * FROM platform WHERE platform_id=?";
    private static final String INSERT_INTO = "INSERT INTO platform (platform_name) VALUES (?);";
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE platform SET platform_name=? WHERE platform_id=?  LIMIT 100;";
    public static final String DELETE_BY_ID = "DELETE FROM platform WHERE platform_id=? LIMIT 100;";
    private static MySqlPlatformDao instance;
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();

    public static PlatformDao getInstance() {
        if (instance == null) {
            instance = new MySqlPlatformDao();
        }
        return instance;
    }

    @Override
    public List<Platform> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Something went wrong during selectAll method: \n" + e);
        }
        List<Platform> platforms = new ArrayList<>();
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    int platformId = resultSet.getInt("platform_id");
                    String platformName = resultSet.getString("platform_name");
                    platforms.add(new Platform(platformId, platformName));
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Something went wrong");
        }
        return platforms;
    }

    @Override
    public Platform selectPlatformById(int platformId) {
        try {
            PreparedStatement preparedStatement = daoFactory.getConnection().prepareStatement(SELECT_PLATFORM_BY_ID);
            preparedStatement.setInt(1, platformId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String gameName = resultSet.getString("platform_name");


                    return new Platform(platformId, gameName);
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong during selectPlatformById method: \n" + e);
        }
        return null;

    }

    @Override
    public void insertIntoPlatform(String platformName) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(INSERT_INTO);
            preparedStatement.setString(1, platformName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during INSERT INTO query: \n" + e);
        }
    }

    @Override
    public void updateNameById(Platform platform) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(UPDATE_NAME_BY_ID);
            preparedStatement.setString(1, platform.getName());
            preparedStatement.setInt(2, platform.getPlatformId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during UPDATE query: \n" + e);
        }
    }

    @Override
    public void deleteFromPlatformById(int platformId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, platformId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during INSERT INTO query: \n" + e);
        }
    }
}
