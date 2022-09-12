package com.proyectointegrador.seriesservice.repository;

import com.proyectointegrador.seriesservice.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerieRepository extends MongoRepository<Serie,Integer> {

    Optional<Serie> findByName(String name);
}
