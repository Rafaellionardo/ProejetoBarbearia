/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.sql.Date;

/**
 *
 * @author renan
 */
public class Cliente {
    
    private int codigo;
    private Endereco endereco; 
    private String nomeCompleto; 
    private String email; 
    private String celular; 
    private Date dataNascimento; 
    private String sexo; 
    private Date dataPrimeiroServ; 
    private Date dataUltimoServ; 
    private boolean ativo; 
    
    public boolean getAtivo() {
        return  ativo;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataPrimeiroServ() {
        return dataPrimeiroServ;
    }

    public void setDataPrimeiroServ(Date dataPrimeiroServ) {
        this.dataPrimeiroServ = dataPrimeiroServ;
    }

    public Date getDataUltimoServ() {
        return dataUltimoServ;
    }

    public void setDataUltimoServ(Date dataUltimoServ) {
        this.dataUltimoServ = dataUltimoServ;
    }

    public boolean isAtivo() {
        return ativo;
    }

    
   
    
}
