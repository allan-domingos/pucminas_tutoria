package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.SensorDTO;
import br.com.mineradora.entity.Sensor;
import br.com.mineradora.repository.SensorRepository;
import br.com.mineradora.service.SensorService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Service
public class SensorServiceImpl extends AbstractService implements SensorService {

	@Autowired
	private SensorRepository sensorRepository;

	public static SensorDTO entityToDto(Sensor entity) {
		SensorDTO dto = new SensorDTO();
		dto.setId(entity.getId());
		dto.setDataInclusao(entity.getDataInclusao());
		dto.setNome(entity.getNome());
		return dto;
	}

	public static Sensor dtoToEntity(SensorDTO dto) {
		Sensor entity = new Sensor();
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setNome(dto.getNome());
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<SensorDTO> findAll() {
		List<Sensor> sensores = this.sensorRepository.findAll();
		
		return sensores.stream().map((sensor) -> {
			 SensorDTO dto = SensorServiceImpl.entityToDto(sensor);
			 dto.setBarragem(BarragemServiceImpl.entityToDto(sensor.getBarragem()));
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public SensorDTO findById(final BigInteger id) {
		Sensor sensor = this.sensorRepository.findById(id);
		SensorDTO dto = SensorServiceImpl.entityToDto(sensor);
		dto.setBarragem(BarragemServiceImpl.entityToDto(sensor.getBarragem()));
		return dto;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(SensorDTO dto) {
		Sensor sensor = SensorServiceImpl.dtoToEntity(dto);
		this.sensorRepository.save(sensor);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(SensorDTO dto) {
		Sensor sensor = this.sensorRepository.findById(dto.getId());
		this.sensorRepository.update(sensor);
	}
	
}
