package com.example.demo.service;

import com.example.demo.clients.MovieFeignClient;
import com.example.demo.clients.SerieFeignClient;
import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.MovieC;
import com.example.demo.model.SerieC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImp implements CatalogService{

    public MovieFeignClient movieFeignClient;

    public SerieFeignClient serieFeignClient;

    @Autowired
    public CatalogServiceImp(MovieFeignClient movieFeignClient,SerieFeignClient serieFeignClient){
        this.movieFeignClient=movieFeignClient;
        this.serieFeignClient=serieFeignClient;
    }

    @Override
    public List<MovieC> listMovies(int from,int to) throws NoResultsException, ExceedsLimitException {
        return movieFeignClient.li(from,to).getBody();
    }

    @Override
    public MovieC findMovieByName(String name) throws NotFound {
       return movieFeignClient.findByName(name).getBody();
    }

    @Override
    public List<MovieC> findMoviesByGenre(String genre,int from, int to) throws NoResultsException, ExceedsLimitException {
        return movieFeignClient.findByGenre(genre,from,to).getBody();
    }

    @Override
    public List<SerieC> listSeries(int from, int to) throws NoResultsException, ExceedsLimitException {
        return serieFeignClient.li(from, to).getBody();
    }

    @Override
    public SerieC findSerieByName(String name) throws NotFound {
        return serieFeignClient.findByName(name).getBody();
    }

    @Override
    public List<SerieC> findSerieByGenre(String genre, int from, int to) throws NoResultsException, ExceedsLimitException {
        return serieFeignClient.findByGenre(genre, from, to).getBody();
    }
}
