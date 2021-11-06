package br.com.mineradora.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.SolicitacaoDTO;
import br.com.mineradora.entity.Solicitacao;
import br.com.mineradora.repository.SolicitacaoRepository;
import br.com.mineradora.service.SolicitacaoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	public static SolicitacaoDTO entityToDto(Solicitacao solicitacao) {
		SolicitacaoDTO dto = new SolicitacaoDTO();
		dto.setId(solicitacao.getId());
		dto.setDataInclusao(solicitacao.getDataInclusao());
		dto.setQuantidade(solicitacao.getQuantidade());
		return dto;
	}
	
	public static Solicitacao dtoToEntity(SolicitacaoDTO dto) {
		Solicitacao entity = new Solicitacao();
		entity.setId(dto.getId());
		entity.setQuantidade(dto.getQuantidade());
		entity.setDataInclusao(dto.getDataInclusao());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public SolicitacaoDTO findById(Long id) {
		Solicitacao solicitacao = this.solicitacaoRepository.findById(id);
		return SolicitacaoServiceImpl.entityToDto(solicitacao);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<SolicitacaoDTO> findAll() {
		List<Solicitacao> solicitacoes = this.solicitacaoRepository.findAll();
		return solicitacoes.stream().map((solicitacao) -> SolicitacaoServiceImpl.entityToDto(solicitacao)).collect(Collectors.toList());	
	}

	@Override
	public void save(SolicitacaoDTO dto) {
		Solicitacao solicitacao = SolicitacaoServiceImpl.dtoToEntity(dto);
		this.solicitacaoRepository.save(solicitacao);
	}

	@Override
	public void update(SolicitacaoDTO dto) {
		Solicitacao solicitacao = this.solicitacaoRepository.findById(dto.getId());
		solicitacao.setQuantidade(dto.getQuantidade());
		this.solicitacaoRepository.update(solicitacao);
	}

}
