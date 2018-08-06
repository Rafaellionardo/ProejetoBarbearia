/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Agendamento;
import entidade.Cliente;
import entidade.ContaReceber;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author renan
 */
public class ContaReceberDao implements Idao {
    
    public static void realizaParcelamento(int numeroParcelas, Agendamento a){
        
        
        
        
        
    }
    
    
    
    @Override
    public boolean salvar(Object o) {

        ContaReceber conta = (ContaReceber) o;

        boolean retorno = false;

        String sql = "INSERT INTO contasareceber "
                + "VALUES(DEFAULT,?,?,?,?,?,?,?)";

        Connection con = ConexaoBD.getInstance().getConnection();

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, conta.getAgendamento().getCodigo());
            st.setDate(2, conta.getVencimento());
            st.setDouble(3, conta.getValor());
            st.setDouble(4, conta.getValorPago());
            st.setDate(5, conta.getDataPagamento());
            st.setInt(6, conta.getParcela());
            st.setString(7, conta.getStatus() + "");

            System.out.println("Query SALVAR contaareceber: " + sql);
            int resultado = st.executeUpdate();

            if (resultado != 0) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("Erro inserir contaareceber  = " + e);
        }
        return retorno;

    }

    @Override
    public String atualizar(Object o) {

        String retorno = "";

        ContaReceber cont = (ContaReceber) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE contasareceber SET agendamento = ?, vencimento = ?, "
                + "valordevido = ?,"
                + "valorpago = ?,"
                + "datapagamento = ?,"
                + "parcela = ?,"
                + "status = ?  WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, cont.getAgendamento().getCodigo());
            st.setDate(2, cont.getVencimento());
            st.setDouble(3, cont.getValor());
            st.setDouble(4, cont.getValorPago());
            st.setDate(5, cont.getDataPagamento());
            st.setInt(6, cont.getParcela());
            st.setString(7, cont.getStatus() + "");
            st.setInt(8, cont.getCodigo());

            if (st.executeUpdate() == 1) {
                return null;
            }

            st.close();
            //con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar Conta a Receber: " + e);
            return e.toString();
        }

        return retorno;
    }

    @Override
    public Boolean excluir(int id) {
         Boolean retorno = false;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "delete from contasareceber WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);
            
     
         
            st.setInt(1, id);

            if (st.executeUpdate() == 1) {
                retorno = true;
            }

            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao excluir conta a receber: " + e);

        }

        return retorno;
    
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
        ContaReceber cont = new ContaReceber();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM contasareceber WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet resultado = st.executeQuery();

            AgendamentoDao dao = new AgendamentoDao();

            if (resultado.next()) {

                cont.setCodigo(id);
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                return cont;
            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar conta a receber: " + e);
            return null;
        }

        return null;

    }

    public ArrayList<ContaReceber> consultarTodas() {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM contasAreceber");

            if (resultado.isBeforeFirst()) {

              
                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();
                AgendamentoDao dao =  new AgendamentoDao(); 
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                   
                   

                   contas.add(cont);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;
 
        
    }
    
      public ArrayList<ContaReceber> consultarPorStatus(char status) {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();
        
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM contasareceber WHERE status = ?";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,status+"");

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

              
               
                AgendamentoDao dao = new AgendamentoDao();
                
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                contas.add(cont);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;     

    }
      
      
      
       public ArrayList<ContaReceber> consultarPorPeriodoVencimento(Date inicio, Date fim) {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();
        
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM contasareceber WHERE vencimento BETWEEN ? AND ? AND status = 'a' order by vencimento";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setDate(1,inicio);
            st.setDate(2, fim);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

              
               
                AgendamentoDao dao = new AgendamentoDao();
                
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                contas.add(cont);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;     

    }
    
        public ArrayList<ContaReceber> consultarPorPeriodoPagamento(Date inicio, Date fim) {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();
        
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM contasareceber WHERE datapagamento BETWEEN ? AND ?";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setDate(1,inicio);
            st.setDate(2, fim);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

              
               
                AgendamentoDao dao = new AgendamentoDao();
                
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                contas.add(cont);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;     

    }
        public ArrayList<ContaReceber> consultarPorPeriodoPagamentoPagos(Date inicio, Date fim) {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();
        
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM contasareceber WHERE datapagamento BETWEEN ? AND ? and status = 'p'";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setDate(1,inicio);
            st.setDate(2, fim);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

              
               
                AgendamentoDao dao = new AgendamentoDao();
                
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                contas.add(cont);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;     

    }
        
         public ArrayList<ContaReceber> consultarPorAgendamento(int a) {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();
        
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM contasareceber WHERE agendamento =  ?";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,a);
            

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

              
               
                AgendamentoDao dao = new AgendamentoDao();
                
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                contas.add(cont);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;     

    }
         
    public Object consultarPorAgendamentoEParcela(int parcela, Agendamento a) {
        ContaReceber cont = new ContaReceber();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM contasareceber WHERE parcela ilike ? and agendamento = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, parcela+"");
            st.setInt(2, a.getCodigo());
            
            ResultSet resultado = st.executeQuery();

            AgendamentoDao dao = new AgendamentoDao();

            if (resultado.next()) {

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                return cont;
            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar conta a receber: " + e);
            return null;
        }

        return null;

    }    
    
    public ArrayList<ContaReceber> consultarPorNomeClienteEAbertaEperiodo(String nome, Date ini, Date fim) {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();
        
        Connection con = ConexaoBD.getInstance().getConnection();

      
        
        String sql =  "select * from contasareceber ca INNER JOIN agendamento a on ca.agendamento=a.codigo INNER JOIN cliente c  ON a.cliente=c.codigo where c.nomecompleto ilike ? and ca.status = 'a'  and ca.vencimento BETWEEN ? AND ? Order by ca.vencimento";
        
      
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,'%' + nome + '%');
            st.setDate(2,ini);
            st.setDate(3, fim);
            
            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

              
               
                AgendamentoDao dao = new AgendamentoDao();
                
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                contas.add(cont);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;     

    }
    
      public ArrayList<ContaReceber> consultarPorNomeClienteEpagaEperiodo(String nome, Date ini, Date fim) {
        //Cria um ArrayList de Objetos
        ArrayList<ContaReceber> contas = new ArrayList<>();
        
        Connection con = ConexaoBD.getInstance().getConnection();

      
        
        String sql =  "select * from contasareceber ca INNER JOIN agendamento a on ca.agendamento=a.codigo INNER JOIN cliente c  ON a.cliente=c.codigo where c.nomecompleto ilike ? and ca.status = 'p'  and ca.vencimento BETWEEN ? AND ? Order by ca.vencimento";
        
      
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,'%' + nome + '%');
            st.setDate(2,ini);
            st.setDate(3, fim);
            
            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

              
               
                AgendamentoDao dao = new AgendamentoDao();
                
                
                while (resultado.next()) {
                    // captura dados do ResultSet
                ContaReceber cont = new ContaReceber();

                cont.setCodigo(resultado.getInt("codigo"));
                cont.setAgendamento((Agendamento) dao.consultarId(resultado.getInt("agendamento")));
                cont.setDataPagamento(resultado.getDate("datapagamento"));
                cont.setValor(resultado.getDouble("valordevido"));
                cont.setValorPago(resultado.getDouble("valorpago"));
                cont.setParcela(resultado.getInt("parcela"));
                cont.setVencimento(resultado.getDate("vencimento"));
                String tmp = resultado.getString("status"); 
                cont.setStatus(tmp.charAt(0));
                contas.add(cont);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return contas;     

    }   
         
}
