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

import br.com.mineradora.dto.BarragemDTO;
import br.com.mineradora.service.BarragemService;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@RestController
@RequestMapping("api/barragem")
public class BarragemResource {
	
	@Autowired
	private BarragemService  barragemService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.barragemService.findAll());
    }
	
	@RequestMapping(value = "{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") final BigInteger id) {
        return ResponseEntity.ok(this.barragemService.findById(id));
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody final BarragemDTO dto) {
		this.barragemService.update(dto);
        return ResponseEntity.ok().build();
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody final BarragemDTO dto) {
		this.barragemService.save(dto);
        return ResponseEntity.ok().build();
    } 

}
