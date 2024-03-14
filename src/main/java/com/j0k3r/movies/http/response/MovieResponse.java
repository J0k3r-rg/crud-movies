package com.j0k3r.movies.http.response;

import com.j0k3r.movies.http.request.ActorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {

    private String title;

    private String gender;

    private List<ActorRequest> actors;

    private Date releaseDate;

}
