package com.github.rezaep.treesearch.provider.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TreeData {
    @JsonProperty("tree_id")
    private long id; // Unique identification number for each tree point.
    @JsonProperty("spc_common")
    private String name; // Common name for species, e.g. "red maple"
    private double latitude; // Latitude of point, in decimal degrees
    private double longitude; // Longitude of point, in decimal degrees
}
