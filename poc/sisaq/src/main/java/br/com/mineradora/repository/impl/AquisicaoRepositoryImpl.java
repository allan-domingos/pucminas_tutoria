package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

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

	@Override
	@SuppressWarnings("unchecked")
	public List<Aquisicao> findAllByAtivoId(final BigInteger id) {
		Query query = this.em.createQuery("SELECT aq FROM Aquisicao aq JOIN aq.solicitacao so WHERE so.idAtivo = :id ");
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Aquisicao> findAllBySolicitacaoId(final BigInteger id) {
		Query query = this.em.createQuery("SELECT aq FROM Aquisicao aq JOIN aq.solicitacao so WHERE so.id = :id ");
		query.setParameter("id", id);
		return query.getResultList();
	}

}
