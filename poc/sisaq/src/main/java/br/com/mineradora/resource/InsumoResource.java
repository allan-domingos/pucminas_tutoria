package br.com.mineradora.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mineradora.service.InsumoService;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 10 de abr. de 2021
 */
@RestController
@RequestMapping("api/insumo")
public class InsumoResource {
	
	@Autowired
	private InsumoService insumoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(null);
    }
	
}
