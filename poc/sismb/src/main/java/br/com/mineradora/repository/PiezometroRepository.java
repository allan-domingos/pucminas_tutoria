package br.com.mineradora.repository;

import java.math.BigInteger;

import br.com.mineradora.entity.Piezometro;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
public interface PiezometroRepository extends Repository<Piezometro> {

	Piezometro findActual(final BigInteger id);
	
}
