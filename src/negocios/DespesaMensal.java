/*
 * DespesaMensal.java
 *
 * Created on 29 de Novembro de 2002, 11:20
 */

package negocios;

/**
 *
 * @author  leme
 */
public class DespesaMensal extends PoliticaDeDespesa {
    
    private int qtasPrestacoes;
    
    /** Creates a new instance of DespesaMensal */
    public DespesaMensal(int q) {
        qtasPrestacoes = q;
    }
    
    public Quantity efetuaDespesa(Quantity valor) {
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
