package org.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="computer_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int gameId;
    private String name;
    private String publisher;
    private int releaseYear;
    private int genreId;
    private double price;
    private int platformId;

    public Game(Integer gameId, String name, String publisher, Integer releaseYear, int genreId, Double price, Integer platformId) {
        this.gameId = gameId;
        this.name = name;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.price = price;
        this.platformId = platformId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }
}
