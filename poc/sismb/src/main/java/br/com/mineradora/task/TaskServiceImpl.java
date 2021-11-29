package br.com.mineradora.task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mineradora.entity.Barragem;
import br.com.mineradora.entity.CargaTensao;
import br.com.mineradora.entity.Inclinometro;
import br.com.mineradora.entity.Piezometro;
import br.com.mineradora.entity.Vazao;
import br.com.mineradora.repository.BarragemRepository;
import br.com.mineradora.repository.CargaTensaoRepository;
import br.com.mineradora.repository.InclinometroRepository;
import br.com.mineradora.repository.PiezometroRepository;
import br.com.mineradora.repository.VazaoRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 28, 2021
 *
 */

@Slf4j
@Service
public class TaskServiceImpl {

	@Autowired
	private BarragemRepository barragemRepository;

	@Autowired
	private PiezometroRepository piezometroRepository;

	@Autowired
	private VazaoRepository vazaoRepository;

	@Autowired
	private CargaTensaoRepository cargaTensaoRepository;

	@Autowired
	private InclinometroRepository inclinometroRepository;

	//@Scheduled(fixedDelay = 30000, initialDelay = 30000)
	@Transactional(propagation = Propagation.REQUIRED)
	public void executar() {
		log.info("--- INICIANDO SIMULANÇÃO DE VALORES ---");

		List<Barragem> barragens = this.barragemRepository.findAll();

		for (Barragem barragem : barragens) {
			piezometro(barragem.getId());
			vazao(barragem.getId());
			cargaTensao(barragem.getId());
			inclinometro(barragem.getId());
		}

		log.info("--- TEMINANDO SIMULANÇÃO DE VALORES ---");
	}

	private void piezometro(final BigInteger id) {
		Piezometro actual = this.piezometroRepository.findActual(id);

		if (actual != null) {
			log.info("--- GERANDO SIMULANÇÃO PIEZOMETRO  ---");
			Piezometro entity = new Piezometro();
			entity.setData(LocalDateTime.now());
			entity.setSensor(actual.getSensor());
			entity.setGeometry(actual.getGeometry());

			BigDecimal value = BigDecimal.valueOf(new Random().doubles(1, 100.00).findAny().getAsDouble()).setScale(2,
					RoundingMode.HALF_DOWN);
			entity.setValor(value);

			value = BigDecimal.valueOf(new Random().doubles(1, 100.00).findAny().getAsDouble()).setScale(2,
					RoundingMode.HALF_DOWN);
			entity.setTemperatura(value);
			
			this.piezometroRepository.save(entity);
		}
	}

	private void vazao(final BigInteger id) {
		Vazao actual = this.vazaoRepository.findActual(id);

		if (actual != null) {
			log.info("--- GERANDO SIMULANÇÃO VAZAO  ---");
			Vazao entity = new Vazao();
			entity.setData(LocalDateTime.now());
			entity.setSensor(actual.getSensor());
			entity.setGeometry(actual.getGeometry());

			BigDecimal value = BigDecimal.valueOf(new Random().doubles(1, 100.00).findAny().getAsDouble()).setScale(2,
					RoundingMode.HALF_DOWN);
			entity.setValor(value);
			
			this.vazaoRepository.save(entity);
		}
	}

	private void cargaTensao(final BigInteger id) {
		CargaTensao actual = this.cargaTensaoRepository.findActual(id);

		if (actual != null) {
			log.info("--- GERANDO SIMULANÇÃO CARGATENSAO  ---");
			CargaTensao entity = new CargaTensao();
			entity.setData(LocalDateTime.now());
			entity.setSensor(actual.getSensor());
			entity.setGeometry(actual.getGeometry());

			BigDecimal value = BigDecimal.valueOf(new Random().doubles(1, 100.00).findAny().getAsDouble()).setScale(2,
					RoundingMode.HALF_DOWN);
			entity.setValor(value);
			
			this.cargaTensaoRepository.save(entity);
		}
	}

	private void inclinometro(final BigInteger id) {
		Inclinometro actual = this.inclinometroRepository.findActual(id);

		if (actual != null) {
			log.info("--- GERANDO SIMULANÇÃO INCLINOMETRO  ---");
			Inclinometro entity = new Inclinometro();
			entity.setData(LocalDateTime.now());
			entity.setSensor(actual.getSensor());
			entity.setGeometry(actual.getGeometry());

			BigDecimal value = BigDecimal.valueOf(new Random().doubles(1, 100.00).findAny().getAsDouble()).setScale(2,
					RoundingMode.HALF_DOWN);
			entity.setValor(value);
			
			 this.inclinometroRepository.save(entity);;
		}
	}

}
