
package com.thais.demotcc.services;

import com.thais.demotcc.model.Funcionario;
import com.thais.demotcc.repository.FuncionarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Thais
 */

@Service
public class FuncionarioService {
    
    @Autowired
    FuncionarioRepository funcionarioRepository;
    
     public void cadastrarFuncionario(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
    }
    
    public Funcionario consultarFuncionario(Long id){
        return funcionarioRepository.findById(id).get();
    }
    
    public void alterarFuncionario(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
    }
    
    public void desativarFuncionario(Long id){
        funcionarioRepository.deleteById(id);
    }
    
     public List<Funcionario> buscaTodos(){
        return funcionarioRepository.buscaEOrdenaPorID();
    }
     
    public Funcionario autenticarFuncionario(Funcionario funcionario){
        Funcionario funcionarioAutenticado;
        funcionarioAutenticado = funcionarioRepository.findByEmailAndSenha(funcionario.getEmail(), funcionario.getSenha());
        return funcionarioAutenticado;
    }
}
