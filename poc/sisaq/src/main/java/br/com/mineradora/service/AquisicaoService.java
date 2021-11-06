package br.com.mineradora.service;

import java.util.List;

import br.com.mineradora.dto.AquisicaoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface AquisicaoService {

	List<AquisicaoDTO> findAll();

	AquisicaoDTO findById(final Long id);

	void save(AquisicaoDTO dto);

	void update(AquisicaoDTO dto);
	
}
