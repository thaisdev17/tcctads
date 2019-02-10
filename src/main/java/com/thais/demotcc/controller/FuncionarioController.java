
package com.thais.demotcc.controller;

import com.thais.demotcc.model.Funcionario;
import com.thais.demotcc.services.FuncionarioService;
import java.util.List;
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
@RequestMapping(value = "/funcionario")
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;
    
    /*########################################################################*/
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarFuncionario(@RequestBody Funcionario funcionario){
        funcionarioService.cadastrarFuncionario(funcionario);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@PathVariable é utilizado quando o valor da variável é passada diretamente na URL. Por isso se usa '/{id}' em 'value'.
    ResponseEntity<Funcionario> consultarProprietario(@PathVariable Long id){
        
        Funcionario funcionario;
        try{
            funcionario = funcionarioService.consultarFuncionario(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(funcionario, HttpStatus.OK);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/alterar", method = RequestMethod.PUT, 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity alterarFuncionario(@RequestBody Funcionario funcionario){
        funcionarioService.alterarFuncionario(funcionario);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/desativar/{id}", method = RequestMethod.DELETE)
    ResponseEntity desativarFuncionario(@PathVariable Long id){
        funcionarioService.desativarFuncionario(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/buscartodos", method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Funcionario> mostraTodosFuncionarios() {
        List<Funcionario> func;
        func = funcionarioService.buscaTodos();

        return new ResponseEntity(func, HttpStatus.OK);
    }
}
