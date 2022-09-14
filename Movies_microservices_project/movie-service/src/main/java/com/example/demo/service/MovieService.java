package com.example.demo.service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovieService implements MovieCRUDService<Movie>{

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    @Override
    public Movie create(Movie movie) throws AlreadyExistsException {

        if (exists(movie.getName())){
            throw new AlreadyExistsException("this element already exists");
        }
        return movieRepository.save(movie);
    }

    private boolean exists(String name){
        return !movieRepository.findByName(name).isEmpty();
    }

    @Override
    public Movie update(Movie object, String name) throws NotFound {
        object.setId(findByName(name).getId());
        return movieRepository.save(object);
    }

    @Override
    public List<Movie> list(int from, int to) throws ExceedsLimitException, NoResultsException {
        if(to-from>8) {
            throw new ExceedsLimitException("can't ask for more than 8 elements");
        }

        Page page = movieRepository.findAll(PageRequest.of(from, to));

        if (page.isEmpty()) {
            throw new NoResultsException("coudldn't find any results");
        }

        return page.toList();
    }

    @Override
    public Movie deleteName(String name) throws NotFound{
        Movie movie=findByName(name);
        movieRepository.deleteById(movie.getId());
        return movie;
    }

    @Override
    public Movie findByName(String name) throws NotFound{
        return movieRepository.findByName(name).orElseThrow(()->new NotFound("no movie has been found with that name"));
    }

    @Override
    public List<Movie> findByGenre(String genre,int from,int to) throws ExceedsLimitException, NoResultsException {
        if(to-from>8) {
            throw new ExceedsLimitException("can't ask for more than 8 elements");
        }

        Page page = movieRepository.findByGenre(genre,PageRequest.of(from,to));

        if (page.isEmpty()) {
            throw new NoResultsException("coudldn't find any results");
        }

        return page.toList();

    }

}
