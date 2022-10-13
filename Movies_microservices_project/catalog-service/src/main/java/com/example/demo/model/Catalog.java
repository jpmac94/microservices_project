package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Catalog {
    @Id
    private Integer id;

    private String genre;

    private List<SerieC> serieC;

    private List<MovieC> movieC;

    public Catalog(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<SerieC> getSerieC() {
        return serieC;
    }

    public void setSerieC(List<SerieC> serieC) {
        this.serieC = serieC;
    }

    public List<MovieC> getMovieC() {
        return movieC;
    }

    public void setMovieC(List<MovieC> movieC) {
        this.movieC = movieC;
    }

    public Catalog(Integer id, String genre, List<SerieC> serieC, List<MovieC> movieC) {
        this.id = id;
        this.genre = genre;
        this.serieC = serieC;
        this.movieC = movieC;
    }
}
