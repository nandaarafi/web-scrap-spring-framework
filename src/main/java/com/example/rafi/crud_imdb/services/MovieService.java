package com.example.rafi.crud_imdb.services;

import com.example.rafi.crud_imdb.models.Movie;
import com.example.rafi.crud_imdb.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(long id) {
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(long id, Movie updatedMovie) {
        updatedMovie.setId(id);
        return movieRepository.save(updatedMovie);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }
}
