package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
public class AtivoServiceImpl extends AbstractService implements AtivoService {

	@Autowired
	private AtivoRepository ativoRepository;
	
	public static AtivoDTO entityToDto(Ativo entity) {
		if(entity == null)
			return null;
		
		AtivoDTO dto = new AtivoDTO();
		dto.setId(entity.getId());
		dto.setDescricao(entity.getDescricao());
		dto.setDataInclusao(entity.getDataInclusao());
		dto.setNome(entity.getNome());
		dto.setDuravel(entity.getDuravel());
		return dto;
	}
	
	public static Ativo dtoToEntity(AtivoDTO dto) {
		if(dto == null)
			return null;
		
		Ativo entity = new Ativo();
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setNome(dto.getNome());
		entity.setDuravel(dto.getDuravel());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<AtivoDTO> findAll() {
		List<Ativo> insumos = this.ativoRepository.findAll();
		return insumos.stream().map( ativo -> AtivoServiceImpl.entityToDto(ativo)).collect(Collectors.toList());	
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AtivoDTO findById(final BigInteger id) {
		Ativo ativo = this.ativoRepository.findById(id);
		return AtivoServiceImpl.entityToDto(ativo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AtivoDTO dto) {
		Ativo ativo = AtivoServiceImpl.dtoToEntity(dto);
		ativo.setDataInclusao(LocalDateTime.now());
		this.ativoRepository.save(ativo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AtivoDTO dto) {
		Ativo ativo = this.ativoRepository.findById(dto.getId());
		ativo.setDescricao(dto.getDescricao());
		ativo.setNome(dto.getNome());
		ativo.setDuravel(dto.getDuravel());
		this.ativoRepository.update(ativo);
	}

}
