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

import br.com.mineradora.dto.AquisicaoDTO;
import br.com.mineradora.dto.SolicitacaoDTO;
import br.com.mineradora.entity.Aquisicao;
import br.com.mineradora.entity.Solicitacao;
import br.com.mineradora.repository.AquisicaoRepository;
import br.com.mineradora.repository.SolicitacaoRepository;
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

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	public static AquisicaoDTO entityToDto(Aquisicao aquisicao) {
		AquisicaoDTO dto = new AquisicaoDTO();
		dto.setId(aquisicao.getId());
		dto.setDataInclusao(aquisicao.getDataInclusao());
		dto.setNotaFiscal(aquisicao.getNotaFiscal());
		dto.setCnpj(aquisicao.getCnpj());
		dto.setValor(aquisicao.getValor());
		dto.setQuantidade(aquisicao.getQuantidade());
		return dto;
	}

	public static Aquisicao dtoToEntity(AquisicaoDTO dto) {
		Aquisicao entity = new Aquisicao();
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setQuantidade(dto.getQuantidade());
		entity.setNotaFiscal(dto.getNotaFiscal());
		entity.setCnpj(dto.getCnpj());
		entity.setValor(dto.getValor());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<AquisicaoDTO> findAll() {
		List<Aquisicao> aquisicoes = this.aquisicaoRepository.findAll();

		return aquisicoes.stream().map((aquisicao) -> {
			SolicitacaoDTO so = SolicitacaoServiceImpl.entityToDto(aquisicao.getSolicitacao());
			AquisicaoDTO aq = AquisicaoServiceImpl.entityToDto(aquisicao);
			aq.setSolicitacao(so);
			return aq;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AquisicaoDTO findById(final BigInteger id) {
		Aquisicao aquisicao = this.aquisicaoRepository.findById(id);
		SolicitacaoDTO so = SolicitacaoServiceImpl.entityToDto(aquisicao.getSolicitacao());
		AquisicaoDTO aq = AquisicaoServiceImpl.entityToDto(aquisicao);
		aq.setSolicitacao(so);
		return aq;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AquisicaoDTO dto) {
		Solicitacao solicitacao = this.solicitacaoRepository.findById(dto.getSolicitacao().getId());
		Aquisicao aquisicao = AquisicaoServiceImpl.dtoToEntity(dto);
		aquisicao.setDataInclusao(LocalDateTime.now());
		aquisicao.setSolicitacao(solicitacao);
		this.aquisicaoRepository.save(aquisicao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AquisicaoDTO dto) {
		Aquisicao aquisicao = this.aquisicaoRepository.findById(dto.getId());
		aquisicao.setQuantidade(dto.getQuantidade());
		aquisicao.setNotaFiscal(dto.getNotaFiscal());
		aquisicao.setValor(dto.getValor());
		aquisicao.setCnpj(dto.getCnpj());
		this.aquisicaoRepository.update(aquisicao);
	}

}
