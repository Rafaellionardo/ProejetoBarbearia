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
public class Colaborador {
    
    private int codigo; 
    private String nomeCompleto; 
    private String funcao; 
    private String email; 
    private String usuario; 
    private String senhaUsuario; 
    private char tipoUsuario; 
    private int comissaoPorServico; 
    private boolean ativo; 
    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        if(!nomeCompleto.trim().isEmpty()){
         this.nomeCompleto = nomeCompleto;
       }
 
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        if(!funcao.trim().isEmpty()){
         this.funcao = funcao;
       }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {     
        if(!email.trim().isEmpty()){
            this.email = email;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if(!usuario.trim().isEmpty()){
                this.usuario = usuario;
        }
   }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public char getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(char tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getComissaoPorServico() {
        
        return comissaoPorServico;
    }

    public void setComissaoPorServico(int comissaoPorServico) {
        if(comissaoPorServico >= 0 && comissaoPorServico <= 100){
            this.comissaoPorServico = comissaoPorServico;
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String toString(){
        return this.nomeCompleto; 
    }
    
}
