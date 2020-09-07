package com.github.rezaep.treesearch.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;

public class GeoUtils {
    public static double convertMeterToRadius(double meter) {
        return meter * 0.00001 / 0.941;
    }

    /**
     * Create point from the given latitude and longitude.
     *
     * @param latitude  the latitude of the point
     * @param longitude the longitude of the point
     * @return the point
     */
    public static Point createPoint(double latitude, double longitude) {
        return new GeometryFactory().createPoint(new Coordinate(longitude, latitude));
    }

    /**
     * Create a circle with the the given center and meter
     *
     * @param center center of the circle
     * @param meter  radius in meter
     * @return circle
     */
    public static Geometry createCircle(Point center, double meter) {
        double radius = GeoUtils.convertMeterToRadius(meter);
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(center.getCoordinate());
        shapeFactory.setSize(radius * 2);
        return shapeFactory.createCircle();
    }
}
