package com.github.rezaep.treesearch.repository;

import com.github.rezaep.treesearch.domain.entity.Tree;
import com.github.rezaep.treesearch.domain.projection.TreeCountProjectionModel;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreeRepository extends JpaRepository<Tree, Long> {
    @Query("SELECT new com.github.rezaep.treesearch.domain.projection.TreeCountProjectionModel(tree.name, count(tree.name)) " +
            "from Tree tree where within(tree.location, :area)=true " +
            "group by tree.name order by count(tree.name) desc")
    List<TreeCountProjectionModel> findInArea(@Param("area") Geometry area);
}
