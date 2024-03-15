package com.j0k3r.movies.filters;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j0k3r.movies.dao.IUserDao;
import com.j0k3r.movies.exceptions.UserException;
import com.j0k3r.movies.models.UserEntity;
import com.j0k3r.movies.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final IUserDao userDao;

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils,IUserDao userDao) {
        this.jwtUtils = jwtUtils;
        this.userDao = userDao;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        UserEntity userEntity;
        String username;
        String password;

        try {
            if (request.getInputStream().available() == 0) {
                throw new BadCredentialsException("Empty request body");
            }

            userEntity = new ObjectMapper()
                    .readValue(request.getInputStream(), UserEntity.class);
            username = userEntity.getUsername();
            password = userEntity.getPassword();
        }  catch (JsonMappingException e) {
            throw new BadCredentialsException("Invalid JSON format");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        User userDetails = (User) authResult.getPrincipal();

        UserEntity user;
        try {
            user = userDao.getUserByUsername(userDetails.getUsername());
        } catch (UserException e) {
            user = null;
        }

        if (user != null) {
            String token = jwtUtils.generatedAccesToken(userDetails.getUsername());

            if (user.getEnable()) {

                response.addHeader("Authorization", "Bearer " + token);

                Map<String, Object> httpResponse = new HashMap<>();

                httpResponse.put("token", token);
                httpResponse.put("message", "success login");
                httpResponse.put("username", userDetails.getUsername());
                httpResponse.put("roles", user.getRoles().stream().toList());

                response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
                response.setStatus(HttpStatus.OK.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().flush();
            }

        }

        super.successfulAuthentication(request, response, chain, authResult);

    }

}
