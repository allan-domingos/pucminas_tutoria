package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Inclinometro;
import br.com.mineradora.repository.InclinometroRepository;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Repository
public class InclinometroRepositoryImpl extends AbstractRepository<Inclinometro> implements InclinometroRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Inclinometro findById(BigInteger id) {
		return super.findById(Inclinometro.class, id);
	}

	@Override
	public List<Inclinometro> findAll() {
		return super.findAll(Inclinometro.class);
	}

	@Override
	public Inclinometro findActual(final BigInteger id) {
		Query query = this.em.createQuery(
				"SELECT ct FROM Inclinometro ct JOIN FETCH ct.sensor sr JOIN sr.barragem bm WHERE bm.id = :id ORDER BY ct.data DESC");
		query.setParameter("id", id);
		query.setMaxResults(1);
		try {
			return (Inclinometro) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
