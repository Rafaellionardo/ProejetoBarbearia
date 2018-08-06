/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Cliente;
import entidade.Colaborador;
import entidade.Endereco;
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
public class ClienteDao implements Idao {

    @Override
    public boolean salvar(Object o) {

        Cliente clie = (Cliente) o;

        boolean retorno = false;

        String sql = "INSERT INTO cliente VALUES(DEFAULT,?,?,?,?,?,?,?,?,?)";

        Connection con = ConexaoBD.getInstance().getConnection();

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, clie.getEndereco().getCodigo());
            st.setString(2, clie.getNomeCompleto());
            st.setString(3, clie.getEmail());
            st.setString(4, clie.getCelular());
            st.setDate(5, clie.getDataNascimento());
            st.setString(6, clie.getSexo());
            st.setDate(7, clie.getDataPrimeiroServ());
            st.setDate(8, clie.getDataUltimoServ());
            st.setBoolean(9, true);

            System.out.println("Query SALVAR cliente: " + sql);

            int resultado = st.executeUpdate();

            if (resultado != 0) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("Erro salvar cliente  = " + e);
        }
        return retorno;

    }

    @Override
    public String atualizar(Object o) {

        String retorno = "";

        Cliente clie = (Cliente) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE cliente SET endereco = ?, nomecompleto = ?, email = ?, celular = ?,datanascimento = ?,sexo = ?, dataprimeiroserv = ?, dataultimoserv = ?  WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, clie.getEndereco().getCodigo());
            st.setString(2, clie.getNomeCompleto());
            st.setString(3, clie.getEmail());
            st.setString(4, clie.getCelular());
            st.setDate(5, clie.getDataNascimento());
            st.setString(6, clie.getSexo());
            st.setDate(7, clie.getDataPrimeiroServ());
            st.setDate(8, clie.getDataUltimoServ());
            //st.setBoolean(9, clie.isAtivo()); 
            st.setInt(9, clie.getCodigo());

            if (st.executeUpdate() == 1) {
                return null;
            }

            st.close();
            //con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e);
            return e.toString();
        }

        return retorno;

    }

    @Override
    public Boolean excluir(int id) {
        Boolean retorno = false;

        Cliente clie = new Cliente();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE cliente SET ativo = ? WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setBoolean(1, false);
            st.setInt(2, id);

            if (st.executeUpdate() == 1) {
                retorno = true;
            }

            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao excluír cliente: " + e);

        }

        return retorno;

    }
 
    public Boolean reativar(int id) {
        Boolean retorno = false;

        Cliente clie = new Cliente();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE cliente SET ativo = ? WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setBoolean(1, true);
            st.setInt(2, id);

            if (st.executeUpdate() == 1) {
                retorno = true;
            }

            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao excluír cliente: " + e);

        }

        return retorno;

    }
    @Override
    public ArrayList<Object> consultarTodos() {

        //Cria um ArrayList de Objetos
        ArrayList<Object> clientes = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM cliente");

            if (resultado.isBeforeFirst()) {

                EnderecoDao daoe = new EnderecoDao();
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Cliente clie = new Cliente();

                    clie.setCodigo(resultado.getInt("codigo"));
                    clie.setEndereco((Endereco) daoe.consultarId(resultado.getInt("endereco")));
                    clie.setNomeCompleto(resultado.getString("nomecompleto"));
                    clie.setEmail(resultado.getString("email"));
                    clie.setCelular(resultado.getString("celular"));
                    clie.setDataNascimento(resultado.getDate("datanascimento"));
                    clie.setSexo(resultado.getString("sexo"));
                    clie.setDataPrimeiroServ(resultado.getDate("dataprimeiroserv"));
                    clie.setDataUltimoServ(resultado.getDate("dataultimoserv"));
                    clie.setAtivo(resultado.getBoolean("ativo"));

                    clientes.add(clie);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return clientes;

    }

    public ArrayList<Cliente> consultarTodosClientes() {

        //Cria um ArrayList de Objetos
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM cliente");

            if (resultado.isBeforeFirst()) {

                EnderecoDao daoe = new EnderecoDao();
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Cliente clie = new Cliente();

                    clie.setCodigo(resultado.getInt("codigo"));
                    clie.setEndereco((Endereco) daoe.consultarId(resultado.getInt("endereco")));
                    clie.setNomeCompleto(resultado.getString("nomecompleto"));
                    clie.setEmail(resultado.getString("email"));
                    clie.setCelular(resultado.getString("celular"));
                    clie.setDataNascimento(resultado.getDate("datanascimento"));
                    clie.setSexo(resultado.getString("sexo"));
                    clie.setDataPrimeiroServ(resultado.getDate("dataprimeiroserv"));
                    clie.setDataUltimoServ(resultado.getDate("dataultimoserv"));
                    clie.setAtivo(resultado.getBoolean("ativo"));

                    clientes.add(clie);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return clientes;

    }
    @Override
    public ArrayList<Object> consultar(String nome) {

        boolean retorno = false;

        ArrayList<Object> clientes = new ArrayList();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cliente WHERE nomecompleto ilike ? ";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, '%' + nome + '%');

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {
                EnderecoDao daoe = new EnderecoDao();
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Cliente clie = new Cliente();

                    clie.setCodigo(resultado.getInt("codigo"));
                    clie.setEndereco((Endereco) daoe.consultarId(resultado.getInt("endereco")));
                    clie.setNomeCompleto(resultado.getString("nomecompleto"));
                    clie.setEmail(resultado.getString("email"));
                    clie.setCelular(resultado.getString("celular"));
                    clie.setDataNascimento(resultado.getDate("datanascimento"));
                    clie.setSexo(resultado.getString("sexo"));
                    clie.setDataPrimeiroServ(resultado.getDate("dataprimeiroserv"));
                    clie.setDataUltimoServ(resultado.getDate("dataultimoserv"));
                    clie.setAtivo(resultado.getBoolean("ativo"));

                    clientes.add(clie);
                }
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
            // return null;
        }

        return clientes;

    }

    @Override
    public Object consultarId(int id) {

        Cliente clie = new Cliente();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cliente WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet resultado = st.executeQuery();

            EnderecoDao daoe = new EnderecoDao();

            if (resultado.next()) {

                clie.setCodigo(resultado.getInt("codigo"));
                clie.setEndereco((Endereco) daoe.consultarId(resultado.getInt("endereco")));
                clie.setNomeCompleto(resultado.getString("nomecompleto"));
                clie.setEmail(resultado.getString("email"));
                clie.setCelular(resultado.getString("celular"));
                clie.setDataNascimento(resultado.getDate("datanascimento"));
                clie.setSexo(resultado.getString("sexo"));
                clie.setDataPrimeiroServ(resultado.getDate("dataprimeiroserv"));
                clie.setDataUltimoServ(resultado.getDate("dataultimoserv"));
                clie.setAtivo(resultado.getBoolean("ativo"));

            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
            return null;
        }

        return clie;

    }

    public void popularTabela(JTable tabela, String criterio) {
        ResultSet resultadoQ;

        Connection con = ConexaoBD.getInstance().getConnection();

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[8];

        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Email";
        cabecalho[3] = "Celular";
        cabecalho[4] = "Nascimento";
        cabecalho[5] = "Sexo";
        cabecalho[6] = "Último Serviço";
        cabecalho[7] = "Primeiro Serviço";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = con.createStatement().executeQuery(""
                    + "SELECT count(*) FROM cliente "
                    + "WHERE nomecompleto ILIKE '%" + criterio + "%' AND ativo = true ");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][6];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = con.createStatement().executeQuery(""
                    + "SELECT * FROM cliente "
                    + "WHERE nomecompleto ILIKE '%" + criterio + "%' AND ativo = true order by nomecompleto");

            char tipo;

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("codigo");
                dadosTabela[lin][1] = resultadoQ.getString("nomecompleto");
                dadosTabela[lin][2] = resultadoQ.getString("email");
                dadosTabela[lin][3] = resultadoQ.getString("celular");
                dadosTabela[lin][4] = resultadoQ.getDate("datanascimento");
                dadosTabela[lin][5] = resultadoQ.getString("sexo");
                dadosTabela[lin][6] = resultadoQ.getDate("dataultimoserv");
                dadosTabela[lin][7] = resultadoQ.getDate("dataprimeiroserv");

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
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }

        //Centralizando as linhas da tabela. 
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();

        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tabela.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        tabela.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        tabela.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        tabela.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        tabela.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        tabela.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        tabela.getColumnModel().getColumn(7).setCellRenderer(centralizado);

    }

    public ArrayList<Cliente> consultarPorNome(String nome) {

        ArrayList<Cliente> clientes = new ArrayList();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cliente WHERE nomecompleto ilike ?  order by nomecompleto";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, '%' + nome + '%');
            //st.setBoolean(2, true);
         
            
            ResultSet resultado = st.executeQuery();

            EnderecoDao daoe = new EnderecoDao();
            if (resultado.isBeforeFirst()) {

                while (resultado.next()) {

                    // captura dados do ResultSet
                    Cliente clie = new Cliente();
                    clie.setCodigo(resultado.getInt("codigo"));
                    clie.setNomeCompleto(resultado.getString("nomecompleto"));
                    clie.setEmail(resultado.getString("email"));
                    clie.setCelular(resultado.getString("celular"));
                    clie.setDataNascimento(resultado.getDate("datanascimento"));
                    clie.setSexo(resultado.getString("sexo"));
                    clie.setDataPrimeiroServ(resultado.getDate("dataprimeiroserv"));
                    clie.setDataUltimoServ(resultado.getDate("dataultimoserv"));
                    clie.setAtivo(resultado.getBoolean("ativo"));
                    clie.setEndereco(daoe.consultarPorCodigo(resultado.getInt("endereco")));
                    
                    clientes.add(clie);
                }
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
            // return null;
        }

        return clientes;

    }
    
    public ArrayList<Cliente> consultarPorNomeAtivo(String nome) {

        ArrayList<Cliente> clientes = new ArrayList();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cliente WHERE nomecompleto ilike ?  and ativo = ? order by nomecompleto";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, '%' + nome + '%');
            st.setBoolean(2, true);
         
            
            ResultSet resultado = st.executeQuery();

            EnderecoDao daoe = new EnderecoDao();
            if (resultado.isBeforeFirst()) {

                while (resultado.next()) {

                    // captura dados do ResultSet
                    Cliente clie = new Cliente();
                    clie.setCodigo(resultado.getInt("codigo"));
                    clie.setNomeCompleto(resultado.getString("nomecompleto"));
                    clie.setEmail(resultado.getString("email"));
                    clie.setCelular(resultado.getString("celular"));
                    clie.setDataNascimento(resultado.getDate("datanascimento"));
                    clie.setSexo(resultado.getString("sexo"));
                    clie.setDataPrimeiroServ(resultado.getDate("dataprimeiroserv"));
                    clie.setDataUltimoServ(resultado.getDate("dataultimoserv"));
                    clie.setAtivo(resultado.getBoolean("ativo"));
                    clie.setEndereco(daoe.consultarPorCodigo(resultado.getInt("endereco")));
                    
                    clientes.add(clie);
                }
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
            // return null;
        }

        return clientes;

    }
    
    public boolean validaNomeCliente(String nome){
       
       boolean retorno = false; 
       
       Cliente clie =  new Cliente(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT  FROM cliente WHERE nomecompleto ilike ?";
        
        
         try {

            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setString(1, nome); 

            ResultSet resultado = st.executeQuery();

            if (resultado.next()){ 
                retorno  = true; 
            }   
            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
           // return null;
        }
       
        return retorno;  
    
    }
    
     public boolean validaEmailCliente(String email){
       
       boolean retorno = false; 
       
       Cliente clie =  new Cliente(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT  FROM cliente WHERE email ilike ?";
        
        
         try {

            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setString(1, email); 

            ResultSet resultado = st.executeQuery();

            if (resultado.next()){ 
                retorno  = true; 
            }   
            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
           // return null;
        }
       
        return retorno;  
    
    }
     
     public boolean validaCelularCliente(String celular){
       
       boolean retorno = false; 
       
       Cliente clie =  new Cliente(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT  FROM cliente WHERE celular ilike ?";
        
        
         try {

            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setString(1, celular); 

            ResultSet resultado = st.executeQuery();

            if (resultado.next()){ 
                retorno  = true; 
            }   
            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
           // return null;
        }
       
        return retorno;  
    
    }


    
}
