package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.VazaoDTO;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 22, 2021
 *
 */
public interface VazaoService {

	List<VazaoDTO> findAll();

	VazaoDTO findById(final BigInteger id);

	void save(VazaoDTO dto);

	void update(VazaoDTO dto);
	
}
