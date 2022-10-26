package com.example.suggestions.controller.v1;

import com.example.suggestions.model.IMDBGenre;
import com.example.suggestions.model.NetflixGenre;
import com.example.suggestions.model.Suggestion;
import com.example.suggestions.service.IMDBSuggestionServiceImpl;
import com.example.suggestions.service.NetflixSuggestionServiceImpl;
import com.example.suggestions.service.StoredSuggestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SuggestionController.class)
@ExtendWith(SpringExtension.class)
public class SuggestionControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NetflixSuggestionServiceImpl netflixService;

    @MockBean
    IMDBSuggestionServiceImpl imdbService;

    @MockBean
    StoredSuggestionService storedSuggestionService;

    @Test
    void shouldReturnEmptyByNonRealNetflixGenre() throws Exception {
        List<String> genres = Collections.singletonList("NotExist");
        Mockito.when(netflixService.findByGenres(genres)).thenReturn(Collections.emptyList());
        Mockito.when(storedSuggestionService.findByNetflixSuggestions(genres)).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/api/v1/suggestions/netflix").contentType(MediaType.APPLICATION_JSON).content("{ \"genres\": [\"NotExist\"]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldReturnOneFromStoredByNetflixGenre() throws Exception {
        List<String> genres = Collections.singletonList("comedy");
        Mockito.when(netflixService.findByGenres(genres)).thenReturn(Collections.emptyList());

        NetflixGenre genre = new NetflixGenre();
        genre.setId(0L);
        genre.setTitle("comedy");
        Suggestion suggestion = new Suggestion();
        suggestion.setTitle("Austin Powers");
        suggestion.setNetflixGenres(Collections.singletonList(genre));

        Mockito.when(storedSuggestionService.findByNetflixSuggestions(genres)).thenReturn(Collections.singletonList(suggestion));

        mockMvc.perform(post("/api/v1/suggestions/netflix").contentType(MediaType.APPLICATION_JSON).content("{ \"genres\": [\"comedy\"]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].title").value("Austin Powers"));
    }

    @Test
    void shouldReturnOneFromNetflixByNetflixGenre() throws Exception {
        List<String> genres = Collections.singletonList("comedy");

        NetflixGenre genre = new NetflixGenre();
        genre.setId(0L);
        genre.setTitle("comedy");
        Suggestion suggestion = new Suggestion();
        suggestion.setTitle("Austin Powers");
        suggestion.setNetflixGenres(Collections.singletonList(genre));

        Mockito.when(netflixService.findByGenres(genres)).thenReturn(Collections.singletonList(suggestion));
        Mockito.when(storedSuggestionService.findByNetflixSuggestions(genres)).thenReturn(Collections.emptyList());
        Mockito.when(storedSuggestionService.saveAll(Collections.singletonList(suggestion))).thenReturn(Collections.singletonList(suggestion));

        mockMvc.perform(post("/api/v1/suggestions/netflix").contentType(MediaType.APPLICATION_JSON).content("{ \"genres\": [\"comedy\"]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].title").value("Austin Powers"));
    }

    @Test
    void shouldReturnEmptyByNonRealIMDBGenre() throws Exception {
        List<String> genres = Collections.singletonList("NotExist");
        Mockito.when(imdbService.findByGenres(genres)).thenReturn(Collections.emptyList());
        Mockito.when(storedSuggestionService.findByNetflixSuggestions(genres)).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/api/v1/suggestions/imdb").contentType(MediaType.APPLICATION_JSON).content("{ \"genres\": [\"NotExist\"]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldReturnOneFromStoredByIMDBGenre() throws Exception {
        List<String> genres = Collections.singletonList("comedy");
        Mockito.when(imdbService.findByGenres(genres)).thenReturn(Collections.emptyList());

        IMDBGenre genre = new IMDBGenre();
        genre.setId(0L);
        genre.setTitle("comedy");
        Suggestion suggestion = new Suggestion();
        suggestion.setTitle("Austin Powers");
        suggestion.setImdbGenres(Collections.singletonList(genre));

        Mockito.when(storedSuggestionService.findByIMDBSuggestions(genres)).thenReturn(Collections.singletonList(suggestion));

        mockMvc.perform(post("/api/v1/suggestions/imdb").contentType(MediaType.APPLICATION_JSON).content("{ \"genres\": [\"comedy\"]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].title").value("Austin Powers"));
    }

    @Test
    void shouldReturnOneFromIMDBByIMDBGenre() throws Exception {
        List<String> genres = Collections.singletonList("comedy");

        IMDBGenre genre = new IMDBGenre();
        genre.setId(0L);
        genre.setTitle("comedy");
        Suggestion suggestion = new Suggestion();
        suggestion.setTitle("Austin Powers");
        suggestion.setImdbGenres(Collections.singletonList(genre));

        Mockito.when(imdbService.findByGenres(genres)).thenReturn(Collections.singletonList(suggestion));
        Mockito.when(storedSuggestionService.findByIMDBSuggestions(genres)).thenReturn(Collections.emptyList());
        Mockito.when(storedSuggestionService.saveAll(Collections.singletonList(suggestion))).thenReturn(Collections.singletonList(suggestion));

        mockMvc.perform(post("/api/v1/suggestions/imdb").contentType(MediaType.APPLICATION_JSON).content("{ \"genres\": [\"comedy\"]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].title").value("Austin Powers"));
    }
}
