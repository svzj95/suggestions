package com.example.suggestions.service;

import com.example.suggestions.model.Suggestion;

import java.util.List;

public interface SuggestionService {

    List<Suggestion> findByGenres(List<String> genres);
}
