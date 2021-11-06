package br.com.mineradora.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.InsumoDTO;
import br.com.mineradora.entity.Insumo;
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
	private InsumoRepository insumoRepository;
	
	public static InsumoDTO entityToDto(Insumo insumo) {
		InsumoDTO dto = new InsumoDTO();
		dto.setId(insumo.getId());
		dto.setDescricao(insumo.getDescricao());
		dto.setDataInclusao(insumo.getDataInclusao());
		dto.setNome(insumo.getNome());
		return dto;
	}
	
	public static Insumo dtoToEntity(InsumoDTO dto) {
		Insumo entity = new Insumo();
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setNome(dto.getNome());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<InsumoDTO> findAll() {
		List<Insumo> insumos = this.insumoRepository.findAll();
		return insumos.stream().map((insumo) -> InsumoServiceImpl.entityToDto(insumo)).collect(Collectors.toList());	
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public InsumoDTO findById(final Long id) {
		Insumo insumo = this.insumoRepository.findById(id);
		return InsumoServiceImpl.entityToDto(insumo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(InsumoDTO dto) {
		Insumo insumo = InsumoServiceImpl.dtoToEntity(dto);
		this.insumoRepository.save(insumo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(InsumoDTO dto) {
		Insumo insumo = this.insumoRepository.findById(dto.getId());
		insumo.setDescricao(dto.getDescricao());
		insumo.setNome(dto.getNome());
		this.insumoRepository.update(insumo);
	}

}
