package com.proyectointegrador.seriesservice.controller;


import com.proyectointegrador.seriesservice.exceptions.AlreadyExistsException;
import com.proyectointegrador.seriesservice.exceptions.ExceedsLimitException;
import com.proyectointegrador.seriesservice.exceptions.NoResultsException;
import com.proyectointegrador.seriesservice.exceptions.NotFound;
import com.proyectointegrador.seriesservice.model.Serie;
import com.proyectointegrador.seriesservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<List<Serie>> li(@RequestParam("from") int from,@RequestParam("to") int to) throws NoResultsException, ExceedsLimitException {
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
}
