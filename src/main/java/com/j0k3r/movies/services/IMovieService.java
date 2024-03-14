package com.j0k3r.movies.services;

import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.MovieException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.request.MovieRequest;
import com.j0k3r.movies.http.response.MovieResponse;
import com.j0k3r.movies.models.Movie;

import java.util.List;

public interface IMovieService {

    Movie saveMovie(MovieRequest movie) throws MovieException, GenderException;

    MovieResponse findMovieById(Long id) throws PropertiesException, MovieException;

    List<MovieResponse> findAllMovies();

    Movie deleteMovieById(Long id) throws MovieException;

    Movie updateMovie(Long id, MovieRequest movieRequest) throws MovieException, GenderException, PropertiesException;

}
