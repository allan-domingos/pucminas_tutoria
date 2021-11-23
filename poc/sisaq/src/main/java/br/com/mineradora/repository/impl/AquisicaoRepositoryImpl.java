package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Aquisicao;
import br.com.mineradora.repository.AquisicaoRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Repository
public class AquisicaoRepositoryImpl extends AbstractRepository<Aquisicao> implements AquisicaoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Aquisicao findById(BigInteger id) {
		return findById(Aquisicao.class, id);
	}

	@Override
	public List<Aquisicao> findAll() {
		return findAll(Aquisicao.class);
	}
	
}
