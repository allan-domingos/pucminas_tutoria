package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.CargaTensaoDTO;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 22, 2021
 *
 */
public interface CargaTensaoService {

	List<CargaTensaoDTO> findAll();

	CargaTensaoDTO findById(final BigInteger id);

	void save(CargaTensaoDTO dto);

	void update(CargaTensaoDTO dto);
	
}
