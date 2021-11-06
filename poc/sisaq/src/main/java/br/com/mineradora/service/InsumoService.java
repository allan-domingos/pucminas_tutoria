package br.com.mineradora.service;

import java.util.List;

import br.com.mineradora.dto.InsumoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface InsumoService {

	List<InsumoDTO> findAll(); 
	
	InsumoDTO findById(final Long id);
	
	void save(InsumoDTO dto);
	
	void update(InsumoDTO dto); 
	
}
