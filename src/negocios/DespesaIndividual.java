/*
 * DespesaIndividual.java
 *
 * Created on 29 de Novembro de 2002, 11:20
 */

package negocios;

/**
 *
 * @author  leme
 */
public class DespesaIndividual extends PoliticaDeDespesa {
    
    /** Creates a new instance of DespesaIndividual */
    public DespesaIndividual() {
    }
    
    public Quantity efetuaDespesa(Quantity valor) {
        return valor;
    }
    
    public boolean haPrestacoes() {
        return false;
    }
    
}
