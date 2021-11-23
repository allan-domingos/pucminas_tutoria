package br.com.mineradora.repository.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

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

}
