package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.CargaTensao;
import br.com.mineradora.repository.CargaTensaoRepository;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Repository
public class CargaTensaoRepositoryImpl extends AbstractRepository<CargaTensao> implements CargaTensaoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public CargaTensao findById(BigInteger id) {
		return super.findById(CargaTensao.class, id);
	}

	@Override
	public List<CargaTensao> findAll() {
		return super.findAll(CargaTensao.class);
	}

	@Override
	public CargaTensao findActual(final BigInteger id) {
		Query query = this.em.createQuery(
				"SELECT ct FROM CargaTensao ct JOIN FETCH ct.sensor sr JOIN sr.barragem bm WHERE bm.id = :id ORDER BY ct.data DESC");
		query.setParameter("id", id);
		query.setMaxResults(1);
		try {
			return (CargaTensao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
