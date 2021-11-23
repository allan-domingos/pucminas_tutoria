package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.entity.Sensor;
import br.com.mineradora.repository.SensorRepository;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
public class SensorRepositoryImpl extends AbstractRepository<Sensor> implements SensorRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Sensor findById(BigInteger id) {
		return super.findById(Sensor.class, id);
	}

	@Override
	public List<Sensor> findAll() {
		return super.findAll(Sensor.class);
	} 

}
