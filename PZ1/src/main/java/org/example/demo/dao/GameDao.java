package org.example.demo.dao;

import org.example.demo.entity.Game;

import java.util.List;

public interface GameDao {
    List<Game> selectAll();
    Game selectGameById(int gameId);

    public void insertIntoGame(String gameName, String gamePublisher, int gameReleaseYear, int genreId, double gamePrice, int PlatformId);

    public void updateName(Game game, String newName);

    public void updateGame(Game game);

    public void deleteFromGamesById(int gameId);
}
