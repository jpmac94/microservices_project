package com.example.demo.model;

public class SeasonC {

    private Integer id;
    private Integer seasonNum;
    private ChapterC chapter;

    public SeasonC(){}

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

    public ChapterC getChapter() {
        return chapter;
    }

    public void setChapter(ChapterC chapter) {
        this.chapter = chapter;
    }

    public SeasonC(Integer id, Integer seasonNum, ChapterC chapter) {
        this.id = id;
        this.seasonNum = seasonNum;
        this.chapter = chapter;
    }
}
