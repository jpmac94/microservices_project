package com.example.demo.service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;

import java.util.List;

public interface MovieCRUDService<T> {

    public T create(T object) throws AlreadyExistsException;
    public T update(T object,String name) throws NotFound;
    public List<T> list(int from, int to) throws ExceedsLimitException, NoResultsException;
    public T deleteName(String name) throws NotFound;
    public T findByName(String name) throws NotFound;

    public List<T> findByGenre(String genre,int from, int to) throws ExceedsLimitException, NoResultsException;
}
