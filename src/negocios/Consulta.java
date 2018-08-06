package negocios;
import java.lang.*;
import java.util.*;

public class Consulta {
    DateRange data;
    String descricao = null;
    ArrayList servicos;
    ArrayList consultas;
    ArrayList receitas;
    

    public Consulta (String descricaoConsulta) {
        descricao = descricaoConsulta;
        servicos = new ArrayList ();
        receitas = new ArrayList ();
        consultas = new ArrayList ();
        Consultorio.Instance().adicionaConsulta(this);
    }
    
    public Consulta (DateRange dataConsulta, String descricaoConsulta) {
        data = dataConsulta;
        descricao = descricaoConsulta;
        servicos = new ArrayList ();
        receitas = new ArrayList ();
        consultas = new ArrayList ();
        Consultorio.Instance().adicionaConsulta(this);
    }
    
    public Consulta (DateRange dataConsulta, String descricaoConsulta, ArrayList servicosConsulta) {
        data = dataConsulta;
        descricao = descricaoConsulta;
        servicos = servicosConsulta;
        receitas = new ArrayList ();
        consultas = new ArrayList ();
        Consultorio.Instance().adicionaConsulta(this);
    }
    
    public Consulta (Date dataInicioConsulta, Date dataFimConsulta, String descricaoConsulta) {
	data = new DateRange (dataInicioConsulta, dataFimConsulta);
        descricao = descricaoConsulta;
        servicos = new ArrayList ();
        receitas = new ArrayList ();
        consultas = new ArrayList ();
        Consultorio.Instance().adicionaConsulta(this);
    }

    public Consulta (Date dataInicioConsulta, Date dataFimConsulta, String descricaoConsulta, ArrayList servicosConsutla) {
	data = new DateRange (dataInicioConsulta, dataFimConsulta);
        descricao = descricaoConsulta;
        servicos = servicosConsutla;
        receitas = new ArrayList ();
        consultas = new ArrayList ();
        Consultorio.Instance().adicionaConsulta(this);
    }
    
    public ArrayList getConsultas () {
        return consultas;
    }
    
    public String getDescricao () {
        return descricao;
    }
    
    public DateRange getData () {
        return data;
    }
    
    public String toString () {
        return descricao;
    }
    
    public void adicionaConsulta (Consulta novaConsulta) {
        consultas.add (novaConsulta);        
    }
    
    public void adicionaReceita (Receita r) {
        receitas.add (r);        
    }
    
    public void adicionaServico (String descricaoServico, Quantity preco) {
	Servico novoServico = new Servico (descricaoServico, preco);
	servicos.add(novoServico);
    }

    public void efetuaConsulta () {
        
    }
    
    public Quantity calculaCusto () {
        Quantity q = new Quantity (0, Unit.get(Unit.REAL));
        for (int i = 0; i < servicos.size(); i++) {
            Servico s = (Servico) servicos.get(i);
            q = Quantity.soma(q, s.getPreco());
        }
        return q;
    }

}
