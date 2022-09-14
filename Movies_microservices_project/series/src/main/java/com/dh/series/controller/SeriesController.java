package com.dh.series.controller;

import com.dh.series.exceptions.AlreadyExistsException;
import com.dh.series.exceptions.ExceedsLimitException;
import com.dh.series.exceptions.NoResultsException;
import com.dh.series.exceptions.NotFound;
import com.dh.series.model.Serie;
import com.dh.series.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("series")
public class SeriesController implements SerieCRUDController<Serie> {

    private SerieService serieService;

    @Autowired
    public SeriesController(SerieService serieService){
        this.serieService=serieService;
    }


    @Override
    @PostMapping("/create")
    public ResponseEntity<Serie> create(@RequestBody Serie object) throws AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serieService.create(object));
    }

    @Override
    @PutMapping("/update/{name}")
    public ResponseEntity<Serie> up(@RequestBody Serie object,@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(serieService.update(object,name));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<Serie>> li(@RequestParam("from") int from, @RequestParam("to") int to) throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(serieService.list(from,to));
    }

    @Override
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Serie> deleteName(@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(serieService.deleteName(name));
    }

    @Override
    @GetMapping("/findByName/{name}")
    public ResponseEntity<Serie> findByName(@PathVariable String name) throws NotFound {
        return ResponseEntity.ok(serieService.findByName(name));
    }

    @Override
    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<List<Serie>> findByGenre(@PathVariable String genre,@RequestParam("from") int from,@RequestParam("to") int to) throws NoResultsException, ExceedsLimitException {
        return ResponseEntity.ok(serieService.findByGenre(genre, from, to));
    }


}