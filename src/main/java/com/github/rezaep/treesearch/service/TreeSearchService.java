package com.github.rezaep.treesearch.service;

import com.github.rezaep.treesearch.domain.projection.TreeCountProjectionModel;
import com.github.rezaep.treesearch.repository.TreeRepository;
import com.github.rezaep.treesearch.util.GeoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TreeSearchService {
    private final TreeRepository treeRepository;

    /**
     *
     * @param latitude
     * @param longitude
     * @param radius
     * @return
     */
    public Map<String, Long> searchTrees(double latitude, double longitude, double radius) {
        Point center = GeoUtils.createPoint(latitude, longitude);
        Geometry area = GeoUtils.createCircle(center, radius);

        return treeRepository.findInArea(area)
                .stream()
                .collect(Collectors.toMap(TreeCountProjectionModel::getName, TreeCountProjectionModel::getCount));
    }
}
