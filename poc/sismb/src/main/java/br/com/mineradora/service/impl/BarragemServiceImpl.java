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

import br.com.mineradora.dto.BarragemDTO;
import br.com.mineradora.entity.Barragem;
import br.com.mineradora.repository.BarragemRepository;
import br.com.mineradora.service.BarragemService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Service
public class BarragemServiceImpl implements BarragemService {

	@Autowired
	private BarragemRepository barragemRepository;

	public static BarragemDTO entityToDto(Barragem entity) {
		BarragemDTO dto = new BarragemDTO();
		dto.setId(entity.getId());
		dto.setDataInclusao(entity.getDataInclusao());
		dto.setNome(dto.getNome());
		return dto;
	}

	public static Barragem dtoToEntity(BarragemDTO dto) {
		Barragem entity = new Barragem();
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setNome(dto.getNome());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<BarragemDTO> findAll() {
		List<Barragem> barragens = this.barragemRepository.findAll();

		return barragens.stream().map((barragem) -> {
			return BarragemServiceImpl.entityToDto(barragem);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public BarragemDTO findById(final BigInteger id) {
		Barragem aquisicao = this.barragemRepository.findById(id);
		return BarragemServiceImpl.entityToDto(aquisicao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(BarragemDTO dto) {
		Barragem barragem = BarragemServiceImpl.dtoToEntity(dto);
		barragem.setDataInclusao(LocalDateTime.now());
		this.barragemRepository.save(barragem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(BarragemDTO dto) {
		Barragem barragem = this.barragemRepository.findById(dto.getId());
		this.barragemRepository.update(barragem);
	}
	
}
