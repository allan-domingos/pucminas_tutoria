package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.entity.Barragem;
import br.com.mineradora.repository.BarragemRepository;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
public class BarragemRepositoryImpl extends AbstractRepository<Barragem> implements BarragemRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Barragem findById(BigInteger id) {
		return super.findById(Barragem.class, id);
	}

	@Override
	public List<Barragem> findAll() {
		return super.findAll(Barragem.class);
	}

}
