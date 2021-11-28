package br.com.mineradora.service;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.dto.DadoDTO;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 28, 2021
 *
 */
public interface DadoService {

	List<DadoDTO> findAtual(final BigInteger id);
	
}
