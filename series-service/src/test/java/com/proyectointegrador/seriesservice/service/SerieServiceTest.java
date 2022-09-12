package com.proyectointegrador.seriesservice.service;

import com.proyectointegrador.seriesservice.exceptions.AlreadyExistsException;
import com.proyectointegrador.seriesservice.exceptions.ExceedsLimitException;
import com.proyectointegrador.seriesservice.exceptions.NoResultsException;
import com.proyectointegrador.seriesservice.exceptions.NotFound;
import com.proyectointegrador.seriesservice.model.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Serie serie=new Serie("friends");
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
}