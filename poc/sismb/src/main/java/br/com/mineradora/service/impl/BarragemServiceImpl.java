package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Polygon;
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
public class BarragemServiceImpl extends AbstractService implements BarragemService {

	@Autowired
	private BarragemRepository barragemRepository;

	public static BarragemDTO entityToDto(Barragem entity) {
		if(entity == null)
			return null;
		
		BarragemDTO dto = new BarragemDTO();
		dto.setId(entity.getId());
		dto.setDataInclusao(entity.getDataInclusao());
		dto.setNome(entity.getNome());
		return dto;
	}

	public static Barragem dtoToEntity(BarragemDTO dto) {
		if(dto == null)
			return null;
		
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
			BarragemDTO dto=  BarragemServiceImpl.entityToDto(barragem);
			dto.setCoordenadas(polygonToDoubleArray((Polygon)barragem.getGeometry()));
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public BarragemDTO findById(final BigInteger id) {
		Barragem barragem = this.barragemRepository.findById(id);
		BarragemDTO dto = BarragemServiceImpl.entityToDto(barragem);
		dto.setCoordenadas(polygonToDoubleArray((Polygon)barragem.getGeometry()));
		return dto;
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
