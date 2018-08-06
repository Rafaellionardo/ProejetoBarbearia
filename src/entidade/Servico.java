package entidade;

public class Servico {
   
    private int  codigo; 
    private String descricao; 
    private double valor; 
    private boolean ativo; 
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
       if(!descricao.trim().isEmpty()){
         this.descricao = descricao;
       }
    }

    public double getValor() {
        
        return valor;
    }
    
    public void setValor(double valor) {
        //Verificar se o valor é maior que zero, evitando serviços com valores negativos
        if(valor > 0){
            this.valor = valor;
        }else{
          
        }
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String toString(){
        return this.descricao + " - "+ this.valor; 
    }

    
}
