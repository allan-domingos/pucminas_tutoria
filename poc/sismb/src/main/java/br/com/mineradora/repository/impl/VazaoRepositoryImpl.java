package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Piezometro;
import br.com.mineradora.entity.Vazao;
import br.com.mineradora.repository.VazaoRepository;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Repository
public class VazaoRepositoryImpl extends AbstractRepository<Vazao> implements VazaoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Vazao findById(BigInteger id) {
		return super.findById(Vazao.class, id);
	}

	@Override
	public List<Vazao> findAll() {
		return super.findAll(Vazao.class);
	}
	
	@Override
	public Vazao findActual(final BigInteger id) {
		Query query = this.em.createQuery("SELECT ct FROM Vazao ct JOIN FETCH ct.sensor sr JOIN sr.barragem bm WHERE bm.id = :id ORDER BY ct.dataInclusao DESC");
		query.setParameter("id", id);
		query.setMaxResults(1);
		return (Vazao) query.getSingleResult();
	}

}
