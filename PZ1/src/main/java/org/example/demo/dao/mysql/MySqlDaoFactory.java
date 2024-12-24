package org.example.demo.dao.mysql;

import org.example.demo.dao.DaoFactory;
import org.example.demo.dao.GameDao;
import org.example.demo.dao.GenreDao;
import org.example.demo.dao.PlatformDao;
import main.java.utils.PlatformPropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory extends DaoFactory {
    public static final String DATABASE_URL = new PlatformPropertiesUtil().getDatabaseUrl();
    public static final String DATABASE_PASSWORD = new PlatformPropertiesUtil().getDatabasePassword();
    private static final String DATABASE_USERNAME = new PlatformPropertiesUtil().getDatabaseUsername();
    private static Connection connection;
    private static MySqlDaoFactory instance;

    public MySqlDaoFactory() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException exception) {
            System.out.println("Something went wrong during the connection to MySQL database");
            exception.printStackTrace();
        }
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public PlatformDao getPlatformDao() {
        return MySqlPlatformDao.getInstance();
    }

    @Override
    public GenreDao getGenreDao() {
        return MySqlGenreDao.getInstance();
    }

    @Override
    public GameDao getGameDao() {
        return MySqlGameDao.getInstance();
    }
}
