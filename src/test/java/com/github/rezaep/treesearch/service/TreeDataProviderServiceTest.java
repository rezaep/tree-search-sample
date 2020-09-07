package com.github.rezaep.treesearch.service;

import com.github.rezaep.treesearch.domain.TreeTestDataBuilder;
import com.github.rezaep.treesearch.domain.entity.Tree;
import com.github.rezaep.treesearch.provider.TreeDataProvider;
import com.github.rezaep.treesearch.provider.model.TreeData;
import com.github.rezaep.treesearch.repository.TreeRepository;
import com.github.rezaep.treesearch.util.GeoUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TreeDataProviderServiceTest {
    @Mock
    private TreeDataProvider dataProvider;
    @Mock
    private TreeRepository treeRepository;

    @InjectMocks
    private TreeDataProviderService service;

    @Test
    public void testUpdateTreesDataShouldGetTreesDataFromProviderAndPersistThem() {
        List<TreeData> treeDataList = getTreeDataList();
        when(dataProvider.getTrees()).thenReturn(treeDataList);

        service.updateTreesData();

        List<Tree> treeList = getConvertedTreeList();

        Mockito.verify(treeRepository, times(1)).saveAll(treeList);
    }

    private List<TreeData> getTreeDataList() {
        return List.of(
                new TreeData(180683, "red maple", 40.72309177, -73.84421522),
                new TreeData(200540, "pin oak", 40.79411067, -73.81867946),
                new TreeData(204026, "honeylocust", 40.71758074, -73.9366077),
                new TreeData(204337, "honeylocust", 40.71353749, -73.93445616),
                new TreeData(189565, "American linden", 40.66677776, -73.97597938)
        );
    }

    private List<Tree> getConvertedTreeList() {
        return List.of(
                TreeTestDataBuilder.aTree().with(180683, "red maple", GeoUtils.createPoint(40.72309177, -73.84421522)).build(),
                TreeTestDataBuilder.aTree().with(200540, "pin oak", GeoUtils.createPoint(40.79411067, -73.81867946)).build(),
                TreeTestDataBuilder.aTree().with(204026, "honeylocust", GeoUtils.createPoint(40.71758074, -73.9366077)).build(),
                TreeTestDataBuilder.aTree().with(204337, "honeylocust", GeoUtils.createPoint(40.71353749, -73.93445616)).build(),
                TreeTestDataBuilder.aTree().with(189565, "American linden", GeoUtils.createPoint(40.66677776, -73.97597938)).build()
        );
    }
}