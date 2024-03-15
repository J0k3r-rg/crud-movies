package com.j0k3r.movies;

import com.j0k3r.movies.dao.IRoleDao;
import com.j0k3r.movies.http.request.RoleRequest;
import com.j0k3r.movies.http.request.UserRequest;
import com.j0k3r.movies.services.IRoleService;
import com.j0k3r.movies.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Bean
	public CommandLineRunner startup() {
		return args -> {
			roleService.saveRole(
					RoleRequest.builder()
							.role("ADMIN")
							.build()
			);

			userService.saveUser(
					UserRequest.builder()
							.username("joker")
							.password("123456")
							.idRoles(List.of(1L))
						.build()
        	);

			userService.saveUser(
					UserRequest.builder()
							.username("queen")
							.password("123456")
							.idRoles(List.of(1L))
							.build()
			);
		};
	}

}
