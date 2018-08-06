/*
 * Receita.java
 *
 * Created on 29 de Novembro de 2002, 11:21
 */

package negocios;

import java.util.*;

/**
 *
 * @author  leme
 */
public class Receita {
    
    private Date data;
    private String descricao;
    private Quantity valor;
    private PoliticaDeReceita politica;
    
    /** Creates a new instance of Receita */
    public Receita(Date d, String desc, Quantity v, PoliticaDeReceita p) {
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

    public PoliticaDeReceita getPolitica()
    {
        return politica;
    }
    
    public void setPolitica(PoliticaDeReceita p)
    {
        politica = p;
    }
    
    public Quantity efetuaReceita()
    {
        return politica.efetuaReceita(valor);
    }
    
    public boolean haPrestacoes()
    {
        return politica.haPrestacoes();
    }
}
