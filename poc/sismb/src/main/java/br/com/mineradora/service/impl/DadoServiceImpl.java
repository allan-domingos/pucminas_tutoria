package br.com.mineradora.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.dto.DadoDTO;
import br.com.mineradora.entity.CargaTensao;
import br.com.mineradora.entity.Inclinometro;
import br.com.mineradora.entity.Piezometro;
import br.com.mineradora.entity.Vazao;
import br.com.mineradora.repository.CargaTensaoRepository;
import br.com.mineradora.repository.InclinometroRepository;
import br.com.mineradora.repository.PiezometroRepository;
import br.com.mineradora.repository.VazaoRepository;
import br.com.mineradora.service.DadoService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Service
public class DadoServiceImpl extends AbstractService implements DadoService {

	@Autowired
	private PiezometroRepository piezometroRepository;
	
	@Autowired
	private VazaoRepository vazaoRepository;
	
	@Autowired
	private InclinometroRepository inclinometroRepository;
	
	@Autowired
	private CargaTensaoRepository cargaTensaoRepository;

	public static DadoDTO piezometroToDto(Piezometro entity) {
		if(entity == null)
			return null;
		
		DadoDTO dto = new DadoDTO();
		dto.setNome(entity.getSensor().getNome());
		dto.setValor(entity.getValor());
		dto.setData(entity.getData());
		return dto;
	}
	
	public static DadoDTO temperaturaToDto(Piezometro entity) {
		if(entity == null)
			return null;
		
		DadoDTO dto = new DadoDTO();
		dto.setNome(entity.getSensor().getNome() + " - Temp");
		dto.setValor(entity.getTemperatura());
		dto.setData(entity.getData());
		return dto;
	}
	
	public static DadoDTO vazaoToDto(Vazao entity) {
		if(entity == null)
			return null;
		
		DadoDTO dto = new DadoDTO();
		dto.setNome(entity.getSensor().getNome());
		dto.setValor(entity.getValor());
		dto.setData(entity.getData());
		return dto;
	}
	
	public static DadoDTO cargaTensaoToDto(CargaTensao entity) {
		if(entity == null)
			return null;
		
		DadoDTO dto = new DadoDTO();
		dto.setNome(entity.getSensor().getNome());
		dto.setValor(entity.getValor());
		dto.setData(entity.getData());
		return dto;
	}
	
	public static DadoDTO inclinometroToDto(Inclinometro entity) {
		if(entity == null)
			return null;
		
		DadoDTO dto = new DadoDTO();
		dto.setNome(entity.getSensor().getNome());
		dto.setValor(entity.getValor());
		dto.setData(entity.getData());
		return dto;
	}
	

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<DadoDTO> findAtual(final BigInteger id) {
		
		List<DadoDTO> dados = new ArrayList<>();
		
		DadoDTO dado =  cargaTensaoToDto(this.cargaTensaoRepository.findActual(id));
		dados.add(dado);
		
		dado =  vazaoToDto(this.vazaoRepository.findActual(id));
		dados.add(dado);
		
		dado =  inclinometroToDto(this.inclinometroRepository.findActual(id));
		dados.add(dado);
		
		Piezometro piezometro = this.piezometroRepository.findActual(id);
		
		dado =  piezometroToDto(piezometro);
		dados.add(dado);
		
		dado =  temperaturaToDto(piezometro);
		dados.add(dado);
		
		return dados;
	}
	
}
