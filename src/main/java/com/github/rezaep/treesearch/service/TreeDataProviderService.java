package com.github.rezaep.treesearch.service;

import com.github.rezaep.treesearch.domain.entity.Tree;
import com.github.rezaep.treesearch.provider.TreeDataProvider;
import com.github.rezaep.treesearch.provider.model.TreeData;
import com.github.rezaep.treesearch.repository.TreeRepository;
import com.github.rezaep.treesearch.util.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreeDataProviderService {
    private final TreeDataProvider treeDataProvider;
    private final TreeRepository treeRepository;

    @PostConstruct
    public void updateTreesData() {
        treeRepository.deleteAll();

        List<Tree> trees = treeDataProvider.getTrees()
                .parallelStream()
                .filter(treeData -> !StringUtils.isEmpty(treeData.getName())) // Filter trees with null name
                .map(this::convertToTree)
                .collect(Collectors.toList());

        treeRepository.saveAll(trees);
    }

    private Tree convertToTree(TreeData treeData) {
        Point location = GeoUtils.createPoint(treeData.getLatitude(), treeData.getLongitude());

        return new Tree()
                .setId(treeData.getId())
                .setName(treeData.getName())
                .setLocation(location);
    }
}
