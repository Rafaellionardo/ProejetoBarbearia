/*
 * PacienteEmergencial.java
 *
 * Created on 4 de Dezembro de 2002, 18:55
 */

package negocios;

import java.util.*;
import java.lang.*;
import dialogos.*;

/**
 *
 * @author  regisb
 */
public class PacienteEmergencial extends Paciente {
    
    private String identificacao;    
    
    /** Creates a new instance of PacienteEmergencial */
    public PacienteEmergencial(String id) {
        super ();        
        identificacao = id;        
        consultas = new ArrayList ();        
    }
    
    public String getNome () {
        return identificacao;
    }
    
    public String toString () {
        return identificacao;
    }
    
    private void testaConsultas (Paciente pac, DateRange data, ArrayList listacon) {
        Consulta tempcon;
        for (int i = 0; i < listacon.size(); i++) {
            tempcon = (Consulta) listacon.get(i);                
            if (data.sobrepoe(tempcon.getData())) {
                // Diálogo avisando da sobreposição
                String s = "Desmarcar consulta de " + pac.getNome() 
                        + "\nHorário Inicial:  " + data.getInicio().toString()
                        + "\nHorário Final:  " + data.getFim().toString();
                new DialogoConfirmacao(new javax.swing.JFrame(), true, "", s).show();
            }
            listacon = tempcon.getConsultas();
            if (listacon != null) {
                testaConsultas (pac, data, listacon);
            }
        }                   
    }
    
    
    private boolean testaDataConsulta (DateRange data) {
        Consulta tempcon;
        
        Consultorio cons = Consultorio.Instance();        
        ArrayList listapac = cons.getPacientes();
        for (int k = 0; k < listapac.size(); k++) {
            Paciente pac = (Paciente) listapac.get(k);
            ArrayList listacon = pac.getConsultas();
            testaConsultas (pac, data, listacon);            
        }
        return true;   
    }
    
    public Consulta marcaConsulta(DateRange data, String descricao) {
        testaDataConsulta (data);
        Consulta nova = new Consulta (data, descricao);
        consultas.add (nova);
        Consultorio.Instance().adicionaConsulta(nova);
        return nova;
    }
    
    public Consulta marcaConsulta(DateRange data, String descricao, ArrayList servicos) {
        testaDataConsulta (data);
        Consulta nova = new Consulta (data, descricao, servicos); 
        consultas.add (nova);
        Consultorio.Instance().adicionaConsulta(nova);
        return nova;
    }
}
