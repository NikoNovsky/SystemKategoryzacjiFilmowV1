package com.example.currenda.async;

import lombok.Data;

import java.util.List;

@Data
public class WrapperMovieAsync {
    private Integer page;
    private List<MovieAsync> results;
    private Integer total_pages;
    private Integer total_results;
}
