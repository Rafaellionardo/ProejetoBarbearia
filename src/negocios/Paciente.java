package negocios;

import java.lang.*;
import java.util.*;

public class Paciente {
    static int n = 0;
    int idPaciente;
    ArrayList consultas;
    Date dataNasc;
    String nome,        
	endRua, 
	endBairro, 
	endCidade, 
	endEstado,
	endCEP,
	endFone,
	endMail;
    
    
    public Paciente () {
	n++;
	idPaciente = n;
        consultas = new ArrayList();
        Consultorio.Instance().adicionaPaciente(this);
    }

    public String toString () {
        return nome;
    }
    
    public void setNome (String nomePac) {
	nome = nomePac;	
    }

    public void setEndereco (String rua, String bairro, String cidade, String estado,
		      String cep, String fone, String email) {
	endRua = rua;
	endBairro = bairro;
	endCidade = cidade;
	endEstado = estado;
	endCEP = cep;
	endFone = fone;
	endMail = email;
    }

    public void setDataNasc (Date data) {
        dataNasc = data;
    }
    
    public Consulta marcaConsulta(Consulta consulta, DateRange data, String descricao) {
        return null;
    }

    public Consulta marcaConsulta(Consulta consulta, DateRange data, String descricao, ArrayList servicos) {
        return null;
    }
    
    public Consulta marcaConsulta(DateRange data, String descricao) {        
        return null;
    }
    public Consulta marcaConsulta(DateRange data, String descricao, ArrayList servicos) {        
        return null;
    }
    
    public ArrayList getConsultas () {
        return consultas;
    }
    
    public String getNome () {
        return nome;
    }
}
