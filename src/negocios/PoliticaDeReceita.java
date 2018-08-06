/*
 * PoliticaDeReceita.java
 *
 * Created on 29 de Novembro de 2002, 11:22
 */

package negocios;

/**
 *
 * @author  leme
 */
public abstract class PoliticaDeReceita {
    
    /** Creates a new instance of PoliticaDeReceita */
    protected PoliticaDeReceita() {
    }

    public abstract Quantity efetuaReceita(Quantity valor);
    
    public abstract boolean haPrestacoes();
    
}
