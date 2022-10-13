package com.dh.series.model;

import java.util.List;

public class Seasons {

    private Integer id;
    private Integer seasonNum;
    private List<Chapter> chapter;

    public Seasons(Integer seasonNum, List<Chapter> chapter) {
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

    public List<Chapter> getChapter() {
        return chapter;
    }

    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }
}
