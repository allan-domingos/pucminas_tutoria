package br.com.mineradora.service.impl;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import br.com.mineradora.util.GeoUtil;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public class AbstractService {
	
	protected Geometry doubleArrayToPoint(Double[] values) {
		GeometryFactory gf = new GeometryFactory();
		return gf.createPoint(GeoUtil.convertDoubleArrayToCoordinate(values));
	}

	protected Geometry doubleArrayToPolygon(Double[] values) {
		GeometryFactory gf = new GeometryFactory();
		return gf.createPolygon(GeoUtil.convertDoubleArrayToCoordinateArray(values));
	}

	protected Double[] pointToDoubleArray(Point point) {
		return GeoUtil.convertCoordinateToDoubleArray(point.getCoordinate());
	}

	protected Double[] polygonToDoubleArray(Polygon polygon) {
		return GeoUtil.convertCoordinateArrayToDoubleArray(polygon.getCoordinates());
	}
	
}
