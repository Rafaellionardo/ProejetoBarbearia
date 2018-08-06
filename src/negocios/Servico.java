package negocios;
import java.util.*;
import java.lang.*;

public class Servico {
    
    private Quantity preco;
    private String descricao;
    
    public Servico(String descricaoServ, Quantity precoServ) {
        preco = precoServ;
        descricao = descricaoServ;
    }
    
    public Quantity getPreco() {
        return preco;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public String toString () {
        String s = descricao + " " + preco.getValor();
        return s;            
    }
}
