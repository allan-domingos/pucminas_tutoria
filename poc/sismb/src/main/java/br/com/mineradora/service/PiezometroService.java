package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.PiezometroDTO;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 22, 2021
 *
 */
public interface PiezometroService {

	List<PiezometroDTO> findAll();

	PiezometroDTO findById(final BigInteger id);

	void save(PiezometroDTO dto);

	void update(PiezometroDTO dto);
	
}
