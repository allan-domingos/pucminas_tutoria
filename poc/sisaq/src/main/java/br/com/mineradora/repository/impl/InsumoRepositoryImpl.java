package br.com.mineradora.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mineradora.entity.Insumo;
import br.com.mineradora.repository.InsumoRepository;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Repository
public class InsumoRepositoryImpl extends AbstractRepository<Insumo> implements InsumoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Insumo findById(Long id) {
		return findById(Insumo.class, id);
	}

	@Override
	public List<Insumo> findAll() {
		return findAll(Insumo.class);
	}
	
}
