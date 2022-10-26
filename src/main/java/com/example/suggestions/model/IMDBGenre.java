package com.example.suggestions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
public class IMDBGenre {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    public IMDBGenre() {

    }
}
