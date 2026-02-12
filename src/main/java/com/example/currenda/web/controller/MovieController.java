package com.example.currenda.web.controller;

import com.example.currenda.async.MovieAsync;
import com.example.currenda.async.WrapperMovieAsync;
import com.example.currenda.data.model.Movies;
import com.example.currenda.data.model.User;
import com.example.currenda.data.repository.MovieRepository;
import com.example.currenda.data.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;

    public MovieController(MovieRepository movieRepository, ObjectMapper mapper, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAllMovies(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("movies", this.movieRepository.findByUserUsername(username));
        model.addAttribute("module", "movies");
        return "movies";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addMovieToUser(@RequestParam List<String> movies, Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username);
        movies.forEach(movie -> {
            try {
                if (!movieRepository.existsByTitleAndUser(movie, user)) {
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

                    WrapperMovieAsync wrapperMovieAsync = mapper.readValue(response.body(), WrapperMovieAsync.class);

                    MovieAsync firstChild = wrapperMovieAsync.getResults().getFirst();
                    Movies movieToAdd = new Movies(firstChild.getTitle(), "Brak danych", user, LocalDate.parse(firstChild.getReleaseDate()), String.valueOf(firstChild.getVoteAverage()));
                    this.movieRepository.save(movieToAdd);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return "redirect:/movies";
    }

    @PostMapping("/delete")
    public String deleteMovieFromFavorite(@RequestParam List<Integer> id, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        List<Movies> moviesToDelete = movieRepository.findAllByIdInAndUser(id, user);
        movieRepository.deleteAll(moviesToDelete);

        return "redirect:/movies";
    }
}
