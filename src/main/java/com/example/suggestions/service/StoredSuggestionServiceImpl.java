package com.example.suggestions.service;

import com.example.suggestions.model.Suggestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoredSuggestionServiceImpl implements StoredSuggestionService {
    @Override
    public List<Suggestion> findByNetflixSuggestions(List<String> genres) {
        return null;
    }

    @Override
    public List<Suggestion> findByIMDBSuggestions(List<String> genres) {
        return null;
    }

    @Override
    public List<Suggestion> saveAll(List<Suggestion> suggestions) {
        return null;
    }
}
