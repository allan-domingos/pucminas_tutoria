package br.com.mineradora.dto;

import java.io.Serializable;
import java.util.Set;

import br.com.mineradora.entity.Solicitacao;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Setter
@Getter
public class InsumoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String cnpj;
	
	private String uf;
	
	private Set<Solicitacao> solicitacoes;
	
}
