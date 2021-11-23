package br.com.mineradora.repository.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 * @param <T>
 */
public class AbstractRepository<T extends Serializable> {

	@PersistenceContext
	protected EntityManager em;

	public void save(T t) {
		this.em.persist(t);
	}

	public void update(T t) {
		this.em.merge(t);
	}

	public void delete(T t) {
		this.em.remove(t);
	}

	public T findById(Class<T> t, BigInteger id) {
		return this.em.find(t, id);
	}

	public List<T> findAll(Class<T> t) {
		CriteriaQuery<T> criteria = this.em.getCriteriaBuilder().createQuery(t);
		Root<T> root = criteria.from(t);
		criteria.select(root);
		TypedQuery<T> query = this.em.createQuery(criteria);
		return query.getResultList();
	}
}
