package org.example.demo.dao.mysql;

import org.example.demo.dao.DaoFactory;
import org.example.demo.dao.GenreDao;
import org.example.demo.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlGenreDao implements GenreDao {
    private final static String SELECT_ALL = "SELECT * FROM genres";
    public static final String GET_GENRE_BY_ID = "SELECT * FROM genres WHERE genres_id=?;";
    private final static String INSERT_INTO = "INSERT INTO genres (genres_name) VALUES (?);";
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE genres SET genres_name=? WHERE genres_id=?  LIMIT 100;";
    public static final String DELETE_BY_ID = "DELETE FROM genres WHERE genres_id=? LIMIT 100;";
    private static MySqlGenreDao instance;
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();

    public static GenreDao getInstance() {
        if (instance == null) {
            instance = new MySqlGenreDao();
        }
        return instance;
    }

    @Override
    public List<Genre> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Something went wrong during selectAll method: \n" + e);
        }
        List<Genre> genres = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int genreId = resultSet.getInt("genres_id");
                String name = resultSet.getString("genres_name");
                genres.add(new Genre(genreId, name));
            }
        } catch (SQLException sqlException) {
            System.out.println("Something went wrong");
        }
        return genres;
    }

    @Override
    public Genre getGenreById(int genreId) {
        try {
            PreparedStatement preparedStatement = daoFactory.getConnection().prepareStatement(GET_GENRE_BY_ID);
            preparedStatement.setInt(1, genreId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Genre(resultSet.getInt("genres_id"),
                            resultSet.getString("genres_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong during getGenreById method: \n" + e);
        }
        return null;
    }


    @Override
    public void insertIntoGenres(String genresName) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(INSERT_INTO);
            preparedStatement.setString(1, genresName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during INSERT INTO query: \n" + e);
        }
    }

    @Override
    public void updateNameById(Genre genre) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(UPDATE_NAME_BY_ID);
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setInt(2, genre.getGenreId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during UPDATE query: \n" + e);
        }
    }

    @Override
    public void deleteFromGenresById(int genreId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = daoFactory.getConnection().prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, genreId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong during DELETE query: \n" + e);
        }
    }
}
