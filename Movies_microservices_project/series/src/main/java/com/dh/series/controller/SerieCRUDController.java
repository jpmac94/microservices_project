package com.dh.series.controller;

import com.dh.series.exceptions.AlreadyExistsException;
import com.dh.series.exceptions.ExceedsLimitException;
import com.dh.series.exceptions.NoResultsException;
import com.dh.series.exceptions.NotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SerieCRUDController<T> {

    public ResponseEntity<T> create(T object) throws AlreadyExistsException;
    public ResponseEntity<T> up(T object,String name) throws NotFound;
    public ResponseEntity<List<T>> li(int from, int to) throws NoResultsException, ExceedsLimitException;
    public ResponseEntity<T> deleteName(String name) throws NotFound;
    public ResponseEntity<T> findByName(String name) throws NotFound;

    public ResponseEntity<List<T>> findByGenre(String genre,int from,int to) throws NoResultsException, ExceedsLimitException;
}
