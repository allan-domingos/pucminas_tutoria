package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Patrimonio;
import br.com.mineradora.repository.PatrimonioRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Repository
public class PatrimonioRepositoryImpl extends AbstractRepository<Patrimonio> implements PatrimonioRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Patrimonio findById(final BigInteger id) {
		return findById(Patrimonio.class, id);
	}

	@Override
	public List<Patrimonio> findAll() {
		return findAll(Patrimonio.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Patrimonio> findAllByAtivoIdNaoAlocado(final BigInteger id) {
		Query query = this.em.createNativeQuery("SELECT P.* FROM PATRIMONIO P LEFT JOIN ALOCACAO A ON P.ID = A.PATRIMONIO_ID  WHERE P.ATIVO_ID = :id AND A.ID IS NULL", Patrimonio.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
}
