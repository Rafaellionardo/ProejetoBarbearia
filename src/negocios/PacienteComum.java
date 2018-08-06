/*
 * PacienteComum.java
 *
 * Created on 4 de Dezembro de 2002, 19:16
 */

package negocios;

import java.util.*;
import java.lang.*;

/**
 *
 * @author  regisb
 */
public class PacienteComum extends Paciente {    
    
    /** Creates a new instance of PacienteComum */
    public PacienteComum() {        
        super ();
        consultas = new ArrayList ();
    }
    
    private boolean testaDataConsulta (DateRange data) {
        Consultorio cons = Consultorio.Instance();        
        ArrayList listacon = cons.getConsultas();
        Consulta tempcon;
        for (int i = 0; i < listacon.size(); i++) {
            tempcon = (Consulta) listacon.get(i);
            if (data.sobrepoe(tempcon.getData())) {
                return false;
            }
        }
        return true;   
    }
    
    public Consulta marcaConsulta(Consulta consulta, DateRange data, String descricao) {
        if (testaDataConsulta (data)) {
            Consulta nova = new Consulta (data, descricao);
            consulta.adicionaConsulta(nova);
            Consultorio.Instance().adicionaConsulta(nova);
            return nova;
        } else {
            return null;
        }
    }

    public Consulta marcaConsulta(Consulta consulta, DateRange data, String descricao, ArrayList servicos) {
        if (testaDataConsulta (data)) {
            Consulta nova = new Consulta (data, descricao, servicos);
            consulta.adicionaConsulta(nova);
            Consultorio.Instance().adicionaConsulta(nova);
            return nova;
        } else {
            return null;
        }
    }

    
    public Consulta marcaConsulta(DateRange data, String descricao) {
        if (testaDataConsulta (data)) {
            Consulta nova = new Consulta (data, descricao);
            consultas.add (nova);
            Consultorio.Instance().adicionaConsulta(nova);
            return nova;
        } else {
            return null;
        }
    }
    
    public Consulta marcaConsulta(DateRange data, String descricao, ArrayList servicos) {
        if (testaDataConsulta (data)) {
            Consulta nova = new Consulta (data, descricao, servicos);
            consultas.add (nova);
            Consultorio.Instance().adicionaConsulta(nova);
            return nova;
        } else {
            return null;
        }
    }
    
}
