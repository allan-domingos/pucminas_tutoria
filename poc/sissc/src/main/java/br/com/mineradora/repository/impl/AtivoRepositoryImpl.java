package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Ativo;
import br.com.mineradora.repository.AtivoRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Repository
public class AtivoRepositoryImpl extends AbstractRepository<Ativo> implements AtivoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Ativo findById(BigInteger id) {
		return findById(Ativo.class, id);
	}

	@Override
	public List<Ativo> findAll() {
		return findAll(Ativo.class);
	}
	
}
