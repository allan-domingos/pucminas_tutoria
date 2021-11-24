package br.com.mineradora.util;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 23, 2021
 *
 */
public class GeoUtil {

	private GeoUtil() {
	}

	public static Coordinate[] convertDoubleArrayToCoordinateArray(Double[] values) {

		boolean second = false;
		Double x = 0D;

		List<Coordinate> coordenadas = new ArrayList<>(values.length / 2);

		for (Double value : values) {

			if (!second)
				x = value;
			else
				coordenadas.add(new Coordinate( x, value));

			second = !second;

		}

		return coordenadas.toArray(new Coordinate[values.length / 2]);
	}
	
	public static Coordinate convertDoubleArrayToCoordinate(Double[] values) {
		return new Coordinate(values[0], values[1]);
	}

	public static Double[] convertCoordinateArrayToDoubleArray( Coordinate[] coordenadas) {
		List<Double> values = new ArrayList<>(coordenadas.length * 2);

		for (Coordinate coordenada : coordenadas) {
			values.add(coordenada.x);
			values.add(coordenada.y);
		}

		return values.toArray(new Double[values.size()]);
	}
	
	public static Double[] convertCoordinateToDoubleArray( Coordinate coordenada) {
		return new Double[] {coordenada.x,coordenada.y};
	}

}
