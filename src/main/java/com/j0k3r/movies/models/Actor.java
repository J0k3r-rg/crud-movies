package com.j0k3r.movies.models;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "actors")
public class Actor implements Serializable{

    @Serial
    private static final long serialVersionUID = 12412412412423232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "url_image")
    private String urlImage;

}
