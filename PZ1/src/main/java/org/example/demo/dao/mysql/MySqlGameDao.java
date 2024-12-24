package org.example.demo.dao.mysql;

import org.example.demo.dao.DaoFactory;
import org.example.demo.dao.GameDao;
import org.example.demo.entity.Game;
import org.example.demo.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlGameDao implements GameDao {

    public static final String UPDATE_NAME_BY_ID =
            "UPDATE computer_games SET cg_name=? WHERE cg_id=?;";
    public static final String UPDATE_GAME = "UPDATE computer_games SET cg_name=?, cg_publisher=?, " +
            "cg_release_year=?, genre_id=?, cg_price=?, platform_id=? WHERE cg_id=?;";
    public static final String DELETE_BY_ID = "DELETE FROM computer_games WHERE cg_id=? LIMIT 100;";
    private final static String SELECT_ALL = "SELECT * FROM computer_games;";
    public static final String  SELECT_GAME_BY_ID = "SELECT * FROM computer_games WHERE cg_id=?";
    private static final String INSERT_INTO = "INSERT INTO computer_games (cg_name, cg_publisher, cg_release_year, genre_id, cg_price, platform_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static MySqlGameDao instance;

    public static GameDao getInstance() {
        if (instance == null) {
            instance = new MySqlGameDao();
        }
        return instance;
    }

    @Override
    public List<Game> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Something went wrong during selectAll method: \n" + e);
        }
        List<Game> games = new ArrayList<>();
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    int gameId = resultSet.getInt("cg_id");
                    String gameName = resultSet.getString("cg_name");
                    String gamePublisher = resultSet.getString("cg_publisher");
                    int gameReleaseYear = resultSet.getInt("cg_release_year");
                    int gameGenreId = resultSet.getInt("genre_id");
                    double gamePrice = resultSet.getDouble("cg_price");
                    int gamePlatformId = resultSet.getInt("platform_id");

                    games.add(new Game(gameId, gameName, gamePublisher, gameReleaseYear, gameGenreId, gamePrice, gamePlatformId));
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Something went wrong");
        }
        return games;
    }

    @Override
    public Game selectGameById(int gameId) {
        try {
            PreparedStatement preparedStatement = daoFactory.getConnection().prepareStatement(SELECT_GAME_BY_ID);
            preparedStatement.setInt(1, gameId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String gameName = resultSet.getString("cg_name");
                    String gamePublisher = resultSet.getString("cg_publisher");
                    int gameReleaseYear = resultSet.getInt("cg_release_year");
                    int gameGenreId = resultSet.getInt("genre_id");
                    double gamePrice = resultSet.getDouble("cg_price");
                    int gamePlatformId = resultSet.getInt("platform_id");

                    Genre genre = MySqlGenreDao.getInstance().getGenreById(gameGenreId);

                    return new Game(gameId, gameName, gamePublisher, gameReleaseYear, gameGenreId, gamePrice, gamePlatformId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong during selectGameById method: \n" + e);
        }
        return null;
    }

    @Override
    public void insertIntoGame(String gameName, String gamePublisher, int gameReleaseYear, int genreId, double gamePrice, int PlatformId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(INSERT_INTO);
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2, gamePublisher);
            preparedStatement.setInt(3, gameReleaseYear);
            preparedStatement.setInt(4, genreId);
            preparedStatement.setDouble(5, gamePrice);
            preparedStatement.setInt(6, PlatformId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during INSERT INTO query: \n" + e);
        }
    }

    @Override
    public void updateName(Game game, String newName) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(UPDATE_NAME_BY_ID);

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, game.getGameId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during UPDATE query: \n" + e);
        }
    }

    @Override
    public void updateGame(Game game) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(UPDATE_GAME);

            preparedStatement.setString(1, game.getName());
            preparedStatement.setString(2, game.getPublisher());
            preparedStatement.setInt(3, game.getReleaseYear());
            preparedStatement.setInt(4, game.getGenreId());
            preparedStatement.setDouble(5, game.getPrice());
            preparedStatement.setInt(6, game.getPlatformId());
            preparedStatement.setInt(7, game.getGameId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during UPDATE_GAME query: \n" + e);
        }
    }

    @Override
    public void deleteFromGamesById(int gameId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, gameId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during INSERT INTO query: \n" + e);
        }
    }
}
