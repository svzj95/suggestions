package com.example.suggestions.service;

import com.example.suggestions.model.Suggestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetflixSuggestionServiceImpl implements SuggestionService {
    @Override
    public List<Suggestion> findByGenres(List<String> genres) {
        return null;
    }
}
