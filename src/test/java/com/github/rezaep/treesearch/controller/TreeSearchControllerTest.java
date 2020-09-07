package com.github.rezaep.treesearch.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rezaep.treesearch.service.TreeSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TreeSearchController.class)
class TreeSearchControllerTest {
    public static final String TREE_SEARCH_URL = "/tree/search";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TreeSearchService service;

    @Test
    void shouldSearchTreesAndReturnAggregatedResultsByName() throws Exception {
        Map<String, Long> searchResult = Map.of("honeylocust", 3L,
                "London planetree", 3L,
                "red maple", 1L,
                "pin oak", 1L);

        when(service.searchTrees(anyDouble(), anyDouble(), anyDouble())).thenReturn(searchResult);

        MvcResult mvcResult = mockMvc.perform(get(TREE_SEARCH_URL)
                .param("lat", String.valueOf(anyDouble()))
                .param("lng", String.valueOf(anyDouble()))
                .param("radius", String.valueOf(anyDouble()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        HashMap<String, Long> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString()
                , new TypeReference<>() {
                });

        assertThat(response).isEqualTo(searchResult);
    }
}