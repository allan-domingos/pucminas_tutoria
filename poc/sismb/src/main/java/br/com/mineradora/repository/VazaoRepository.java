package br.com.mineradora.repository;

import java.math.BigInteger;

import br.com.mineradora.entity.Vazao;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
public interface VazaoRepository extends Repository<Vazao> {

	Vazao findActual(final BigInteger id);
	
}
