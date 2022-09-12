package com.proyectointegrador.seriesservice.controller;

import com.proyectointegrador.seriesservice.exceptions.AlreadyExistsException;
import com.proyectointegrador.seriesservice.exceptions.ExceedsLimitException;
import com.proyectointegrador.seriesservice.exceptions.NoResultsException;
import com.proyectointegrador.seriesservice.exceptions.NotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SerieCRUDController<T> {

    public ResponseEntity<T> create(T object) throws AlreadyExistsException;
    public ResponseEntity<T> up(T object,String name) throws NotFound;
    public ResponseEntity<List<T>> li(int from, int to) throws NoResultsException, ExceedsLimitException;
    public ResponseEntity<T> deleteName(String name) throws NotFound;
    public ResponseEntity<T> findByName(String name) throws NotFound;
}
