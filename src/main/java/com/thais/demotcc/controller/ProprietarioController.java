
package com.thais.demotcc.controller;

import static com.thais.demotcc.config.Autenticar.KEY;
import com.thais.demotcc.model.Proprietario;
import com.thais.demotcc.services.ProprietarioService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
@RequestMapping(value = "/proprietario")
public class ProprietarioController {
    
    @Autowired
    ProprietarioService proprietarioService;
    
    /*########################################################################*/
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarProprietario(@RequestBody Proprietario proprietario){
        proprietarioService.cadastrarProprietario(proprietario);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Proprietario> consultarProprietario(@PathVariable Long id){
        
        Proprietario proprietario;
        try{
            proprietario = proprietarioService.consultarProprietario(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(proprietario, HttpStatus.OK);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/alterar", method = RequestMethod.PUT, 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity alterarProprietario(@RequestBody Proprietario proprietario){
        proprietarioService.alterarProprietario(proprietario);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/desativar/{id}", method = RequestMethod.DELETE)
    ResponseEntity desativarProprietario(@PathVariable Long id){
        proprietarioService.desativarProprietario(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    /*########################################################################*/
    @RequestMapping(value = "/loginProprietario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity autenticarProprietario(@RequestBody Proprietario adminProprietario){
        
        Proprietario proprietarioAutenticado = proprietarioService.autenticarProprietario(adminProprietario);
        if (proprietarioAutenticado == null || proprietarioAutenticado.getEmail().isEmpty() || 
                proprietarioAutenticado.getSenha().isEmpty()) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(proprietarioAutenticado.getIdPessoa() + "");
        jwtBuilder.claim("proprietario", true);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        jwtBuilder.signWith(KEY);
        
        String token = jwtBuilder.compact();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
