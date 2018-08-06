/*
 * Unit.java
 *
 * Created on 6 de Dezembro de 2002, 20:38
 */

package negocios;

import java.util.*;

/**
 *
 * @author  leme
 */
public class Unit {
    
    public static int REAL = 0;
    public static int DOLAR = 1;
    
    private String nome;
    private ArrayList taxasDeConversao;
    
    public static Unit get(int unidade)
    {
        if (unidade == Unit.REAL)
            return new Unit("real");
        else
            if (unidade == Unit.DOLAR)
                return new Unit("dólar");
            else
                return null;
    }
    
    /** Creates a new instance of Unit */
    protected Unit(String n) {
        nome = n;
        taxasDeConversao = new ArrayList();
    }
    
    public String getNome()
    {
        return nome;
    }
    
}
