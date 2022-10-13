package com.example.demo.controller;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieCRUDController<T> {

    public ResponseEntity<T> create(T object) throws AlreadyExistsException;
    public ResponseEntity<T> up(T object,String name) throws NotFound;
    public ResponseEntity<List<T>> li(int from, int to) throws NoResultsException, ExceedsLimitException;
    public ResponseEntity<T> deleteName(String name) throws NotFound;
    public ResponseEntity<T> findByName(String name) throws NotFound;

    public ResponseEntity<List<T>> findByGenre(String genre,int from,int to) throws NoResultsException, ExceedsLimitException;
}
