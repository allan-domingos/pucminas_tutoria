package br.com.mineradora.repository;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.entity.Aquisicao;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 * @param <T>
 */
public interface AquisicaoRepository extends Repository<Aquisicao> {

	public List<Aquisicao> findAllByAtivoId(final BigInteger id);
	
	public List<Aquisicao> findAllBySolicitacaoId(final BigInteger id);

}
