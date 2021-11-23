package br.com.mineradora.resource;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mineradora.dto.SensorDTO;
import br.com.mineradora.service.SensorService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@RestController
@RequestMapping("api/sensor")
public class SensorResource {

	@Autowired
	private SensorService sensorService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.sensorService.findAll());
    }
	
	@RequestMapping(value = "{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") final BigInteger id) {
        return ResponseEntity.ok(this.sensorService.findById(id));
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody final SensorDTO dto) {
		this.sensorService.update(dto);
        return ResponseEntity.ok().build();
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody final SensorDTO dto) {
		this.sensorService.save(dto);
        return ResponseEntity.ok().build();
    }
	
}
