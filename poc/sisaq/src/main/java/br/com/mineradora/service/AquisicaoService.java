package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.AquisicaoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface AquisicaoService {

	List<AquisicaoDTO> findAll();

	AquisicaoDTO findById(final BigInteger id);

	void save(AquisicaoDTO dto);

	void update(AquisicaoDTO dto);
	
	List<AquisicaoDTO> findAllByAtivoId(final BigInteger id);
	
}
