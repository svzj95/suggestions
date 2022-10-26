package com.example.suggestions.controller.v1;

import com.example.suggestions.dao.v1.GenreDao;
import com.example.suggestions.dao.v1.SuggestionDao;
import com.example.suggestions.model.Suggestion;
import com.example.suggestions.service.IMDBSuggestionServiceImpl;
import com.example.suggestions.service.StoredSuggestionService;
import com.example.suggestions.service.NetflixSuggestionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class SuggestionController {

    private final StoredSuggestionService storedSuggestionService;
    private final NetflixSuggestionServiceImpl netflixSuggestionService;
    private final IMDBSuggestionServiceImpl imdbSuggestionService;

    public SuggestionController(NetflixSuggestionServiceImpl netflixSuggestionService,
                                IMDBSuggestionServiceImpl imdbSuggestionService,
                                StoredSuggestionService storedSuggestionService) {
        this.storedSuggestionService = storedSuggestionService;
        this.netflixSuggestionService = netflixSuggestionService;
        this.imdbSuggestionService = imdbSuggestionService;
    }

    @PostMapping("/suggestions/netflix")
    public ResponseEntity<List<SuggestionDao>> getSuggestionsByNetflixGenres(@RequestBody GenreDao genreDao) {
        List<Suggestion> suggestions = storedSuggestionService.findByNetflixSuggestions(genreDao.getGenres());
        if (suggestions.isEmpty()) {
            suggestions = netflixSuggestionService.findByGenres(genreDao.getGenres());
            if(!suggestions.isEmpty()) {
                suggestions = storedSuggestionService.saveAll(suggestions);
            }
        }

        return new ResponseEntity<>(suggestions.stream().map(this::mapSuggestionToSuggestionDao).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/suggestions/imdb")
    public ResponseEntity<List<SuggestionDao>> getSuggestionsByIMDBGenres(@RequestBody GenreDao genreDao) {
        List<Suggestion> suggestions = storedSuggestionService.findByIMDBSuggestions(genreDao.getGenres());
        if (suggestions.isEmpty()) {
            suggestions = imdbSuggestionService.findByGenres(genreDao.getGenres());
            if(!suggestions.isEmpty()) {
                suggestions = storedSuggestionService.saveAll(suggestions);
            }
        }

        return new ResponseEntity<>(suggestions.stream().map(this::mapSuggestionToSuggestionDao).collect(Collectors.toList()), HttpStatus.OK);
    }

    private SuggestionDao mapSuggestionToSuggestionDao(Suggestion suggestion) {
        return new SuggestionDao(suggestion.getTitle(), suggestion.getYear());
    }
}
