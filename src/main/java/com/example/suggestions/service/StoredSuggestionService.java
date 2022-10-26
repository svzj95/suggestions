package com.example.suggestions.service;

import com.example.suggestions.model.Suggestion;

import java.util.List;

public interface StoredSuggestionService {
    List<Suggestion> findByNetflixSuggestions(List<String> genres);

    List<Suggestion> findByIMDBSuggestions(List<String> genres);

    List<Suggestion> saveAll(List<Suggestion> suggestions);
}
