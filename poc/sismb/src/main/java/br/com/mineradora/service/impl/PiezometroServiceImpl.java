package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.PiezometroDTO;
import br.com.mineradora.entity.Piezometro;
import br.com.mineradora.repository.PiezometroRepository;
import br.com.mineradora.service.PiezometroService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Service
public class PiezometroServiceImpl implements PiezometroService {

	@Autowired
	private PiezometroRepository piezometroRepository;

	public static PiezometroDTO entityToDto(Piezometro entity) {
		PiezometroDTO dto = new PiezometroDTO();
		dto.setId(entity.getId());
		dto.setValor(dto.getValor());
		dto.setData(entity.getData());
		return dto;
	}

	public static Piezometro dtoToEntity(PiezometroDTO dto) {
		Piezometro entity = new Piezometro();
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setValor(dto.getValor());
		entity.setTemperatura(dto.getTemperatura());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<PiezometroDTO> findAll() {
		List<Piezometro> piezometros = this.piezometroRepository.findAll();
		
		return piezometros.stream().map((piezometro) -> {
			return PiezometroServiceImpl.entityToDto(piezometro);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public PiezometroDTO findById(final BigInteger id) {
		Piezometro piezometro = this.piezometroRepository.findById(id);
		return PiezometroServiceImpl.entityToDto(piezometro);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(PiezometroDTO dto) {
		Piezometro piezometro = PiezometroServiceImpl.dtoToEntity(dto);
		this.piezometroRepository.save(piezometro);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PiezometroDTO dto) {
		Piezometro piezometro = this.piezometroRepository.findById(dto.getId());
		this.piezometroRepository.update(piezometro);
	}
	
}
