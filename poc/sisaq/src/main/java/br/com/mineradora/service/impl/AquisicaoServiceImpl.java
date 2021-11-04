package br.com.mineradora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.AquisicaoDTO;
import br.com.mineradora.repository.AquisicaoRepository;
import br.com.mineradora.service.AquisicaoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Service
public class AquisicaoServiceImpl implements AquisicaoService {

	@Autowired
	private AquisicaoRepository aquisicaoRepository;

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AquisicaoDTO findById(Long id) {

		return null;

	}

}
