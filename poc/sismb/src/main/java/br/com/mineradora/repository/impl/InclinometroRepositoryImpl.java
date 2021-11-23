package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.entity.Inclinometro;
import br.com.mineradora.repository.InclinometroRepository;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
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

}
