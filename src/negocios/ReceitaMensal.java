/*
 * ReceitaMensal.java
 *
 * Created on 29 de Novembro de 2002, 11:22
 */

package negocios;

/**
 *
 * @author  leme
 */
public class ReceitaMensal extends PoliticaDeReceita {
    
    private int qtasPrestacoes;
    
    /** Creates a new instance of ReceitaMensal */
    public ReceitaMensal(int q) {
        qtasPrestacoes = q;
    }
    
    public Quantity efetuaReceita(Quantity valor) {
        qtasPrestacoes--;
        return valor;
    }
    
    public boolean haPrestacoes() {
        if (qtasPrestacoes > 0)
            return true;
        else
            return false;
    }
    
}
