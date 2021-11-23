package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.InclinometroDTO;
import br.com.mineradora.entity.Inclinometro;
import br.com.mineradora.repository.InclinometroRepository;
import br.com.mineradora.service.InclinometroService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Service
public class InclinometroServiceImpl implements InclinometroService {

	@Autowired
	private InclinometroRepository inclinometroRepository;

	public static InclinometroDTO entityToDto(Inclinometro entity) {
		InclinometroDTO dto = new InclinometroDTO();
		dto.setId(entity.getId());
		dto.setValor(dto.getValor());
		dto.setData(entity.getData());
		return dto;
	}

	public static Inclinometro dtoToEntity(InclinometroDTO dto) {
		Inclinometro entity = new Inclinometro();
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setValor(dto.getValor());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<InclinometroDTO> findAll() {
		List<Inclinometro> inclinometros = this.inclinometroRepository.findAll();
		
		return inclinometros.stream().map((inclinometro) -> {
			return InclinometroServiceImpl.entityToDto(inclinometro);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public InclinometroDTO findById(final BigInteger id) {
		Inclinometro inclinometro = this.inclinometroRepository.findById(id);
		return InclinometroServiceImpl.entityToDto(inclinometro);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(InclinometroDTO dto) {
		Inclinometro inclinometro = InclinometroServiceImpl.dtoToEntity(dto);
		this.inclinometroRepository.save(inclinometro);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(InclinometroDTO dto) {
		Inclinometro inclinometro = this.inclinometroRepository.findById(dto.getId());
		this.inclinometroRepository.update(inclinometro);
	}
	
}
