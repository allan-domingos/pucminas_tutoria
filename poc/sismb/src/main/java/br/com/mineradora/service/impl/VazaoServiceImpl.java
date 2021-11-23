package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.VazaoDTO;
import br.com.mineradora.entity.Vazao;
import br.com.mineradora.repository.VazaoRepository;
import br.com.mineradora.service.VazaoService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Service
public class VazaoServiceImpl implements VazaoService {

	
	@Autowired
	private VazaoRepository vazaoRepository;

	public static VazaoDTO entityToDto(Vazao entity) {
		VazaoDTO dto = new VazaoDTO();
		dto.setId(entity.getId());
		dto.setData(entity.getData());
		dto.setValor(entity.getValor());
		return dto;
	}

	public static Vazao dtoToEntity(VazaoDTO dto) {
		Vazao entity = new Vazao();
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setValor(dto.getValor());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<VazaoDTO> findAll() {
		List<Vazao> vazoes = this.vazaoRepository.findAll();
		
		return vazoes.stream().map((vazao) -> {
			return VazaoServiceImpl.entityToDto(vazao);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public VazaoDTO findById(final BigInteger id) {
		Vazao vazao = this.vazaoRepository.findById(id);
		return VazaoServiceImpl.entityToDto(vazao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(VazaoDTO dto) {
		Vazao vazao = VazaoServiceImpl.dtoToEntity(dto);
		this.vazaoRepository.save(vazao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(VazaoDTO dto) {
		Vazao vazao = this.vazaoRepository.findById(dto.getId());
		this.vazaoRepository.update(vazao);
	}
	
}
