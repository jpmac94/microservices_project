package com.example.demo.model;

public class MovieC {

    private Integer id;
    private String name;
    private String genre;
    private String urlStream;

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

    public String getUrlStream() {
        return urlStream;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }

    public MovieC(Integer id, String name, String genre, String urlStream) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.urlStream = urlStream;
    }
}
