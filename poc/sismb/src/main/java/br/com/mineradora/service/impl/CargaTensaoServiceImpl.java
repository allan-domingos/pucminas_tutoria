package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.CargaTensaoDTO;
import br.com.mineradora.entity.CargaTensao;
import br.com.mineradora.repository.CargaTensaoRepository;
import br.com.mineradora.service.CargaTensaoService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Service
public class CargaTensaoServiceImpl implements CargaTensaoService {

	@Autowired
	private CargaTensaoRepository cargaTensaoRepository;

	public static CargaTensaoDTO entityToDto(CargaTensao entity) {
		CargaTensaoDTO dto = new CargaTensaoDTO();
		dto.setId(entity.getId());
		dto.setValor(dto.getValor());
		dto.setData(entity.getData());
		return dto;
	}

	public static CargaTensao dtoToEntity(CargaTensaoDTO dto) {
		CargaTensao entity = new CargaTensao();
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setValor(dto.getValor());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<CargaTensaoDTO> findAll() {
		List<CargaTensao> cargaTensoes = this.cargaTensaoRepository.findAll();

		return cargaTensoes.stream().map((cargaTensao) -> {
			return CargaTensaoServiceImpl.entityToDto(cargaTensao);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public CargaTensaoDTO findById(final BigInteger id) {
		CargaTensao cargaTensao = this.cargaTensaoRepository.findById(id);
		return CargaTensaoServiceImpl.entityToDto(cargaTensao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(CargaTensaoDTO dto) {
		CargaTensao cargaTensao = CargaTensaoServiceImpl.dtoToEntity(dto);
		this.cargaTensaoRepository.save(cargaTensao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CargaTensaoDTO dto) {
		CargaTensao cargaTensao = this.cargaTensaoRepository.findById(dto.getId());
		this.cargaTensaoRepository.update(cargaTensao);
	}
	
}
