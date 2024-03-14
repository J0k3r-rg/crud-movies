package com.j0k3r.movies.dao;

import com.j0k3r.movies.exceptions.MovieException;
import com.j0k3r.movies.models.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieDao {

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    Movie getMovieById(Long id) throws MovieException;

    List<Movie> getAllMovies();

    void deleteMovie(Movie movie);

    Optional<Movie> getMovieByTittle(String tittle);
}
