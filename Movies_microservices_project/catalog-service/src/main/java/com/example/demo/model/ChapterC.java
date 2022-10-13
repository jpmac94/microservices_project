package com.example.demo.model;

public class ChapterC {

    private Integer id;
    private String name;
    private String number;
    private String urlStream;

    public ChapterC(){}

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

    public ChapterC(Integer id, String name, String number, String urlStream) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.urlStream = urlStream;
    }
}
