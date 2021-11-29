package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Piezometro;
import br.com.mineradora.repository.PiezometroRepository;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Repository
public class PiezometroRepositoryImpl extends AbstractRepository<Piezometro> implements PiezometroRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Piezometro findById(BigInteger id) {
		return super.findById(Piezometro.class, id);
	}

	@Override
	public List<Piezometro> findAll() {
		return super.findAll(Piezometro.class);
	}
	
	@Override
	public Piezometro findActual(final BigInteger id) {
		Query query = this.em.createQuery("SELECT ct FROM Piezometro ct JOIN FETCH ct.sensor sr JOIN sr.barragem bm WHERE bm.id = :id ORDER BY ct.data DESC");
		query.setParameter("id", id);
		query.setMaxResults(1);
		try {
			return (Piezometro) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
