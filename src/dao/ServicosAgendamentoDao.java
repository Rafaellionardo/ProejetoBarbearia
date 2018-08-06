/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Agendamento;
import entidade.Colaborador;
import entidade.ItemAgendamento;
import entidade.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author renan
 */
public class ServicosAgendamentoDao implements Idao {

    @Override
    public boolean salvar(Object o) {
        
       ItemAgendamento item = (ItemAgendamento) o;

        boolean retorno = false;

        String sql = "INSERT INTO servicos_agendamento "
                + "VALUES(?,?,?,?,?,?,?,?)";

        Connection con = ConexaoBD.getInstance().getConnection();

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, item.getAgendamento().getCodigo());
            st.setInt(2, item.getServico().getCodigo());
            st.setInt(3, item.getColaborador().getCodigo());
            st.setInt(4, item.getQuantidadeServico()); 
            st.setDouble(5, item.getValorServico()); 
            st.setDouble(6, item.getDesconto());
            st.setTime(7, item.getHoraInicio());
            st.setTime(8, item.getHoraFim());
            
            System.out.println("Query SALVAR item agendamento: " + sql);
            int resultado = st.executeUpdate();

            if (resultado != 0) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("Erro inserir itemagendamento = " + e);
        }
        return retorno;
        
    }

    @Override
    public String atualizar(Object o) {
        
     String retorno = "";

        ItemAgendamento item = (ItemAgendamento) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE servicos_agendamento "
                + "SET agendamento = ?,"
                + "servico = ?, "
                + "colaborador = ?,"
                + "quantidadeservico = ?,"
                + "valorservico = ?,"
                + "desconto = ? "
                + " WHERE agendamento = ? and servico = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, item.getAgendamento().getCodigo());
            st.setInt(2, item.getServico().getCodigo());
            st.setDouble(3, item.getColaborador().getCodigo());
            st.setInt(4, item.getQuantidadeServico());
            st.setDouble(5, item.getValorServico());
            st.setDouble(6, item.getDesconto());
            st.setDouble(7, item.getAgendamento().getCodigo());
            st.setInt(8, item.getServico().getCodigo());
         

            if (st.executeUpdate() == 1) {
                return null;
            }

            st.close();
            //con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar agendamento: " + e);
            return e.toString();
        }

        return retorno;
    
    }

    @Override
    public Boolean excluir(int id) {
        
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools |
        
        
        
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public ArrayList<ItemAgendamento> consultarPorAgendamento(int codigoAgendamento) {
        
        

        ArrayList<ItemAgendamento> itens = new ArrayList();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM servicos_agendamento WHERE agendamento = ? ";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,codigoAgendamento);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {
                AgendamentoDao daoA = new AgendamentoDao();
                ColaboradorDao daoC = new ColaboradorDao(); 
                ServicoDao daoS = new ServicoDao();
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                    ItemAgendamento item = new ItemAgendamento();

                    item.setAgendamento((Agendamento) daoA.consultarId(resultado.getInt("agendamento")));
                    item.setColaborador((Colaborador) daoC.consultarId(resultado.getInt("colaborador")));
                    item.setDesconto(resultado.getDouble("desconto"));
                    item.setValorServico(resultado.getDouble("valorservico"));
                    item.setServico((Servico) daoS.consultarId(resultado.getInt("servico")));
                    item.setQuantidadeServico(resultado.getInt("quantidadeservico"));
                    item.setHoraInicio(resultado.getTime("horaInicio"));
                    item.setHoraFim(resultado.getTime("horaFim"));
                    itens.add(item);
                }
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar itens para este agendamento: " + e);
            // return null;
        }

        return itens;
         
         
    }
    
    public boolean excluirItem(ItemAgendamento item){
         

        boolean retorno = false;

        String sql = "DELETE FROM servicos_agendamento "
                + "WHERE agendamento = ? and servico = ? ";

        Connection con = ConexaoBD.getInstance().getConnection();

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, item.getAgendamento().getCodigo());
            st.setInt(2, item.getServico().getCodigo());
           
            
            System.out.println("Query excluir item agendamento: " + sql);
            int resultado = st.executeUpdate();

            if (resultado != 0) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("Erro excluir itemagendamento: " + e);
        }
        return retorno;
        
    }
}
