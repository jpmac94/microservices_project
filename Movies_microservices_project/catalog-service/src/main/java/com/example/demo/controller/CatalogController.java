package com.example.demo.controller;

import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.MovieC;
import com.example.demo.model.SerieC;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CatalogController {

    public ResponseEntity<List<MovieC>> listMovies(int from, int to) throws NoResultsException, ExceedsLimitException;

    public ResponseEntity<MovieC> findMovieByName(String name) throws NotFound;

    public ResponseEntity<List<MovieC>> findMoviesByGenre(String genre,int from, int to) throws NoResultsException, ExceedsLimitException;

    public ResponseEntity<List<SerieC>> listSeries(int from, int to) throws NoResultsException, ExceedsLimitException;

    public ResponseEntity<SerieC> findSerieByName(String name) throws NotFound;

    public ResponseEntity<List<SerieC>> findSerieByGenre(String genre,int from, int to) throws NoResultsException, ExceedsLimitException;

}
