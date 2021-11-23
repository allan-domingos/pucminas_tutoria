package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.PatrimonioDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
public interface PatrimonioService {

	List<PatrimonioDTO> findAll(); 
	
	PatrimonioDTO findById(final BigInteger id);
	
	void save(PatrimonioDTO dto);
	
	void update(PatrimonioDTO dto); 
	
}
