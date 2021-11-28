package br.com.mineradora.repository;

import java.math.BigInteger;

import br.com.mineradora.entity.Inclinometro;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
public interface InclinometroRepository extends Repository<Inclinometro> {

	Inclinometro findActual(final BigInteger id);
	
}
