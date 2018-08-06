/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author renan
 */
public class Endereco {
    
    private int codigo; 
    private Cidade cidade; 
    private String bairro; 
    private String rua;
    private String numero; 
    private String complemento; 
    private boolean ativo; 
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if(!bairro.trim().isEmpty()){
            this.bairro = bairro;
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
       if(!rua.trim().isEmpty()){
            this.rua = rua;
       }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(!numero.trim().isEmpty()){
            this.numero = numero;
        }
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        
            this.complemento = complemento;
        
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
    
    
    

}
