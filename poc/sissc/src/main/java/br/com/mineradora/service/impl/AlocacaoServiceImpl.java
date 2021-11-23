package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.AlocacaoDTO;
import br.com.mineradora.entity.Alocacao;
import br.com.mineradora.repository.AlocacaoRepository;
import br.com.mineradora.service.AlocacaoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Service
public class AlocacaoServiceImpl implements AlocacaoService {

	@Autowired
	private AlocacaoRepository alocacaoRepository;
	
	public static AlocacaoDTO entityToDto(Alocacao alocacao) {
		AlocacaoDTO dto = new AlocacaoDTO();
		dto.setId(alocacao.getId());
		dto.setDataInclusao(alocacao.getDataInclusao());
		return dto;
	}
	
	public static Alocacao dtoToEntity(AlocacaoDTO dto) {
		Alocacao entity = new Alocacao();
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<AlocacaoDTO> findAll() {
		List<Alocacao> alocacoes = this.alocacaoRepository.findAll();
		return alocacoes.stream().map((alocacao) -> AlocacaoServiceImpl.entityToDto(alocacao)).collect(Collectors.toList());	
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS , isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AlocacaoDTO findById(final BigInteger id) {
		Alocacao alocacao = this.alocacaoRepository.findById(id);
		return AlocacaoServiceImpl.entityToDto(alocacao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AlocacaoDTO dto) {
		Alocacao alocacao = AlocacaoServiceImpl.dtoToEntity(dto);
		this.alocacaoRepository.save(alocacao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AlocacaoDTO dto) {
		Alocacao alocacao = this.alocacaoRepository.findById(dto.getId());
		this.alocacaoRepository.update(alocacao);
	}

}
