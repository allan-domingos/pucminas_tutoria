package br.com.mineradora.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.InsumoDTO;
import br.com.mineradora.repository.InsumoRepository;
import br.com.mineradora.service.InsumoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Service
public class InsumoServiceImpl implements InsumoService {

	@Autowired
	private InsumoRepository terrenoRepository;

	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<InsumoDTO> findAll() {

		return null;	
	}

}
