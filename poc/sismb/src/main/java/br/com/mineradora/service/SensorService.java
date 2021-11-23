package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.SensorDTO;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 22, 2021
 *
 */
public interface SensorService {

	List<SensorDTO> findAll();

	SensorDTO findById(final BigInteger id);

	void save(SensorDTO dto);

	void update(SensorDTO dto);
	
}
