package com.example.suggestions.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
public class NetflixGenre {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    public NetflixGenre() {
    }
}
