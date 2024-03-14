package com.j0k3r.movies.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequest {

    private String tittle;

    private Long idGender;

    private List<Long> listIdActors;
}
