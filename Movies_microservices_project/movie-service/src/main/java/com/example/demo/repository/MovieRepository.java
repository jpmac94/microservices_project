package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Optional<Movie> findByName(String name);

    Page findByGenre(String genre, PageRequest pageRequest);
}
