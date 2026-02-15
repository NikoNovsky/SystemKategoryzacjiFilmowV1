package com.example.currenda.web.service;

import com.example.currenda.async.WrapperMovieAsync;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class MovieService {
    private final ObjectMapper mapper;

    public MovieService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public WrapperMovieAsync getWrapperMovieAsync(String movie, Model model) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + URLEncoder.encode(movie, StandardCharsets.UTF_8) + "&include_adult=false&language=en-US"))
                .GET()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + System.getenv("TMDB_API_TOKEN"))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            model.addAttribute("error", "Coś poszło nie tak, skontaktuj się z administratorem albo zapłać 5zł i spróbuj ponownie :) Albo po prostu utwórz konto na https://developer.themoviedb.org/reference/getting-started i utwórz zmienną środowiskową pod nazwą TMDB_API_TOKEN i ustaw w niej token");
        }

        return mapper.readValue(response.body(), WrapperMovieAsync.class);
    }
}
