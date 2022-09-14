package com.dh.series.service;

import com.dh.series.exceptions.AlreadyExistsException;
import com.dh.series.exceptions.ExceedsLimitException;
import com.dh.series.exceptions.NoResultsException;
import com.dh.series.exceptions.NotFound;
import com.dh.series.model.Serie;

import java.util.List;

public interface SerieCRUDService<T> {

    public T create(T object) throws AlreadyExistsException;
    public T update(Serie object, String name) throws NotFound;
    public List<T> list(int from, int to) throws ExceedsLimitException, NoResultsException;
    public T deleteName(String name) throws NotFound;
    public T findByName(String name) throws NotFound;

    public List<T> findByGenre(String genre,int from, int to) throws ExceedsLimitException, NoResultsException;
}
