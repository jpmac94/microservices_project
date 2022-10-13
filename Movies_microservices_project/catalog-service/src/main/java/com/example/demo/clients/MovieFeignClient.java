package com.example.demo.clients;

import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.MovieC;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "movies")
public interface MovieFeignClient {

    @GetMapping("movies/list")
    public ResponseEntity<List<MovieC>> li(@RequestParam("from") int from, @RequestParam("to") int to) throws NoResultsException, ExceedsLimitException;

    @GetMapping("movies/findByName/{name}")
    public ResponseEntity<MovieC> findByName(@PathVariable String name) throws NotFound;

    @GetMapping("movies/findByGenre/{genre}")
    public ResponseEntity<List<MovieC>> findByGenre(@PathVariable String genre,@RequestParam("from") int from,@RequestParam("to") int to) throws NoResultsException, ExceedsLimitException;
}
