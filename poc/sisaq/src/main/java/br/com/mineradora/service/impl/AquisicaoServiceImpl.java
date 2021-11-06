package br.com.mineradora.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.AquisicaoDTO;
import br.com.mineradora.entity.Aquisicao;
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

	public static AquisicaoDTO entityToDto(Aquisicao aquisicao) {
		AquisicaoDTO dto = new AquisicaoDTO();
		dto.setId(aquisicao.getId());
		dto.setDataInclusao(aquisicao.getDataInclusao());
		dto.setQuantidade(aquisicao.getQuantidade());
		return dto;
	}
	
	public static Aquisicao dtoToEntity(AquisicaoDTO dto) {
		Aquisicao entity = new Aquisicao();
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setQuantidade(dto.getQuantidade());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<AquisicaoDTO> findAll() {
		List<Aquisicao> aquisicoes = this.aquisicaoRepository.findAll();
		return aquisicoes.stream().map((aquisicao) -> AquisicaoServiceImpl.entityToDto(aquisicao)).collect(Collectors.toList());	
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AquisicaoDTO findById(final Long id) {
		Aquisicao aquisicao = this.aquisicaoRepository.findById(id);
		return AquisicaoServiceImpl.entityToDto(aquisicao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AquisicaoDTO dto) {
		Aquisicao aquisicao = AquisicaoServiceImpl.dtoToEntity(dto);
		this.aquisicaoRepository.save(aquisicao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AquisicaoDTO dto) {
		Aquisicao aquisicao = this.aquisicaoRepository.findById(dto.getId());
		aquisicao.setQuantidade(dto.getQuantidade());
		this.aquisicaoRepository.update(aquisicao);
	}

}
