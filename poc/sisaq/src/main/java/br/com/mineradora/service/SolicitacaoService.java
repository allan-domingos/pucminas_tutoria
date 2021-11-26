package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.SolicitacaoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface SolicitacaoService {

	List<SolicitacaoDTO> findAll();

	SolicitacaoDTO findById(final BigInteger id);

	void save(SolicitacaoDTO dto);

	void update(SolicitacaoDTO dto);

	List<SolicitacaoDTO> findAllByAtivoId(final BigInteger id);
	
}
