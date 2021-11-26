package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

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

	@Override
	@SuppressWarnings("unchecked")
	public List<Solicitacao> findAllByAtivoId(final BigInteger id) {
		Query query = this.em.createQuery("SELECT so FROM Solicitacao so WHERE so.idAtivo = :id ");
		query.setParameter("id", id);
		return query.getResultList();
	}

}
