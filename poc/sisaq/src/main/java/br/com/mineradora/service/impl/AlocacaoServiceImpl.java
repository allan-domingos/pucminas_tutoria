package br.com.mineradora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.AlocacaoDTO;
import br.com.mineradora.repository.AlocacaoRepository;
import br.com.mineradora.service.AlocacaoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Service
public class AlocacaoServiceImpl implements AlocacaoService {

	@Autowired
	private AlocacaoRepository alocacaoRepository;

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AlocacaoDTO findById(Long id) {

		return null;

	}

}
