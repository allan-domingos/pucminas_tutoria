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

import br.com.mineradora.dto.AlocacaoDTO;
import br.com.mineradora.entity.Alocacao;
import br.com.mineradora.entity.Ativo;
import br.com.mineradora.entity.Local;
import br.com.mineradora.entity.Patrimonio;
import br.com.mineradora.repository.AlocacaoRepository;
import br.com.mineradora.repository.AtivoRepository;
import br.com.mineradora.repository.LocalRepository;
import br.com.mineradora.repository.PatrimonioRepository;
import br.com.mineradora.service.AlocacaoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Service
public class AlocacaoServiceImpl extends AbstractService implements AlocacaoService {

	@Autowired
	private AlocacaoRepository alocacaoRepository;

	@Autowired
	private AtivoRepository ativoRepository;

	@Autowired
	private PatrimonioRepository patrimonioRepository;

	@Autowired
	private LocalRepository localRepositorye;

	public static AlocacaoDTO entityToDto(Alocacao alocacao) {
		if(alocacao == null)
			return null;
		
		AlocacaoDTO dto = new AlocacaoDTO();
		dto.setId(alocacao.getId());
		dto.setDataInclusao(alocacao.getDataInclusao());
		dto.setIdAquisicao(alocacao.getIdAquisicao());
		return dto;
	}

	public static Alocacao dtoToEntity(AlocacaoDTO dto) {
		if(dto == null)
			return null;
		
		Alocacao entity = new Alocacao();
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setIdAquisicao(dto.getIdAquisicao());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<AlocacaoDTO> findAll() {
		List<Alocacao> alocacoes = this.alocacaoRepository.findAll();
		return alocacoes.stream().map(alocacao -> {
			AlocacaoDTO dto = AlocacaoServiceImpl.entityToDto(alocacao);
			dto.setAtivo(AtivoServiceImpl.entityToDto(alocacao.getAtivo()));
			dto.setPatrimonio(PatrimonioServiceImpl.entityToDto(alocacao.getPatrimonio()));
			dto.setLocal(LocalServiceImpl.entityToDto(alocacao.getLocal()));
			return dto;
		}).collect(Collectors.toList());
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AlocacaoDTO findById(final BigInteger id) {
		Alocacao alocacao = this.alocacaoRepository.findById(id);
		AlocacaoDTO dto = AlocacaoServiceImpl.entityToDto(alocacao);
		dto.setAtivo(AtivoServiceImpl.entityToDto(alocacao.getAtivo()));
		dto.setPatrimonio(PatrimonioServiceImpl.entityToDto(alocacao.getPatrimonio()));
		dto.setLocal(LocalServiceImpl.entityToDto(alocacao.getLocal()));
		return dto;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AlocacaoDTO dto) {
		Ativo ativo = this.ativoRepository.findById(dto.getAtivo().getId());
		Patrimonio patrimonio = this.patrimonioRepository.findById(dto.getPatrimonio().getId());
		Local local = this.localRepositorye.findById(dto.getLocal().getId());
		Alocacao alocacao = AlocacaoServiceImpl.dtoToEntity(dto);
		alocacao.setDataInclusao(LocalDateTime.now());
		alocacao.setAtivo(ativo);
		alocacao.setPatrimonio(patrimonio);
		alocacao.setLocal(local);
		this.alocacaoRepository.save(alocacao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AlocacaoDTO dto) {
		Ativo ativo = this.ativoRepository.findById(dto.getAtivo().getId());
		Patrimonio patrimonio = this.patrimonioRepository.findById(dto.getPatrimonio().getId());
		Local local = this.localRepositorye.findById(dto.getLocal().getId());
		Alocacao alocacao = this.alocacaoRepository.findById(dto.getId());
		alocacao.setAtivo(ativo);
		alocacao.setPatrimonio(patrimonio);
		alocacao.setLocal(local);
		alocacao.setIdAquisicao(dto.getIdAquisicao());
		this.alocacaoRepository.update(alocacao);
	}

}
