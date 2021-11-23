package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.BarragemDTO;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 22, 2021
 *
 */
public interface BarragemService {

	List<BarragemDTO> findAll();

	BarragemDTO findById(final BigInteger id);

	void save(BarragemDTO dto);

	void update(BarragemDTO dto);
	
}
