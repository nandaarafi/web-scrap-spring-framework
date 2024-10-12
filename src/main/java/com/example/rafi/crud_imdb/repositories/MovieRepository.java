package com.example.rafi.crud_imdb.repositories;

import com.example.rafi.crud_imdb.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
