
package com.thais.demotcc.services;

import com.thais.demotcc.model.Proprietario;
import com.thais.demotcc.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Thais
 */

@Service
public class ProprietarioService {
    
    @Autowired
    ProprietarioRepository proprietarioRepository;
    
    public void cadastrarProprietario(Proprietario proprietario){
        proprietarioRepository.save(proprietario);
    }
    
    public Proprietario consultarProprietario(Long id){
        return proprietarioRepository.findById(id).get();
    }
    
    public void alterarProprietario(Proprietario proprietario){
        proprietarioRepository.save(proprietario);
    }
    
    public void desativarProprietario(Long id){
        proprietarioRepository.deleteById(id);
    }
    
    public Proprietario autenticarProprietario(Proprietario proprietario){
        return proprietarioRepository.findById(proprietario.getIdPessoa()).get();
    }
}
