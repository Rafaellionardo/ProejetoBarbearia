/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author renan
 */
public class ItemAgendamento {
    
    private Agendamento agendamento; 
    private Servico servico; 
    private Colaborador colaborador;
    private int quantidadeServico; 
    private Double valorServico; 
    private Double desconto; 
    private Time horaInicio; 
    private Time horaFim; 
    //private int comissaoColab; 
    
     public ItemAgendamento(Agendamento agendamento, Servico servico, Colaborador colaborador, int quantidadeServico, Double valorServico, double desconto, Time horaInicio, Time horaFim ){
        
        this.agendamento = agendamento;
        this.servico = servico; 
        this.colaborador = colaborador; 
        this.quantidadeServico = quantidadeServico; 
        this.valorServico = valorServico; 
        this.desconto = desconto; 
        this. horaInicio = horaInicio; 
        this.horaFim = horaFim;
        
    }
    
    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }
  
   
    
    public ItemAgendamento(){
        
    }
    
    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public int getQuantidadeServico() {
        return quantidadeServico;
    }

    public void setQuantidadeServico(int quantidadeServico) {
        this.quantidadeServico = quantidadeServico;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
 
}
