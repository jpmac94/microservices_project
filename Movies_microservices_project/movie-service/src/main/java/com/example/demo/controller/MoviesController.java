package com.example.demo.controller;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MoviesController implements MovieCRUDController<Movie> {

    private MovieService movieService;

    @Autowired
    public MoviesController(MovieService movieService){
        this.movieService=movieService;
    }


    @Override
    @PostMapping("/create")
    public ResponseEntity<Movie> create(@RequestBody Movie object) throws AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieService.create(object));
    }

    @Override
    @PutMapping("/update/{name}")
    public ResponseEntity<Movie> up(@RequestBody Movie object,@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(movieService.update(object,name));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<Movie>> li(@RequestParam("from") int from, @RequestParam("to") int to) throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(movieService.list(from,to));
    }

    @Override
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Movie> deleteName(@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(movieService.deleteName(name));
    }

    @Override
    @GetMapping("/findByName/{name}")
    public ResponseEntity<Movie> findByName(@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(movieService.findByName(name));
    }

    @Override
    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<List<Movie>> findByGenre(@PathVariable String genre,@RequestParam("from") int from,@RequestParam("to") int to) throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(movieService.findByGenre(genre, from, to));
    }


}
