package com.example.currenda.web.controller;

import com.example.currenda.async.WrapperMovieAsync;
import com.example.currenda.web.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final MovieService movieService;

    public SearchController(MovieService movieService) {
        this.movieService = movieService;
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
}
