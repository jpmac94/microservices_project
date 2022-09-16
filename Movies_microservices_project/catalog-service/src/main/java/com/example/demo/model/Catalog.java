package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Catalog {
    @Id
    private Integer id;

    private String genre;

    private SerieC serieC;

    private MovieC movieC;
}
