package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.LocalDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
public interface LocalService {

	List<LocalDTO> findAll(); 
	
	LocalDTO findById(final BigInteger id);
	
	void save(LocalDTO dto);
	
	void update(LocalDTO dto); 
	
}
