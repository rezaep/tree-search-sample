package com.github.rezaep.treesearch.service;

import com.github.rezaep.treesearch.domain.projection.TreeCountProjectionModel;
import com.github.rezaep.treesearch.repository.TreeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreeSearchServiceTest {
    @Mock
    private TreeRepository treeRepository;

    @InjectMocks
    private TreeSearchService service;

    @Test
    public void shouldSearchTreeAndConvertResults() {
        List<TreeCountProjectionModel> searchResult = List.of(
                new TreeCountProjectionModel("red maple", 30L),
                new TreeCountProjectionModel("American linden", 1L),
                new TreeCountProjectionModel("London planetree", 3L)
        );

        when(treeRepository.findInArea(any()))
                .thenReturn(searchResult);

        Map<String, Long> expected = Map.of(
                "red maple", 30L,
                "American linden", 1L,
                "London planetree", 3L
        );

        Map<String, Long> actual = service.searchTrees(40.72309177, -73.84421522, 1000);

        assertThat(actual).isEqualTo(expected);
    }
}