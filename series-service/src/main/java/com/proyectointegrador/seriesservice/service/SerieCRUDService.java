package com.proyectointegrador.seriesservice.service;

import com.proyectointegrador.seriesservice.exceptions.AlreadyExistsException;
import com.proyectointegrador.seriesservice.exceptions.ExceedsLimitException;
import com.proyectointegrador.seriesservice.exceptions.NoResultsException;
import com.proyectointegrador.seriesservice.exceptions.NotFound;
import com.proyectointegrador.seriesservice.model.Serie;

import java.util.List;

public interface SerieCRUDService<T> {

    public T create(T object) throws AlreadyExistsException;
    public T update(Serie object,String name) throws NotFound;
    public List<T> list(int from, int to) throws ExceedsLimitException, NoResultsException;
    public T deleteName(String name) throws NotFound;
    public T findByName(String name) throws NotFound;
}
