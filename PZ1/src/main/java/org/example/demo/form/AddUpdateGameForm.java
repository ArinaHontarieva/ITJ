package org.example.demo.form;

public class AddUpdateGameForm {
    String name;
    String publisher;
    int releaseYear;
    int genreId;
    double price;
    int platformId;

    public AddUpdateGameForm() {
    }

    public AddUpdateGameForm(String name, String publisher, int releaseYear, int genreId, double price, int platformId) {
        this.name = name;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.price = price;
        this.platformId = platformId;
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
