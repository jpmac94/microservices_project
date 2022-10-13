package com.dh.series.repository;

import com.dh.series.model.Serie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerieRepository extends MongoRepository<Serie,Integer> {

    Optional<Serie> findByName(String name);

    Page findByGenre(String genre, PageRequest pageRequest);
}
