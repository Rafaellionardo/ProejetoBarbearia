/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author renan
 */
public class Agendamento {

    private int codigo;
    private Cliente cliente;
    private Colaborador colaborador;
    private String moticoCancelamento;
    private Double valorTotal;
    private Date dia;
    private String comentario;
    private char status;
    private Double descontoVenda;
    private String descricaoDesconto;
    private ArrayList<ItemAgendamento> itensAgendamento;
    private Boolean ativo;
    private Boolean pago;
    
    
    
    
    
    public ArrayList<ItemAgendamento> getItensAgendamento() {
        return itensAgendamento;
    }

    public Agendamento() {

        this.itensAgendamento = new ArrayList();
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public void setItensAgendamento(ArrayList<ItemAgendamento> itensAgendamento) {
        this.itensAgendamento = itensAgendamento;
    }

    public void setMoticoCancelamento(String moticoCancelamento) {
        this.moticoCancelamento = moticoCancelamento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void adicionarItem(ItemAgendamento item) {
        this.itensAgendamento.add(item);
    }

    public void removerItem(ItemAgendamento item) {
        this.itensAgendamento.remove(item);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getMotivoCancelamento() {
        return moticoCancelamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Double getDescontoVenda() {
        return descontoVenda;
    }

    public void setDescontoVenda(Double descontoVenda) {
        this.descontoVenda = descontoVenda;
    }

    public String getDescricaoDesconto() {
        return descricaoDesconto;
    }

    public void setDescricaoDesconto(String descricaoDesconto) {
        this.descricaoDesconto = descricaoDesconto;
    }

    public void aualizaValorTotal() {

        double valor = 0;

        for (int i = 0; i < this.itensAgendamento.size(); i++) {

            valor = valor + ((this.itensAgendamento.get(i).getValorServico() * this.itensAgendamento.get(i).getQuantidadeServico()) - this.itensAgendamento.get(i).getDesconto());

        }

        this.valorTotal = valor - this.descontoVenda;
    }

}
