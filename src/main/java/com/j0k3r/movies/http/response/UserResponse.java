package com.j0k3r.movies.http.response;

import com.j0k3r.movies.http.request.RoleRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String username;

    private List<RoleRequest> idRoles;

    private Boolean active;

}
