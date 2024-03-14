package com.j0k3r.movies.controllers;

import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.MovieException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.request.MovieRequest;
import com.j0k3r.movies.http.response.MovieResponse;
import com.j0k3r.movies.models.Movie;
import com.j0k3r.movies.services.IMovieService;
import com.j0k3r.movies.utils.ErrorResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id){
        MovieResponse movie;
        try {
            movie = movieService.findMovieById(id);
        } catch (PropertiesException | MovieException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400,e.getMessage())
            );
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody MovieRequest movieRequest){
        Movie movie;

        try {
            movie = movieService.saveMovie(movieRequest);
        } catch (MovieException | GenderException | RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400,e.getMessage())
            );
        }
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest){
        Movie movie;
        try {
            movie = movieService.updateMovie(id, movieRequest);
        } catch (MovieException | GenderException | PropertiesException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400, e.getMessage())
            );
        }
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id){
        Movie movie;
        try {
            movie = movieService.deleteMovieById(id);
        } catch (MovieException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400, e.getMessage())
            );
        }
        return ResponseEntity.ok(movie);
    }

}
