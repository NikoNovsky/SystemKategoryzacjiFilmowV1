package com.example.currenda.web.controller;

import com.example.currenda.data.model.Movies;
import com.example.currenda.data.model.User;
import com.example.currenda.data.repository.MovieRepository;
import com.example.currenda.data.repository.UserRepository;
import com.example.currenda.web.service.MovieService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public MovieController(MovieRepository movieRepository, UserRepository userRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
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

    @PostMapping("/deleteOrUpdate")
    public String deleteOrUpdateMovieFromFavorite(@RequestParam String action, @RequestParam Integer id, Principal principal, Movies movie) {
        if (action.equals("delete")) {
            deleteMovieFromFavorite(id, principal);
        } else {
            updateMovieFromFavorite(id, principal, movie);
        }
        return "redirect:/movies";
    }

    private void deleteMovieFromFavorite(Integer id, Principal principal) {
        this.movieRepository.delete(getMoviesForUser(id, principal));
    }

    private void updateMovieFromFavorite(Integer id, Principal principal, Movies movie) {
        Movies movieToUpdate = getMoviesForUser(id, principal);
        movieToUpdate.setTitle(movie.getTitle());
        movieToUpdate.setReleaseDate(movie.getReleaseDate());
        movieToUpdate.setVoteAverage(movie.getVoteAverage());
        movieToUpdate.setDirector(movie.getDirector());
        this.movieRepository.save(movieToUpdate);
    }

    private Movies getMoviesForUser(Integer id, Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username);
        return this.movieRepository.findByIdAndUser(id, user);
    }
}
