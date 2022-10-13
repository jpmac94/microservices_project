package com.example.demo.service;

import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.Catalog;
import com.example.demo.model.MovieC;
import com.example.demo.model.SerieC;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatalogServiceImpTest {
    @Autowired
    public CatalogServiceImp catalogServiceImp;

    @Test
    void list() {
        List<MovieC> movieCList;
        try{
            movieCList= catalogServiceImp.listMovies(0,8);
        } catch (NoResultsException e) {
            throw new RuntimeException(e);
        } catch (ExceedsLimitException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(movieCList.size()==8);
    }


    @Test
    void findSerieByName(){
        String nameActual;
        try{
            nameActual=catalogServiceImp.findSerieByName("lost").getName();
        } catch (NotFound e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(nameActual.equals("lost"));
    }


    @Test
    void listSerieByGenre(){
        List<SerieC> serieCList;
        try{
            serieCList=catalogServiceImp.findSerieByGenre("action",0,3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(serieCList.size()==2);
    }

    @Test
    void name(){
        String nameActual;
        try{
            nameActual=catalogServiceImp.findMovieByName("dune").getName();
        } catch (NotFound e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(nameActual.equals("dune"));
    }

    @Test
    void genre(){
        List<MovieC> movieCList;

        try {
            movieCList=catalogServiceImp.findMoviesByGenre("Terror",0,8);
        } catch (NoResultsException e) {
            throw new RuntimeException(e);
        } catch (ExceedsLimitException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(movieCList.size()==3);
    }

    @Test
    void notFound(){
        Assertions.assertThrows(FeignException.NotFound.class,()->catalogServiceImp.findMovieByName("terminator"));
    }

    @Test
    void exceedsLimit(){
        Assertions.assertThrows(FeignException.BadRequest.class,()-> catalogServiceImp.listMovies(0,13));
    }

    @Test
    void findByGenre(){
        Catalog catalog=new Catalog();
        catalog.setId(1);
        catalog.setGenre("comedy");
        catalogServiceImp.createUpdate(catalog);
        Assertions.assertTrue(catalogServiceImp.findByGenre("comedy").getId().equals(1));

    }

    @Test
    void createUpdate(){
       Catalog catalog=new Catalog();
       catalog.setId(2);
       catalog.setGenre("action");
       Assertions.assertTrue(catalogServiceImp.createUpdate(catalog).getGenre().equals("action"));

    }
}