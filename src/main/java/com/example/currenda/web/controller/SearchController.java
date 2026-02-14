package com.example.currenda.web.controller;

import com.example.currenda.async.MovieAsync;
import com.example.currenda.async.WrapperMovieAsync;
import com.example.currenda.data.model.Movies;
import com.example.currenda.data.model.User;
import com.example.currenda.data.repository.MovieRepository;
import com.example.currenda.data.repository.UserRepository;
import com.example.currenda.web.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final MovieService movieService;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public SearchController(MovieService movieService, UserRepository userRepository, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping()
    public String getSearch(Model model, @RequestParam(required = false) String query) {
        try {
            if (query != null && !query.isBlank()) {
                WrapperMovieAsync wrapperMovieAsync = movieService.getWrapperMovieAsync(query);

                model.addAttribute("movies", wrapperMovieAsync.getResults());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }

    @PostMapping
    public String addMovieToUser(@RequestParam(required = false) List<String> movies, Principal principal, Model model) {
        if (movies == null || movies.isEmpty()) {
            model.addAttribute("error", "Nie wybrano żadnych filmów do dodania!");
            return "search";
        } else {
            String username = principal.getName();
            User user = this.userRepository.findByUsername(username);
            movies.forEach(movie -> {
                try {
                    if (!this.movieRepository.existsByTitleAndUser(movie, user)) {
                        WrapperMovieAsync wrapperMovieAsync = movieService.getWrapperMovieAsync(movie);

                        MovieAsync firstChild = wrapperMovieAsync.getResults().getFirst();
                        Movies movieToAdd = new Movies(firstChild.getTitle(), "Brak danych", user, LocalDate.parse(firstChild.getReleaseDate()), String.valueOf(firstChild.getVoteAverage()));
                        this.movieRepository.save(movieToAdd);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        return "redirect:/movies";
    }
}
