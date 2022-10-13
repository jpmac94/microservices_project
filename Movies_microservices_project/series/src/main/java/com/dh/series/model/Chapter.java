package com.dh.series.model;

public class Chapter {

    private Integer id;
    private String name;
    private String number;
    private String urlStream;

    public Chapter(String name, String number, String urlStream) {
        this.name = name;
        this.number = number;
        this.urlStream = urlStream;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrlStream() {
        return urlStream;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }
}
