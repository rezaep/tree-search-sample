package com.github.rezaep.treesearch.domain.entity;

import lombok.Data;
import org.locationtech.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tree")
public class Tree {
    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Point location;
}
