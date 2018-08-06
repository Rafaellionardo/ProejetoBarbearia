/*
 * Despesa.java
 *
 * Created on 29 de Novembro de 2002, 11:18
 */

package negocios;

import java.util.*;

/**
 *
 * @author  leme
 */
public class Despesa {
    
    private Date data;
    private String descricao;
    private Quantity valor;
    private PoliticaDeDespesa politica;
    
    /** Creates a new instance of Despesa */
    public Despesa(Date d, String desc, Quantity v, PoliticaDeDespesa p) {
        data = d;
        descricao = desc;
        valor = v;
        politica = p;
    }

    public Date getData()
    {
        return data;
    }
    
    public void setData(Date d)
    {
        data = d;
    }
    
    public String getDescricao()
    {
        return descricao;
    }
    
    public void setDescricao(String d)
    {
        descricao = d;
    }
    
    public Quantity getValor()
    {
        return valor;
    }
    
    public void setValor(Quantity v)
    {
        valor = v;
    }
    
    public PoliticaDeDespesa getPolitica()
    {
        return politica;
    }
    
    public void setPolitica(PoliticaDeDespesa p)
    {
        politica = p;
    }
    
    public Quantity efetuaDespesa()
    {
        return politica.efetuaDespesa(valor);
    }
    
    public boolean haPrestacoes()
    {
        return politica.haPrestacoes();
    }
}
