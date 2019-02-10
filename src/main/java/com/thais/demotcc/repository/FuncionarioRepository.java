
package com.thais.demotcc.repository;

import com.thais.demotcc.model.Funcionario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Thais
 */
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
    public Funcionario findByEmailAndSenha(
            @Param("email") String email,
            @Param("senha") String senha
    );
    
    @Query(value = "SELECT f FROM Funcionario f ORDER BY f.id")
    public List<Funcionario> buscaEOrdenaPorID();
}
