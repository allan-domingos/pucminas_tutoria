package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.PatrimonioDTO;
import br.com.mineradora.entity.Patrimonio;
import br.com.mineradora.repository.PatrimonioRepository;
import br.com.mineradora.service.PatrimonioService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Service
public class PatrimonioServiceImpl implements PatrimonioService {

	@Autowired
	private PatrimonioRepository patrimonioRepository;
	
	public static PatrimonioDTO entityToDto(Patrimonio patrimonio) {
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
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<PatrimonioDTO> findAll() {
		List<Patrimonio> patrimonios = this.patrimonioRepository.findAll();
		return patrimonios.stream().map((insumo) -> PatrimonioServiceImpl.entityToDto(insumo)).collect(Collectors.toList());	
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public PatrimonioDTO findById(final BigInteger id) {
		Patrimonio patrimonio = this.patrimonioRepository.findById(id);
		return PatrimonioServiceImpl.entityToDto(patrimonio);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(PatrimonioDTO dto) {
		Patrimonio patrimonio = PatrimonioServiceImpl.dtoToEntity(dto);
		this.patrimonioRepository.save(patrimonio);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PatrimonioDTO dto) {
		Patrimonio patrimonio = this.patrimonioRepository.findById(dto.getId());
		patrimonio.setNumero(dto.getNumero());
		this.patrimonioRepository.update(patrimonio);
	}

}
