package com.example.currenda.web.service;

import com.example.currenda.async.WrapperMovieAsync;
import org.springframework.stereotype.Service;
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

    public WrapperMovieAsync getWrapperMovieAsync(String movie) throws IOException, InterruptedException {
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

        return mapper.readValue(response.body(), WrapperMovieAsync.class);
    }
}
