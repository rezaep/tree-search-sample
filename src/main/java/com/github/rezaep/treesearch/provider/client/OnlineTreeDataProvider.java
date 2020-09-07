package com.github.rezaep.treesearch.provider.client;

import com.github.rezaep.treesearch.provider.TreeDataProvider;
import com.github.rezaep.treesearch.provider.model.TreeData;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OnlineTreeDataProvider implements TreeDataProvider {
    private static final String NYC_TREE_DATA_API_URL = "https://data.cityofnewyork.us/resource/uvpi-gqnh.json";

    private final RestTemplate restTemplate;

    /**
     * Fetch trees data from the remote webservice.
     *
     * @return list of tree data
     */
    @Override
    public List<TreeData> getTrees() {
        return restTemplate.exchange(NYC_TREE_DATA_API_URL, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<TreeData>>() {
                }).getBody();
    }
}
