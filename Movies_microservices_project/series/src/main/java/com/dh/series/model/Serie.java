package com.dh.series.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Serie {

    @Id
    private Integer id;
    private String name;

    private String genre;
    private Seasons seasons;
    public Serie(){}

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Seasons getSeasons() {
        return seasons;
    }

    public void setSeasons(Seasons seasons) {
        this.seasons = seasons;
    }

    public Serie(String name, String genre, Seasons seasons) {
        this.name = name;
        this.genre = genre;
        this.seasons = seasons;
    }

    public Serie(String name){
        this.name=name;
    }

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
}
