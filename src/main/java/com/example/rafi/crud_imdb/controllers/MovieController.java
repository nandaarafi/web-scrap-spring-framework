package com.example.rafi.crud_imdb.controllers;

import com.example.rafi.crud_imdb.DTOs.MovieDTO;
import com.example.rafi.crud_imdb.models.Movie;
import com.example.rafi.crud_imdb.payloads.responses.BodyResponse;

import com.example.rafi.crud_imdb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")  // Change the endpoint to reflect movies
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Helper method to convert Model to DTO
    private MovieDTO convertToDTO(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getYear(), movie.getRating());
    }

    // Get all movies
    @GetMapping
    public ResponseEntity<BodyResponse<List<MovieDTO>>> getAllMovies() {
        List<MovieDTO> movieDTOS = movieService.getAllMovies()  // Change to getAllMovies()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        BodyResponse<List<MovieDTO>> response = new BodyResponse<>("success", HttpStatus.OK.value(), "Movies retrieved", movieDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<BodyResponse<MovieDTO>> getMovieById(@PathVariable Long id) {
        Optional<Movie> movieOpt = movieService.getMovieById(id); // Change to getMovieById()

        if (movieOpt.isPresent()) {
            MovieDTO movieDTO = convertToDTO(movieOpt.get());
            BodyResponse<MovieDTO> response = new BodyResponse<>("success", HttpStatus.OK.value(), "Movie retrieved", movieDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BodyResponse<MovieDTO> response = new BodyResponse<>("error", HttpStatus.NOT_FOUND.value(), "Movie not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Create new movie
    @PostMapping
    public ResponseEntity<BodyResponse<MovieDTO>> addMovie(@RequestBody MovieDTO movieDTO) {
        // Ensure that movieDTO is not null and has a valid title
        System.out.println("Received movieDTO: " + movieDTO); // Add this line

        if (movieDTO.getTitle() == null || movieDTO.getTitle().isEmpty()) {
            return new ResponseEntity<>(new BodyResponse<>("error", HttpStatus.BAD_REQUEST.value(), "Title is required", null), HttpStatus.BAD_REQUEST);
        }

        Movie movie = new Movie(null, movieDTO.getTitle(), movieDTO.getYear(), movieDTO.getRating());
        Movie savedMovie = movieService.addMovie(movie);

        MovieDTO savedMovieDTO = convertToDTO(savedMovie);
        BodyResponse<MovieDTO> response = new BodyResponse<>("success", HttpStatus.CREATED.value(), "Movie created", savedMovieDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update existing movie
    @PutMapping("/{id}")
    public ResponseEntity<BodyResponse<MovieDTO>> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        Optional<Movie> existingMovieOpt = movieService.getMovieById(id); // Change to getMovieById()

        if (existingMovieOpt.isPresent()) {
            Movie updatedMovie = new Movie(id, movieDTO.getTitle(), movieDTO.getYear(), movieDTO.getRating()); // Use Movie DTO properties
            Movie savedMovie = movieService.updateMovie(id, updatedMovie); // Change to updateMovie()

            MovieDTO savedMovieDTO = convertToDTO(savedMovie);
            BodyResponse<MovieDTO> response = new BodyResponse<>("success", HttpStatus.OK.value(), "Movie updated", savedMovieDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BodyResponse<MovieDTO> response = new BodyResponse<>("error", HttpStatus.NOT_FOUND.value(), "Movie not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Delete movie by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<BodyResponse<Void>> deleteMovie(@PathVariable Long id) {
        Optional<Movie> movieOpt = movieService.getMovieById(id); // Change to getMovieById()

        if (movieOpt.isPresent()) {
            movieService.deleteMovie(id); // Change to deleteMovie()
            BodyResponse<Void> response = new BodyResponse<>("success", HttpStatus.NO_CONTENT.value(), "Movie deleted", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            BodyResponse<Void> response = new BodyResponse<>("error", HttpStatus.NOT_FOUND.value(), "Movie not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
