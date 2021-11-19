package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Alocacao;
import br.com.mineradora.repository.AlocacaoRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Repository
public class AlocacaoRepositoryImpl extends AbstractRepository<Alocacao> implements AlocacaoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Alocacao findById(BigInteger id) {
		return findById(Alocacao.class, id);
	}

	@Override
	public List<Alocacao> findAll() {
		return findAll(Alocacao.class);
	}
	
}
