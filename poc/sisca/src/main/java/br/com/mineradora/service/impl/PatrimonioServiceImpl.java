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

import br.com.mineradora.dto.PatrimonioDTO;
import br.com.mineradora.entity.Ativo;
import br.com.mineradora.entity.Patrimonio;
import br.com.mineradora.repository.AtivoRepository;
import br.com.mineradora.repository.PatrimonioRepository;
import br.com.mineradora.service.PatrimonioService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Service
public class PatrimonioServiceImpl extends AbstractService implements PatrimonioService {

	@Autowired
	private PatrimonioRepository patrimonioRepository;

	@Autowired
	private AtivoRepository ativoRepository;

	public static PatrimonioDTO entityToDto(Patrimonio patrimonio) {
		if(patrimonio == null )
			return null;
		
		PatrimonioDTO dto = new PatrimonioDTO();
		dto.setId(patrimonio.getId());
		dto.setNumero(patrimonio.getNumero());
		dto.setDataInclusao(patrimonio.getDataInclusao());
		return dto;
	}

	public static Patrimonio dtoToEntity(PatrimonioDTO dto) {
		Patrimonio entity = new Patrimonio();
		entity.setId(dto.getId());
		entity.setNumero(dto.getNumero());
		entity.setDataInclusao(dto.getDataInclusao());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<PatrimonioDTO> findAll() {
		List<Patrimonio> patrimonios = this.patrimonioRepository.findAll();
		return patrimonios.stream().map(patrimonio -> {
			PatrimonioDTO dto = PatrimonioServiceImpl.entityToDto(patrimonio);
			dto.setAtivo(AtivoServiceImpl.entityToDto(patrimonio.getAtivo()));
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public PatrimonioDTO findById(final BigInteger id) {
		Patrimonio patrimonio = this.patrimonioRepository.findById(id);
		PatrimonioDTO dto = PatrimonioServiceImpl.entityToDto(patrimonio);
		dto.setAtivo(AtivoServiceImpl.entityToDto(patrimonio.getAtivo()));
		return dto;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(PatrimonioDTO dto) {
		Ativo ativo = this.ativoRepository.findById(dto.getAtivo().getId());
		Patrimonio patrimonio = PatrimonioServiceImpl.dtoToEntity(dto);
		patrimonio.setDataInclusao(LocalDateTime.now());
		patrimonio.setAtivo(ativo);
		this.patrimonioRepository.save(patrimonio);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PatrimonioDTO dto) {
		Patrimonio patrimonio = this.patrimonioRepository.findById(dto.getId());
		patrimonio.setNumero(dto.getNumero());
		this.patrimonioRepository.update(patrimonio);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<PatrimonioDTO> findAllByAtivoIdNaoAlocado(final BigInteger id) {
		List<Patrimonio> patrimonios = this.patrimonioRepository.findAllByAtivoIdNaoAlocado(id);
		return patrimonios.stream().map(patrimonio -> {
			PatrimonioDTO dto = PatrimonioServiceImpl.entityToDto(patrimonio);
			dto.setAtivo(AtivoServiceImpl.entityToDto(patrimonio.getAtivo()));
			return dto;
		}).collect(Collectors.toList());
	}

}
