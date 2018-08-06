/*
 * HistoricoContabil.java
 *
 * Created on 29 de Novembro de 2002, 11:18
 */

package negocios;

import java.util.*;

/**
 *
 * @author  leme
 */
public class HistoricoContabil {
    
    private Quantity receita,
                     despesa;
    int ano;
    String mes;
    private ArrayList lancamentos;
    
    /** Creates a new instance of HistoricoContabil */
    public HistoricoContabil(Quantity r, Quantity d, int a, String m, ArrayList l) {
        receita = r;
        despesa = d;
        ano = a;
        mes = m;
        lancamentos = l;
    }
    
    public HistoricoContabil(Quantity r, Quantity d, int a, String m)
    {
        receita = r;
        despesa = d;
        ano = a;
        mes = m;
        lancamentos = new ArrayList();
    }

    public void adicionaLancamento(String l)
    {
        lancamentos.add(l);
    }
    
    public void removeLancamento(String l)
    {
        lancamentos.remove(l);
    }
    
    public Quantity getReceita()
    {
        return receita;
    }
    
    public void setReceita(Quantity r)
    {
        receita = r;
    }
    
    public Quantity getDespesa()
    {
        return despesa;
    }
    
    public void setDespesa(Quantity d)
    {
        despesa = d;
    }
    
    public int getAno()
    {
        return ano;
    }
    
    public void setAno(int a)
    {
        ano = a;
    }
    
    public String getMes()
    {
        return mes;
    }
    
    public void setMes(String m)
    {
        mes = m;
    }
    
    public ArrayList getLancamentos()
    {
        return lancamentos;
    }
    
    public Quantity obtemSaldo()
    {
        return Quantity.subtracao(receita,despesa);
    }
    
}
