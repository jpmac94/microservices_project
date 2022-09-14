package com.example.demo.service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    void listException(){
        Assertions.assertThrows(NoResultsException.class,()->{
            movieService.list(3,5).get(0).getName();});
    }

    @Test
    void testCreate() {
        Movie movie=new Movie();
        movie.setName("the boys");
        String name;
        try{
            name= movieService.create(movie).getName();
        } catch (AlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(name.equals(movie.getName()));
    }

    @Test
    void update() {
        Movie movie=new Movie();
        movie.setName("the boys");
        movie.setGenre("action");
        Movie actual;
        try{
            actual= movieService.update(movie,"the boys");
        }catch (NotFound e){
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(actual.getName().equals(movie.getName()));
    }

    @Test
    void testList() {
        String name;
        try{
            name= movieService.list(0,1).get(0).getName();
        } catch (NoResultsException e) {
            throw new RuntimeException(e);
        } catch (ExceedsLimitException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(name.equals("friends"));
    }

    @Test
    void testListExceedsLimitException(){
        Assertions.assertThrows(ExceedsLimitException.class,()-> movieService.list(0,18));
    }

    @Test
    void findByName() {
        Movie movie;
        try{
            movie= movieService.findByName("friends");
        } catch (NotFound e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(movie.getName().equals("friends"));
    }

    @Test
    void testAlreadyExistsException(){
        Movie movie=new Movie();
        movie.setName("friends");
        try {
            movieService.create(movie);
        } catch (AlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(AlreadyExistsException.class,()-> movieService.create(movie));
    }

    @Test
    void deleteName() {
        try{
            movieService.deleteName("friends");
        } catch (NotFound e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows( NotFound.class,()-> movieService.findByName("friends"));
    }

    @Test
    void findByGenre() {
        String name;
        try {
            name= movieService.findByGenre("action",0,3).get(0).getName();
        } catch (NoResultsException e) {
            throw new RuntimeException(e);
        } catch (ExceedsLimitException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(name.equals("the boys"));
    }

    @Test
    void findByGenreNoResultsException(){
        Assertions.assertThrows(NoResultsException.class,()->{
            movieService.findByGenre("romantic",0,3);});
    }

    @Test
    void findByGenreExceedsLimitException(){
        Assertions.assertThrows(ExceedsLimitException.class,()-> movieService.findByGenre("action",0,13));
    }
}