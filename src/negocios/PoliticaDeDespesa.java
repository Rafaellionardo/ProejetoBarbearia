/*
 * PoliticaDeDespesa.java
 *
 * Created on 29 de Novembro de 2002, 11:19
 */

package negocios;

/**
 *
 * @author  leme
 */
public abstract class PoliticaDeDespesa {
    
    /** Creates a new instance of PoliticaDeDespesa */
    protected PoliticaDeDespesa() {
    }
    
    public abstract Quantity efetuaDespesa(Quantity valor);
    
    public abstract boolean haPrestacoes();    
}
