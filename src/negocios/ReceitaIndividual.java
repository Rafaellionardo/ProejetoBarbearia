/*
 * ReceitaIndividual.java
 *
 * Created on 29 de Novembro de 2002, 11:23
 */

package negocios;

/**
 *
 * @author  leme
 */
public class ReceitaIndividual extends PoliticaDeReceita {
    
    /** Creates a new instance of ReceitaIndividual */
    public ReceitaIndividual() {
    }
    
    public Quantity efetuaReceita(Quantity valor) {
        return valor;
    }
    
    public boolean haPrestacoes() {
        return false;
    }
    
}
