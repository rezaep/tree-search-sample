package com.github.rezaep.treesearch.controller;

import com.github.rezaep.treesearch.service.TreeSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("tree")
@RequiredArgsConstructor
public class TreeSearchController {
    private final TreeSearchService searchService;

    @GetMapping("search")
    public Map<String, Long> searchTrees(@RequestParam(name = "lat") double latitude,
                                         @RequestParam(name = "lng") double longitude,
                                         @RequestParam double radius) {
        return searchService.searchTrees(latitude, longitude, radius);
    }
}
