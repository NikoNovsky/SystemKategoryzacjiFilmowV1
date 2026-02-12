package com.example.currenda.web.controller;

import com.example.currenda.async.MovieAsync;
import com.example.currenda.async.WrapperMovieAsync;
import com.example.currenda.data.model.Movies;
import com.example.currenda.data.model.User;
import com.example.currenda.data.repository.MovieRepository;
import com.example.currenda.data.repository.UserRepository;
import com.example.currenda.web.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieService movieService;

    public MovieController(MovieRepository movieRepository, UserRepository userRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.movieService = movieService;
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
    public String addMovieToUser(@RequestParam List<String> movies, Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username);
        movies.forEach(movie -> {
            try {
                if (!movieRepository.existsByTitleAndUser(movie, user)) {
                    WrapperMovieAsync wrapperMovieAsync = movieService.getWrapperMovieAsync(movie);

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
