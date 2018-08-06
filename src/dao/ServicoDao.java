/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Servico;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author renan
 */
public class ServicoDao implements Idao {
    
    @Override
    public boolean salvar(Object o) {

        Servico serv = (Servico) o;
        
        boolean retorno = false;
        
        String sql ="INSERT INTO servico VALUES(DEFAULT,?,?,?)";  
       
        Connection con =  ConexaoBD.getInstance().getConnection();
        
        
        try {
           
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, serv.getDescricao());
            st.setDouble(2, serv.getValor());
            st.setBoolean(3, true);

            System.out.println("Query SALVAR servico: " + sql);

            int resultado = st.executeUpdate();

            if (resultado != 0) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("Erro salvar servico  = " + e);
        }
        return retorno;
    }

    @Override
    public String atualizar(Object o) {
        
        String retorno  = ""; 
        
        Servico serv = (Servico) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE servico SET descricao = ?, valor = ? WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, serv.getDescricao());
            st.setDouble(2, serv.getValor());
            st.setInt(3, serv.getCodigo());
            
            
            if (st.executeUpdate() == 1) {
                return null;
            }
            
            st.close();
            //con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar Servico: " + e);
            return e.toString();
        }
        
        return retorno;  
    }
    @Override
    public Boolean excluir(int id) {
       
        Boolean retorno =  false; 
        
        Servico serv =  new Servico(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE servico SET ativo = ? WHERE codigo = ?";
        
        
         try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setBoolean(1, false);
            st.setInt(2, id);
      

            if (st.executeUpdate() == 1) {
                retorno = true; 
            }
            
            st.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao excluír servico: " + e);
           
        }
       
        return retorno; 
    }
    
    
    public Boolean reativar(int id) {
       
        Boolean retorno =  false; 
        
        Servico serv =  new Servico(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE servico SET ativo = ? WHERE codigo = ?";
        
        
         try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setBoolean(1, true);
            st.setInt(2, id);
      

            if (st.executeUpdate() == 1) {
                retorno = true; 
            }
            
            st.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao excluír servico: " + e);
           
        }
       
        return retorno; 
    }

    
    
    @Override
    public ArrayList<Object> consultarTodos() {
        //Cria um ArrayList de Objetos
        ArrayList<Object> servicos = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM servico");

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Servico serv = new Servico();

                    serv.setDescricao(resultado.getString("descricao"));
                    serv.setValor(resultado.getDouble("valor"));
                    serv.setAtivo(resultado.getBoolean("ativo"));

                    serv.setCodigo(resultado.getInt("codigo"));

                    servicos.add(serv);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return servicos;

    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public ArrayList<Servico> consultarPorDescricao(String criterio) {
         //Cria um ArrayList de Objetos
        ArrayList<Servico> servicos = new ArrayList<>();
        
        try {
            Connection con = ConexaoBD.getInstance().getConnection();
           
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            String sql = "SELECT * FROM servico where descricao ilike ? order by descricao";
            
            PreparedStatement st = con.prepareStatement(sql); 
            
            st.setString(1, "%" + criterio + "%"); 
            
            ResultSet resultado = st.executeQuery();
            
            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Servico serv = new Servico();

                    serv.setDescricao(resultado.getString("descricao"));
                    serv.setValor(resultado.getDouble("valor"));
                    serv.setAtivo(resultado.getBoolean("ativo"));

                    serv.setCodigo(resultado.getInt("codigo"));

                    servicos.add(serv);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        
        return servicos;

    }
      public ArrayList<Servico> consultarPorDescricaoAtivos(String criterio) {
         //Cria um ArrayList de Objetos
        ArrayList<Servico> servicos = new ArrayList<>();
        
        try {
            Connection con = ConexaoBD.getInstance().getConnection();
           
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            String sql = "SELECT * FROM servico where descricao ilike ? and ativo = true order by descricao";
            
            PreparedStatement st = con.prepareStatement(sql); 
            
            st.setString(1, "%" + criterio + "%"); 
            
            ResultSet resultado = st.executeQuery();
            
            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Servico serv = new Servico();

                    serv.setDescricao(resultado.getString("descricao"));
                    serv.setValor(resultado.getDouble("valor"));
                    serv.setAtivo(resultado.getBoolean("ativo"));

                    serv.setCodigo(resultado.getInt("codigo"));

                    servicos.add(serv);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        
        return servicos;

    }
    /**
     * Valida no banco de dados se o valor que está no campo descrição do serviço é único
     * @param descricao
     * @return Caso for único, retorna verdadeiro, do contrário falso 
     */
   
   public boolean validaDescricaoServico(String descricao){
       
       boolean retorno = false; 
       
       Servico serv =  new Servico(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT  FROM servico WHERE descricao ilike ?";
        
        
         try {

            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setString(1, descricao); 

            ResultSet resultado = st.executeQuery();

            if (resultado.next()){ 
                retorno  = true; 
            }   
            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar servico: " + e);
           // return null;
        }
       
        return retorno;  
    
    }

    
    
    @Override
    public Object consultarId(int id) {
        
        Servico serv =  new Servico(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM servico WHERE codigo = ?";
        
        
         try {

            
            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setInt(1, id); 

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                
                serv.setCodigo(id);
                serv.setDescricao(resultado.getString("descricao"));
                serv.setValor(resultado.getDouble("valor"));
                serv.setAtivo(resultado.getBoolean("ativo"));
                return serv;
            } 

            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            return null;
        }
       
        return serv; 
    }

    public void popularTabela(JTable tabela, String criterio) {
        ResultSet resultadoQ;
        
        
         Connection con = ConexaoBD.getInstance().getConnection();
         
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Valor (R$)";
        //cabecalho[3] = "Ativo";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = con.createStatement().executeQuery(""
                    + "SELECT count(*) FROM servico "
                    + "WHERE descricao ILIKE '%" + criterio + "%' AND ativo = true ");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = con.createStatement().executeQuery(""
                    + "SELECT * FROM servico "
                    + "WHERE DESCRICAO ILIKE '%" + criterio + "%' AND ativo = true order by descricao");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("codigo");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");
                dadosTabela[lin][2] = resultadoQ.getDouble("valor");
               // dadosTabela[lin][3] = resultadoQ.getBoolean("ativo");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
                
                //con.close();
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }
        
        
        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
       

        
        // renderizacao das linhas da tabela = mudar a cor
       /* tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(Color.WHITE);
                } else {
                    setBackground(Color.LIGHT_GRAY);
                }
                return this;
            }
        });*/
       
        
    }
    
}
