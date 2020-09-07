package com.github.rezaep.treesearch.repository;

import com.github.rezaep.treesearch.domain.TreeTestDataBuilder;
import com.github.rezaep.treesearch.domain.entity.Tree;
import com.github.rezaep.treesearch.domain.projection.TreeCountProjectionModel;
import com.github.rezaep.treesearch.util.GeoUtils;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
public class TreeRepositoryTest {
    @Autowired
    private TreeRepository treeRepository;

    @Test
    public void testFindInAreaShouldReturnAllTreesInArea() {
        setupTrees();

        List<TreeCountProjectionModel> expected = List.of(
                new TreeCountProjectionModel("honeylocust", 3),
                new TreeCountProjectionModel("London planetree", 3),
                new TreeCountProjectionModel("red maple", 1),
                new TreeCountProjectionModel("pin oak", 1)
        );

        Point center = GeoUtils.createPoint(40.72309177, -73.84421522);
        Geometry area = GeoUtils.createCircle(center, 10000);

        List<TreeCountProjectionModel> actual = treeRepository.findInArea(area);

        assertThat(actual)
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    private void setupTrees() {
        List<Tree> trees = List.of(
                TreeTestDataBuilder.aTree().with(180683, "red maple", GeoUtils.createPoint(40.72309177, -73.84421522)).build(),
                TreeTestDataBuilder.aTree().with(200540, "pin oak", GeoUtils.createPoint(40.79411067, -73.81867946)).build(),
                TreeTestDataBuilder.aTree().with(204026, "honeylocust", GeoUtils.createPoint(40.71758074, -73.9366077)).build(),
                TreeTestDataBuilder.aTree().with(204337, "honeylocust", GeoUtils.createPoint(40.71353749, -73.93445616)).build(),
                TreeTestDataBuilder.aTree().with(189565, "American linden", GeoUtils.createPoint(40.66677776, -73.97597938)).build(),
                TreeTestDataBuilder.aTree().with(190422, "honeylocust", GeoUtils.createPoint(40.77004563, -73.98494997)).build(),
                TreeTestDataBuilder.aTree().with(190426, "honeylocust", GeoUtils.createPoint(40.77020969, -73.98533807)).build(),
                TreeTestDataBuilder.aTree().with(208649, "American linden", GeoUtils.createPoint(40.76272385, -73.98729652)).build(),
                TreeTestDataBuilder.aTree().with(209610, "honeylocust", GeoUtils.createPoint(40.59657931, -74.07625483)).build(),
                TreeTestDataBuilder.aTree().with(192755, "London planetree", GeoUtils.createPoint(40.58635725, -73.96974394)).build(),
                TreeTestDataBuilder.aTree().with(203719, "London planetree", GeoUtils.createPoint(40.78242823, -73.91117077)).build(),
                TreeTestDataBuilder.aTree().with(203726, "London planetree", GeoUtils.createPoint(40.78173511, -73.91201957)).build(),
                TreeTestDataBuilder.aTree().with(195202, "London planetree", GeoUtils.createPoint(40.55710259, -74.16267038)).build(),
                TreeTestDataBuilder.aTree().with(189465, "London planetree", GeoUtils.createPoint(40.69473314, -73.96821054)).build(),
                TreeTestDataBuilder.aTree().with(192998, "London planetree", GeoUtils.createPoint(40.6643174, -73.92113023)).build(),
                TreeTestDataBuilder.aTree().with(189834, "honeylocust", GeoUtils.createPoint(40.69331418, -73.96760066)).build(),
                TreeTestDataBuilder.aTree().with(204208, "ginkgo", GeoUtils.createPoint(40.59378755, -73.9915968)).build(),
                TreeTestDataBuilder.aTree().with(161339, "honeylocust", GeoUtils.createPoint(40.64878769, -73.96452447)).build(),
                TreeTestDataBuilder.aTree().with(187311, "honeylocust", GeoUtils.createPoint(40.73764622, -73.86529992)).build(),
                TreeTestDataBuilder.aTree().with(208201, "ginkgo", GeoUtils.createPoint(40.69149917, -73.97258754)).build()
        );

        treeRepository.saveAll(trees);
    }
}