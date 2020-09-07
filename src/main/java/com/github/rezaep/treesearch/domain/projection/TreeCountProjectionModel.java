package com.github.rezaep.treesearch.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeCountProjectionModel {
    private String name;
    private long count;
}
