/*
 * Quantity.java
 *
 * Created on 6 de Dezembro de 2002, 20:35
 */

package negocios;

/**
 *
 * @author  leme
 */
public class Quantity {
    
    private double valor;
    private Unit unidade;
    
    /** Creates a new instance of Quantity */
    public Quantity(double v, Unit u) {
        valor = v;
        unidade = u;
    }
    
    public double getValor()
    {
        return valor;
    }
    
    public void setValor(int v)
    {
        valor = v;
    }
    
    public static Quantity soma(Quantity a, Quantity b)
    {
        return new Quantity(a.getValor() + b.getValor(),Unit.get(Unit.REAL));
    }
    
    public static Quantity subtracao(Quantity a, Quantity b)
    {
        return new Quantity(a.getValor() - b.getValor(),Unit.get(Unit.REAL));
    }
    
}
