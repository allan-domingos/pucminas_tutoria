package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.InclinometroDTO;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 22, 2021
 *
 */
public interface InclinometroService {

	List<InclinometroDTO> findAll();

	InclinometroDTO findById(final BigInteger id);

	void save(InclinometroDTO dto);

	void update(InclinometroDTO dto);
	
}
