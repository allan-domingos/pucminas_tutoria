package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.AtivoDTO;
import br.com.mineradora.entity.Ativo;
import br.com.mineradora.repository.AtivoRepository;
import br.com.mineradora.service.AtivoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Service
public class AtivoServiceImpl implements AtivoService {

	@Autowired
	private AtivoRepository insumoRepository;
	
	public static AtivoDTO entityToDto(Ativo insumo) {
		AtivoDTO dto = new AtivoDTO();
		dto.setId(insumo.getId());
		dto.setDescricao(insumo.getDescricao());
		dto.setDataInclusao(insumo.getDataInclusao());
		dto.setNome(insumo.getNome());
		return dto;
	}
	
	public static Ativo dtoToEntity(AtivoDTO dto) {
		Ativo entity = new Ativo();
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setNome(dto.getNome());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<AtivoDTO> findAll() {
		List<Ativo> insumos = this.insumoRepository.findAll();
		return insumos.stream().map((insumo) -> AtivoServiceImpl.entityToDto(insumo)).collect(Collectors.toList());	
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AtivoDTO findById(final BigInteger id) {
		Ativo insumo = this.insumoRepository.findById(id);
		return AtivoServiceImpl.entityToDto(insumo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AtivoDTO dto) {
		Ativo insumo = AtivoServiceImpl.dtoToEntity(dto);
		this.insumoRepository.save(insumo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AtivoDTO dto) {
		Ativo insumo = this.insumoRepository.findById(dto.getId());
		insumo.setDescricao(dto.getDescricao());
		insumo.setNome(dto.getNome());
		this.insumoRepository.update(insumo);
	}

}
