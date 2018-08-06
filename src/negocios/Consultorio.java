/*
 * Consultorio.java
 *
 * Created on 29 de Novembro de 2002, 11:15
 */

package negocios;

import java.util.*;

/**
 *
 * @author  leme
 */
public class Consultorio {
       
    private ArrayList receitas,
                      despesas,
                      historicosCont,
                      pacientes,
                      consultas;
    private static Consultorio instance = null;

    /** Creates a new instance of Consultorio */
    protected Consultorio() {
        receitas = new ArrayList();
        despesas = new ArrayList();
        historicosCont = new ArrayList();
        pacientes = new ArrayList();
        consultas = new ArrayList();
    }
    
    public static Consultorio Instance()
    {
        if (instance == null)
            instance = new Consultorio();
        return instance;
    }

    public void adicionaDespesa(Despesa d)
    {
        despesas.add(d);
    }
    
    public void removeDespesa(Despesa d)
    {
        despesas.remove(d);
    }
    
    public ArrayList getPacientes()
    {
        return pacientes;
    }
    
    public void adicionaPaciente(Paciente p)
    {
        pacientes.add(p);
    }
    
    
    public void removePaciente(Paciente p)
    {
        pacientes.remove(p);
    }
    
    public void adicionaReceita(Receita r)
    {
        receitas.add(r);
    }
    
    public void removeReceita(Receita r)
    {
        receitas.remove(r);
    }
    
    public ArrayList getConsultas()
    {
        return consultas;
    }
    
    public void adicionaConsulta(Consulta c)
    {
        consultas.add(c);
    }
    
    public void removeConsulta(Consulta c)
    {
        consultas.remove(c);
    }

    public Quantity calculaPagamento(DateRange faixaData)
    {
        Quantity result = new Quantity(0,Unit.get(Unit.REAL));

        for (Iterator i = receitas.iterator(); i.hasNext(); )
        {
            Receita receita = (Receita) i.next();
            if (faixaData.incluiCompleto(receita.getData()))
                result = Quantity.soma(result,receita.getValor());
        }
        return result;
    }

    public HistoricoContabil buscaHistorico(int ano, String mes)
    {
        HistoricoContabil hc = null;
        
        for (Iterator i = historicosCont.iterator(); i.hasNext();)
        {
            hc = (HistoricoContabil) i.next();
            if ((ano == hc.getAno()) && mes.equals(hc.getMes()))
                return hc;
        }
        return null;
    }

    private String nomeMesAtual()
    {
        String mes = new String();
        
        switch (Calendar.getInstance().get(Calendar.MONTH))
        {
            case Calendar.JANUARY : mes = "Janeiro"; break;
            case Calendar.FEBRUARY : mes = "Fevereiro"; break;
            case Calendar.MARCH : mes = "Marco"; break;
            case Calendar.APRIL : mes = "Abril"; break;
            case Calendar.MAY : mes = "Maio"; break;
            case Calendar.JUNE : mes = "Junho"; break;
            case Calendar.JULY : mes = "Julho"; break;
            case Calendar.AUGUST : mes = "Agosto"; break;
            case Calendar.SEPTEMBER : mes = "Setembro"; break;
            case Calendar.OCTOBER : mes = "Outubro"; break;
            case Calendar.NOVEMBER : mes = "Novembro"; break;
            case Calendar.DECEMBER : mes = "Dezembro"; break;
        }
        
        return mes;
    }
    
    private boolean fimMesFiscal(boolean simulacao)
    {
        if (simulacao)
            return true;
        else
        {
            Calendar hoje = Calendar.getInstance();
            
            if ((hoje.get(Calendar.MONTH) != Calendar.FEBRUARY && hoje.get(Calendar.DATE) == 30) ||
                (hoje.get(Calendar.MONTH) == Calendar.FEBRUARY && hoje.get(Calendar.DATE) == 28))
                return true;
            else
                return false;
        }
    }
    
    public void efetuaBalanco(boolean simulacao)
    {
        ArrayList despesasExcluidas = new ArrayList(),
                  receitasExcluidas = new ArrayList();
        
        if (fimMesFiscal(simulacao))
        {
            Quantity receita = new Quantity(0,Unit.get(Unit.REAL)),
                     despesa = new Quantity(0,Unit.get(Unit.REAL));
            int ano = Calendar.getInstance().get(Calendar.YEAR);
            String mes = new String(nomeMesAtual());
            ArrayList lancamentos = new ArrayList();
            
            for (Iterator i = receitas.iterator(); i.hasNext();)
            {
                Receita rec = (Receita) i.next();
                lancamentos.add("R/" + rec.getDescricao());
                receita = Quantity.soma(receita,rec.efetuaReceita());
                if (!rec.haPrestacoes())
                    receitasExcluidas.add(rec);
            }
            
            for (Iterator i = despesas.iterator(); i.hasNext();)
            {
                Despesa desp = (Despesa) i.next();
                lancamentos.add("D/" + desp.getDescricao());
                despesa = Quantity.soma(despesa,desp.efetuaDespesa());
                if (!desp.haPrestacoes())
                    despesasExcluidas.add(desp);
             }
            
            for (Iterator i = receitasExcluidas.iterator(); i.hasNext();)
            {
                receitas.remove((Receita) i.next());
            }
                
            for (Iterator i = despesasExcluidas.iterator(); i.hasNext();)
            {
                despesas.remove((Despesa) i.next());
            }
            
            historicosCont.add(new HistoricoContabil(receita,despesa,ano,mes,lancamentos));
        }
    }
}
