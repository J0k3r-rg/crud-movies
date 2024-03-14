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
public class UserRequest {

    private String username;

    private String password;

    private List<Long> idRoles;

    @Builder.Default
    private Boolean activate=false;
}
