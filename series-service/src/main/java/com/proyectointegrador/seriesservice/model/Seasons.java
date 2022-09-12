package com.proyectointegrador.seriesservice.model;

public class Seasons {

    private Integer id;
    private Integer seasonNum;
    private Chapter chapter;

    public Seasons(Integer seasonNum, Chapter chapter) {
        this.seasonNum = seasonNum;
        this.chapter = chapter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeasonNum() {
        return seasonNum;
    }

    public void setSeasonNum(Integer seasonNum) {
        this.seasonNum = seasonNum;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
