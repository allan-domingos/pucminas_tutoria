package br.com.mineradora.repository;

import java.math.BigInteger;

import br.com.mineradora.entity.CargaTensao;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
public interface CargaTensaoRepository extends Repository<CargaTensao> {

	CargaTensao findActual(final BigInteger id);
	
}
