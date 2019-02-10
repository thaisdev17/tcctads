
package com.thais.demotcc.controller;

import com.thais.demotcc.model.Fazenda;
import com.thais.demotcc.services.FazendaService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Thais
 */

@RestController
@RequestMapping(value = "/fazenda")
public class FazendaController {
    
    @Autowired
    FazendaService fazendaService;
    
    /*########################################################################*/
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarFazenda(@RequestBody Fazenda fazenda){
        fazendaService.cadastrarFazenda(fazenda);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Fazenda> consultarFazenda(@PathVariable Long id){
        
        Fazenda fazenda;
        try{
            fazenda = fazendaService.consultarFazenda(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(fazenda, HttpStatus.OK);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/alterar", method = RequestMethod.PUT, 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity alterarFazenda(@RequestBody Fazenda fazenda){
        fazendaService.alterarFazenda(fazenda);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/desativar", method = RequestMethod.DELETE)
    ResponseEntity desativarFazenda(@PathVariable Long id){
        fazendaService.desativarFazenda(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
