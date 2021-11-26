package br.com.mineradora.repository;

import java.math.BigInteger;
import java.util.List;

import br.com.mineradora.entity.Solicitacao;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 * @param <T>
 */
public interface SolicitacaoRepository extends Repository<Solicitacao> {
	
	public List<Solicitacao> findAllByAtivoId(final BigInteger id);
	
}
