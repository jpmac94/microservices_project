package com.example.demo.clients;

import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.SerieC;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "serie-service")
public interface SerieFeignClient {

    @GetMapping("series/list")
    public ResponseEntity<List<SerieC>> li(@RequestParam("from") int from, @RequestParam("to") int to) throws NoResultsException, ExceedsLimitException;

    @GetMapping("series/findByName/{name}")
    public ResponseEntity<SerieC> findByName(@PathVariable String name) throws NotFound;

    @GetMapping("series/findByGenre/{genre}")
    public ResponseEntity<List<SerieC>> findByGenre(@PathVariable String genre,@RequestParam("from") int from,@RequestParam("to") int to) throws NoResultsException, ExceedsLimitException;
}
