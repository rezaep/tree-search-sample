package com.github.rezaep.treesearch.data;

import com.github.rezaep.treesearch.data.entity.Tree;
import com.github.rezaep.treesearch.util.GeoUtils;
import org.locationtech.jts.geom.Point;

public class TreeTestDataBuilder {
    private long id;
    private String name;
    private Point location;

    private TreeTestDataBuilder() {

    }

    public static TreeTestDataBuilder aTree() {
        return new TreeTestDataBuilder();
    }

    public static TreeTestDataBuilder aValidTree() {
        Point location = GeoUtils.createPoint(40.72309177, -73.84421522);

        return new TreeTestDataBuilder()
                .withId(1)
                .withName("station-1")
                .withLocation(location);
    }

    public TreeTestDataBuilder with(long id, String name, Point location) {
        this.id = id;
        this.name = name;
        this.location = location;
        return this;
    }

    public TreeTestDataBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TreeTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TreeTestDataBuilder withLocation(Point location) {
        this.location = location;
        return this;
    }

    public TreeTestDataBuilder but() {
        return TreeTestDataBuilder
                .aTree()
                .withId(id)
                .withName(name)
                .withLocation(location);
    }

    public Tree build() {
        return new Tree()
                .setId(id)
                .setName(name)
                .setLocation(location);
    }
}
