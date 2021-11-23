package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Local;
import br.com.mineradora.repository.LocalRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Repository
public class LocalRepositoryImpl extends AbstractRepository<Local> implements LocalRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Local findById(BigInteger id) {
		return findById(Local.class, id);
	}

	@Override
	public List<Local> findAll() {
		return findAll(Local.class);
	}
	
}
