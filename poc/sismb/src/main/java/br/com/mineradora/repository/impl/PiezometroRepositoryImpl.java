package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

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

}
