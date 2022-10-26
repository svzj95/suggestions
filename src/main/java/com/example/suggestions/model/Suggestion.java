package com.example.suggestions.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Suggestion {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long year;

    @ManyToMany
    private List<NetflixGenre> netflixGenres;
    @ManyToMany
    private List<IMDBGenre> imdbGenres;

    public Suggestion() {

    }
}
