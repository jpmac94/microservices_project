package com.example.demo.service;

import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.MovieC;
import com.example.demo.model.SerieC;

import java.util.List;

public interface CatalogService {

    public List<MovieC> listMovies(int from, int to) throws NoResultsException, ExceedsLimitException;

    public MovieC findMovieByName(String name) throws NotFound;

    public List<MovieC> findMoviesByGenre(String genre,int from, int to) throws NoResultsException, ExceedsLimitException;

    public List<SerieC> listSeries(int from, int to) throws NoResultsException, ExceedsLimitException;

    public SerieC findSerieByName(String name) throws NotFound;

    public List<SerieC> findSerieByGenre(String genre,int from, int to) throws NoResultsException, ExceedsLimitException;


}
