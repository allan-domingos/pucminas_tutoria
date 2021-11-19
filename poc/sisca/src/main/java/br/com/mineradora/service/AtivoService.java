package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.AtivoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface AtivoService {

	List<AtivoDTO> findAll(); 
	
	AtivoDTO findById(final BigInteger id);
	
	void save(AtivoDTO dto);
	
	void update(AtivoDTO dto); 
	
}
