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

import br.com.mineradora.dto.PatrimonioDTO;
import br.com.mineradora.service.PatrimonioService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@RestController
@RequestMapping("api/patrimonio")
public class PatrimonioResource {
	
	@Autowired
	private PatrimonioService  patrimonioService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.patrimonioService.findAll());
    }
	
	@RequestMapping(value = "{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") final BigInteger id) {
        return ResponseEntity.ok(this.patrimonioService.findById(id));
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody final PatrimonioDTO dto) {
		this.patrimonioService.update(dto);
        return ResponseEntity.ok().build();
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody final PatrimonioDTO dto) {
		this.patrimonioService.save(dto);
        return ResponseEntity.ok().build();
    } 
	
	@RequestMapping(value ="ativo/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByAtivoId(@PathVariable("id") final BigInteger id) {
        return ResponseEntity.ok(this.patrimonioService.findAllByAtivoId(id));
    }
	
}
