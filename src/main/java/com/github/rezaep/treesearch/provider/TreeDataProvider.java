package com.github.rezaep.treesearch.provider;

import com.github.rezaep.treesearch.provider.model.TreeData;

import java.util.List;

public interface TreeDataProvider {
    /**
     * Fetch trees data from the selected provider.
     *
     * @return list of tree data
     */
    List<TreeData> getTrees();
}
