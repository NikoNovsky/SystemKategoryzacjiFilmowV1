package com.example.currenda.web.controller;

import com.example.currenda.async.WrapperMovieAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final ObjectMapper mapper;

    public SearchController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping()
    public String getSearch(Model model, @RequestParam(required = false) String query) {
        try {
            if (query != null && !query.isBlank()) {
                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&include_adult=false&language=en-US"))
                        .GET()
                        .header("accept", "application/json")
                        .header("Authorization", "Bearer " + System.getenv("TMDB_API_TOKEN"))
                        .build();

                HttpResponse<String> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

                WrapperMovieAsync wrapperMovieAsync = mapper.readValue(response.body(), WrapperMovieAsync.class);

                model.addAttribute("movies", wrapperMovieAsync.getResults());
                model.addAttribute("test", "Hello World");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }
}
