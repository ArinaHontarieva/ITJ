package org.example.demo.dao;

import org.example.demo.dao.mysql.MySqlDaoFactory;
import main.java.utils.PlatformPropertiesUtil;

import java.sql.Connection;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        switch (new PlatformPropertiesUtil().getDatabaseType()) {
            case "MySQL":
                return MySqlDaoFactory.getInstance();
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract Connection getConnection();

    public abstract PlatformDao getPlatformDao();

    public abstract GenreDao getGenreDao();

    public abstract GameDao getGameDao();
}
