package com.j0k3r.movies.dao.impl;

import com.j0k3r.movies.dao.IMovieDao;
import com.j0k3r.movies.exceptions.MovieException;
import com.j0k3r.movies.models.Movie;
import com.j0k3r.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieDao implements IMovieDao {

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(Long id) throws MovieException {
        return movieRepository.findById(id).orElseThrow(
                () -> new MovieException("This id movie do not exist",444)
        );
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public Optional<Movie> getMovieByTittle(String tittle) {
        return movieRepository.findByTittle(tittle);
    }

}
