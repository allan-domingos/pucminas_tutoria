package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.LocalDTO;
import br.com.mineradora.entity.Local;
import br.com.mineradora.repository.LocalRepository;
import br.com.mineradora.service.LocalService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	private LocalRepository localRepository;
	
	public static LocalDTO entityToDto(Local local) {
		LocalDTO dto = new LocalDTO();
		dto.setId(local.getId());
		dto.setDataInclusao(local.getDataInclusao());
		dto.setNome(local.getNome());
		return dto;
	}
	
	public static Local dtoToEntity(LocalDTO dto) {
		Local entity = new Local();
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setNome(dto.getNome());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<LocalDTO> findAll() {
		List<Local> locais = this.localRepository.findAll();
		return locais.stream().map((local) -> LocalServiceImpl.entityToDto(local)).collect(Collectors.toList());	
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public LocalDTO findById(final BigInteger id) {
		Local local = this.localRepository.findById(id);
		return LocalServiceImpl.entityToDto(local);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(LocalDTO dto) {
		Local local = LocalServiceImpl.dtoToEntity(dto);
		this.localRepository.save(local);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(LocalDTO dto) {
		Local local = this.localRepository.findById(dto.getId());
		local.setNome(dto.getNome());
		this.localRepository.update(local);
	}

}
