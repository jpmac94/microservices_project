package com.dh.series.service;

import com.dh.series.exceptions.AlreadyExistsException;
import com.dh.series.exceptions.ExceedsLimitException;
import com.dh.series.exceptions.NoResultsException;
import com.dh.series.exceptions.NotFound;
import com.dh.series.model.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SerieServiceTest {

    @Autowired
    private SerieService serieService;

    @Test
    void listException(){
        Assertions.assertThrows(NoResultsException.class,()->{serieService.list(3,5).get(0).getName();});
    }

    @Test
    void testCreate() {
        Serie serie=new Serie("the boys");
        String name;
        try{
            name=serieService.create(serie).getName();
        } catch (AlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(name.equals(serie.getName()));
    }

    @Test
    void update() {
        Serie serie=new Serie("the boys");
        serie.setGenre("action");
        Serie actual;
        try{
            actual=serieService.update(serie,"the boys");
        }catch (NotFound e){
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(actual.getName().equals(serie.getName()));
    }

    @Test
    void testList() {
        String name;
        try{
            name=serieService.list(0,1).get(0).getName();
        } catch (NoResultsException e) {
            throw new RuntimeException(e);
        } catch (ExceedsLimitException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(name.equals("friends"));
    }

    @Test
    void testListExceedsLimitException(){
        Assertions.assertThrows(ExceedsLimitException.class,()->serieService.list(0,18));
    }

    @Test
    void findByName() {
        Serie serie;
        try{
            serie=serieService.findByName("friends");
        } catch (NotFound e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(serie.getName().equals("friends"));
    }

    @Test
    void testAlreadyExistsException(){
        Serie serie=new Serie("friends");
        try {
            serieService.create(serie);
        } catch (AlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(AlreadyExistsException.class,()->serieService.create(serie));
    }

    @Test
    void deleteName() {
        try{
            serieService.deleteName("friends");
        } catch (NotFound e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows( NotFound.class,()->serieService.findByName("friends"));
    }

    @Test
    void findByGenre() {
        String name;
        try {
            name=serieService.findByGenre("action",0,3).get(0).getName();
        } catch (NoResultsException e) {
            throw new RuntimeException(e);
        } catch (ExceedsLimitException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(name.equals("the boys"));
    }

    @Test
    void findByGenreNoResultsException(){
        Assertions.assertThrows(NoResultsException.class,()->{serieService.findByGenre("romantic",0,3);});
    }

    @Test
    void findByGenreExceedsLimitException(){
        Assertions.assertThrows(ExceedsLimitException.class,()->serieService.findByGenre("action",0,13));
    }
}