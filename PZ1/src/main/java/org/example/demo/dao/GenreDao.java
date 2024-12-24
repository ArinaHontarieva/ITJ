package org.example.demo.dao;

import org.example.demo.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> selectAll();

    Genre getGenreById(int genreId);

    public void insertIntoGenres(String genresName);

    public void updateNameById(Genre genre);

    public void deleteFromGenresById(int genresName);
}