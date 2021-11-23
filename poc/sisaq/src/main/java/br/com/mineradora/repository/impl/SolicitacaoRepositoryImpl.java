package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Solicitacao;
import br.com.mineradora.repository.SolicitacaoRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Repository
public class SolicitacaoRepositoryImpl extends AbstractRepository<Solicitacao> implements SolicitacaoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Solicitacao findById(BigInteger id) {
		return findById(Solicitacao.class, id);
	}

	@Override
	public List<Solicitacao> findAll() {
		return findAll(Solicitacao.class);
	}
	
}
