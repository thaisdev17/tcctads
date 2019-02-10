
package com.thais.demotcc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Thais
 */

@Entity
@PrimaryKeyJoinColumn(name="idPessoa")
public class Proprietario extends PessoaFisica {
    
    private double prolabore;
    private String email;
    private String senha;

    public double getProlabore() {
        return prolabore;
    }

    public void setProlabore(double prolabore) {
        this.prolabore = prolabore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
