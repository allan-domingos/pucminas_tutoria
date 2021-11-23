package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Patrimonio;
import br.com.mineradora.repository.PatrimonioRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Repository
public class PatrimonioRepositoryImpl extends AbstractRepository<Patrimonio> implements PatrimonioRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Patrimonio findById(BigInteger id) {
		return findById(Patrimonio.class, id);
	}

	@Override
	public List<Patrimonio> findAll() {
		return findAll(Patrimonio.class);
	}
	
}
