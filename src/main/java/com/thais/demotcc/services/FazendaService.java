
package com.thais.demotcc.services;

import com.thais.demotcc.model.Fazenda;
import com.thais.demotcc.repository.FazendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Thais
 */

@Service
public class FazendaService {
    
    @Autowired
    FazendaRepository fazendaRepository;
    
    public void cadastrarFazenda(Fazenda fazenda){
        fazendaRepository.save(fazenda);
    }
    
    public Fazenda consultarFazenda(Long id){
        return fazendaRepository.findById(id).get();
    }
    
    public void alterarFazenda(Fazenda fazenda){
        fazendaRepository.save(fazenda);
    }
    
    public void desativarFazenda(Long id){
        fazendaRepository.deleteById(id);
    }
    
}
