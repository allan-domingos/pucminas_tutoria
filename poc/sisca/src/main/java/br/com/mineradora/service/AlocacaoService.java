package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.AlocacaoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface AlocacaoService {

	List<AlocacaoDTO> findAll();

	AlocacaoDTO findById(final BigInteger id);

	void save(AlocacaoDTO dto);

	void update(AlocacaoDTO dto);
	
}
