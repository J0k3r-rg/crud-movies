package com.j0k3r.movies.services.impl;

import com.j0k3r.movies.dao.IMovieDao;
import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.MovieException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.request.MovieRequest;
import com.j0k3r.movies.http.response.MovieResponse;
import com.j0k3r.movies.models.Movie;
import com.j0k3r.movies.services.IMovieService;
import com.j0k3r.movies.utils.MovieUtils;
import com.j0k3r.movies.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieDao movieDao;

    @Autowired
    private MovieUtils movieUtils;

    @Override
    public Movie saveMovie(MovieRequest movieRequest) throws MovieException, GenderException, RuntimeException {
        movieUtils.validateMovieRequest(movieRequest);
        if (movieDao.getMovieByTittle(movieRequest.getTittle()).isPresent())
            throw new MovieException("The tittle movie is repeat", 448);
        return movieDao.saveMovie(movieUtils.mappingMovieRequestToMovie(movieRequest));
    }

    @Override
    public MovieResponse findMovieById(Long id) throws PropertiesException, MovieException {
        Utils.validateIdLong(id);
        Movie movie = movieDao.getMovieById(id);
        return movieUtils.mappingMovieToMovieRequest(movie);
    }

    @Override
    public List<MovieResponse> findAllMovies() {
        return movieUtils.mappingMoviesToMoviesRequests(movieDao.getAllMovies());
    }

    @Override
    public Movie deleteMovieById(Long id) throws MovieException {
        Movie movie = movieDao.getMovieById(id);
        movieDao.deleteMovie(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(Long id, MovieRequest movieRequest) throws MovieException, GenderException, PropertiesException,RuntimeException {
        Utils.validateIdLong(id);
        Movie movie = movieDao.getMovieById(id);
        movieUtils.validateMovieRequest(movieRequest);
        return movieDao.updateMovie(movieUtils.updateMovieToMovieRequest(movie,movieRequest));
    }
}
