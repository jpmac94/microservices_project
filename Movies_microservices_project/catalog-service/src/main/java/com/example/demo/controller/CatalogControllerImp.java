package com.example.demo.controller;

import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.MovieC;
import com.example.demo.model.SerieC;
import com.example.demo.service.CatalogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogControllerImp implements CatalogController{

    private CatalogServiceImp catalogServiceImp;

    @Autowired
    public CatalogControllerImp(CatalogServiceImp catalogServiceImp) {
        this.catalogServiceImp = catalogServiceImp;
    }

    @Override
    @GetMapping("movies/list")
    public ResponseEntity<List<MovieC>> listMovies(@RequestParam("from") int from, @RequestParam("to") int to) throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(catalogServiceImp.listMovies(from, to));
    }

    @Override
    @GetMapping("movies/findByName/{name}")
    public ResponseEntity<MovieC> findMovieByName(@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(catalogServiceImp.findMovieByName(name));
    }

    @Override
    @GetMapping("movies/findByGenre/{genre}")
    public ResponseEntity<List<MovieC>> findMoviesByGenre(@PathVariable String genre,@RequestParam("from") int from,@RequestParam("to") int to)throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(catalogServiceImp.findMoviesByGenre(genre, from, to));
    }

    @Override
    @GetMapping("series/list")
    public ResponseEntity<List<SerieC>> listSeries(@RequestParam("from") int from, @RequestParam("to") int to) throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(catalogServiceImp.listSeries(from, to));
    }

    @Override
    @GetMapping("series/findByName/{name}")
    public ResponseEntity<SerieC> findSerieByName(@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(catalogServiceImp.findSerieByName(name));
    }

    @Override
    @GetMapping("series/findByGenre/{genre}")
    public ResponseEntity<List<SerieC>> findSerieByGenre(@PathVariable String genre,@RequestParam("from") int from,@RequestParam("to") int to)  throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(catalogServiceImp.findSerieByGenre(genre, from, to));
    }
}
