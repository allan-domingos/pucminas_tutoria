package br.com.mineradora.repository;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.entity.Patrimonio;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 * @param <T>
 */
public interface PatrimonioRepository extends Repository<Patrimonio> {
	
	List<Patrimonio> findAllByAtivoId(final BigInteger id);
	
}
