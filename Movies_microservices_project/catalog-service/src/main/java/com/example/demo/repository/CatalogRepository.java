package com.example.demo.repository;

import com.example.demo.model.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog,Integer> {

    Catalog findByGenre(String genre);
}
