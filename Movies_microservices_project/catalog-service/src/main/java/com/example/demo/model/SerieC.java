package com.example.demo.model;

public class SerieC {

    private Integer id;
    private String name;

    private String genre;
    private SeasonC seasons;

    public SerieC(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public SeasonC getSeasons() {
        return seasons;
    }

    public void setSeasons(SeasonC seasons) {
        this.seasons = seasons;
    }

    public SerieC(Integer id, String name, String genre, SeasonC seasons) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.seasons = seasons;
    }
}
