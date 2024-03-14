package com.j0k3r.movies.utils;

import com.j0k3r.movies.dao.IGenderDao;
import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.MovieException;
import com.j0k3r.movies.http.request.MovieRequest;
import com.j0k3r.movies.http.response.MovieResponse;
import com.j0k3r.movies.models.Actor;
import com.j0k3r.movies.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieUtils {

    @Autowired
    private IGenderDao genderDao;

    @Autowired
    private ActorUtils actorUtils;

    public Movie mappingMovieRequestToMovie(MovieRequest movieRequest) throws GenderException, RuntimeException {

        return Movie.builder()
                .tittle(movieRequest.getTittle())
                .gender(genderDao.findGenderById(movieRequest.getIdGender()))
                .actors(actorUtils.listActorsByIdsList(movieRequest.getListIdActors()))
                .build();
    }

    public MovieResponse mappingMovieToMovieRequest(Movie movie){

        return MovieResponse.builder()
                .title(movie.getTittle())
                .gender(movie.getGender().getName())
                .actors(actorUtils.mappingActorsToActorsRequests(movie.getActors()))
                .releaseDate(movie.getReleaseDate())
                .build();
    }

    public Movie updateMovieToMovieRequest(Movie movie, MovieRequest movieRequest) throws GenderException {
        movie.setTittle(movieRequest.getTittle());
        movie.setGender(genderDao.findGenderById(movieRequest.getIdGender()));
        movie.setActors(new ArrayList<>(actorUtils.listActorsByIdsList(movieRequest.getListIdActors())));
        return movie;
    }

    public List<MovieResponse> mappingMoviesToMoviesRequests(List<Movie> movies){
        if (movies.isEmpty()) return List.of();
        return movies.stream().map(
                this::mappingMovieToMovieRequest
        ).toList();
    }

    public void validateMovieRequest(MovieRequest movieRequest) throws MovieException {
        if (movieRequest.getTittle() == null || movieRequest.getTittle().isEmpty()) {
            throw new MovieException("Tittle's movie is required",445);
        }
        if (movieRequest.getIdGender() == null || movieRequest.getIdGender() <=0){
            throw new MovieException("IdGender's is required",4456);
        }
        movieRequest.getListIdActors().forEach(
                (idMovie) -> {
                    if (idMovie == null || idMovie <= 0) try {
                        throw new MovieException("All idActor's are required",447);
                    } catch (MovieException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

}
