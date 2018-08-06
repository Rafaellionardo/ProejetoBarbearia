/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Colaborador;
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
public class ColaboradorDao implements Idao {

    @Override
    public boolean salvar(Object o) {

        Colaborador colab = (Colaborador) o;

        boolean retorno = false;

        String sql = "INSERT INTO colaborador VALUES(DEFAULT,?,?,?,?,md5(?),?,?,?)";

        Connection con = ConexaoBD.getInstance().getConnection();

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, colab.getNomeCompleto());
            st.setString(2, colab.getFuncao());
            st.setString(3, colab.getEmail());
            st.setString(4, colab.getUsuario());
            st.setString(5, colab.getSenhaUsuario());
            String tmp = Character.toString(colab.getTipoUsuario());
            st.setString(6, tmp);
            st.setInt(7, colab.getComissaoPorServico());
            st.setBoolean(8, true);

            System.out.println("Query SALVAR colaborador: " + sql);

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

        String retorno = "";

        Colaborador colab = (Colaborador) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE colaborador SET nomecompleto = ?, funcao = ?, email = ?, usuario = ?,tipousuario = ?,comissaoporservico = ?  WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, colab.getNomeCompleto());
            st.setString(2, colab.getFuncao());
            st.setString(3, colab.getEmail());
            st.setString(4, colab.getUsuario());
            String tmp = Character.toString(colab.getTipoUsuario());
            st.setString(5, tmp);
            st.setInt(6, colab.getComissaoPorServico());
            st.setInt(7, colab.getCodigo());

            if (st.executeUpdate() == 1) {
                return null;
            }

            st.close();
            //con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar colaborador: " + e);
            return e.toString();
        }

        return retorno;

    }

    @Override
    public Boolean excluir(int id) {
        Boolean retorno = false;

        Colaborador colab = new Colaborador();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE colaborador SET ativo = ? WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setBoolean(1, false);
            st.setInt(2, id);

            if (st.executeUpdate() == 1) {
                retorno = true;
            }

            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao excluír colaborador: " + e);

        }

        return retorno;

    }
    
    public Boolean reativar(int id) {
        Boolean retorno = false;

        Colaborador colab = new Colaborador();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE colaborador SET ativo = ? WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setBoolean(1, true);
            st.setInt(2, id);

            if (st.executeUpdate() == 1) {
                retorno = true;
            }

            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao excluír colaborador: " + e);

        }

        return retorno;

    }

    
    @Override
    public ArrayList<Object> consultarTodos() {

        //Cria um ArrayList de Objetos
        ArrayList<Object> colaboradores = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM colaborador");

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Colaborador colab = new Colaborador();

                    colab.setCodigo(resultado.getInt("codigo"));
                    colab.setNomeCompleto(resultado.getString("nomecompleto"));
                    colab.setFuncao(resultado.getString("funcao"));
                    colab.setEmail(resultado.getString("email"));
                    colab.setUsuario(resultado.getString("usuario"));
                    colab.setSenhaUsuario(resultado.getString("senhausuario"));
                    colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                    colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                    colab.setAtivo(resultado.getBoolean("ativo"));

                    colaboradores.add(colab);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return colaboradores;
    }

    @Override
    public ArrayList<Object> consultar(String nome) {

        boolean retorno = false;

        ArrayList<Object> colaboradores = new ArrayList();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE nomecompleto ilike ? ";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, '%' + nome + '%');

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Colaborador colab = new Colaborador();

                    colab.setCodigo(resultado.getInt("codigo"));
                    colab.setNomeCompleto(resultado.getString("nomecompleto"));
                    colab.setFuncao(resultado.getString("funcao"));
                    colab.setEmail(resultado.getString("email"));
                    colab.setUsuario(resultado.getString("usuario"));
                    colab.setSenhaUsuario(resultado.getString("senhausuario"));
                    colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                    colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                    colab.setAtivo(resultado.getBoolean("ativo"));

                    colaboradores.add(colab);
                }
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            // return null;
        }

        return colaboradores;

    }

    public ArrayList<Colaborador> consultarPorNome(String nome) {

        boolean retorno = false;

        ArrayList<Colaborador> colaboradores = new ArrayList();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE nomecompleto  ILIKE  ? and ativo = true order by nomecompleto ";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, '%' + nome + '%');

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Colaborador colab = new Colaborador();

                    colab.setCodigo(resultado.getInt("codigo"));
                    colab.setNomeCompleto(resultado.getString("nomecompleto"));
                    colab.setFuncao(resultado.getString("funcao"));
                    colab.setEmail(resultado.getString("email"));
                    colab.setUsuario(resultado.getString("usuario"));
                    colab.setSenhaUsuario(resultado.getString("senhausuario"));
                    colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                    colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                    colab.setAtivo(resultado.getBoolean("ativo"));

                    colaboradores.add(colab);
                }
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            // return null;
        }

        return colaboradores;

    }

    public ArrayList<Colaborador> consultarPorNomeAtivo(String nome) {

        boolean retorno = false;

        ArrayList<Colaborador> colaboradores = new ArrayList();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE nomecompleto  ILIKE  ?  order by nomecompleto ";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, '%' + nome + '%');

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Colaborador colab = new Colaborador();

                    colab.setCodigo(resultado.getInt("codigo"));
                    colab.setNomeCompleto(resultado.getString("nomecompleto"));
                    colab.setFuncao(resultado.getString("funcao"));
                    colab.setEmail(resultado.getString("email"));
                    colab.setUsuario(resultado.getString("usuario"));
                    colab.setSenhaUsuario(resultado.getString("senhausuario"));
                    colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                    colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                    colab.setAtivo(resultado.getBoolean("ativo"));

                    colaboradores.add(colab);
                }
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            // return null;
        }

        return colaboradores;

    }

    @Override
    public Object consultarId(int id) {

        Colaborador colab = new Colaborador();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {

                colab.setCodigo(resultado.getInt("codigo"));
                colab.setNomeCompleto(resultado.getString("nomecompleto"));
                colab.setFuncao(resultado.getString("funcao"));
                colab.setEmail(resultado.getString("email"));
                colab.setUsuario(resultado.getString("usuario"));
                colab.setSenhaUsuario(resultado.getString("senhausuario"));
                colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                colab.setAtivo(resultado.getBoolean("ativo"));

            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            return null;
        }

        return colab;
    }

    public Boolean autenticarColaborador(String usuario, String senha) {

        boolean retorno = false;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE usuario ilike ? and senhausuario ilike md5(?)";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, usuario);
            st.setString(2, senha);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {

                retorno = true;

            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador com este usuário e senha: " + e);
            return null;
        }

        return retorno;
    }

    public void popularTabela(JTable tabela, String criterio) {
        ResultSet resultadoQ;

        Connection con = ConexaoBD.getInstance().getConnection();

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Email";
        cabecalho[3] = "Função";
        cabecalho[4] = "Usuário";
        cabecalho[5] = "Comissão(%)";
       /// cabecalho[6] = "Administrador";
        // cabecalho[6] = "Tipo Usuário"; 

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = con.createStatement().executeQuery(""
                    + "SELECT count(*) FROM colaborador "
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
                    + "SELECT * FROM colaborador "
                    + "WHERE nomecompleto ILIKE '%" + criterio + "%' AND ativo = true order by nomecompleto");

            char tipo;

            while (resultadoQ.next()) {
                tipo = resultadoQ.getString("tipousuario").charAt(0);
                dadosTabela[lin][0] = resultadoQ.getInt("codigo");
                dadosTabela[lin][1] = resultadoQ.getString("nomecompleto");
                dadosTabela[lin][2] = resultadoQ.getString("email");
                dadosTabela[lin][3] = resultadoQ.getString("funcao");
                dadosTabela[lin][4] = resultadoQ.getString("usuario");
                dadosTabela[lin][5] = resultadoQ.getInt("comissaoporservico");
                //dadosTabela[lin][6] = tipo;

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
        // renderizacao das linhas da tabela = mudar a cor
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        });
        //Centralizando as linhas da tabela. 

        //tabela.getColumnModel().getColumn(6).setCellRenderer(centralizado);
    }

    public boolean consultaNomeCompleto(String nomeCompleto) {

        boolean retorno = false;

        Servico serv = new Servico();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE nomecompleto ilike ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, nomeCompleto);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                retorno = true;
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            // return null;
        }

        return retorno;
    }

    public boolean consultaEmail(String email) {

        boolean retorno = false;

        Servico serv = new Servico();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT  FROM colaborador WHERE email ilike ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, email);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                retorno = true;
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            // return null;
        }

        return retorno;
    }

    public boolean consultaUsuario(String usuario) {

        boolean retorno = false;

        Servico serv = new Servico();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE usuario ilike ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, usuario);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                retorno = true;
            }
            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            // return null;
        }

        return retorno;
    }

    public Colaborador consultarUsuario(String usuario) {

        Colaborador colab = new Colaborador();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador WHERE usuario ilike ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, usuario);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {

                colab.setCodigo(resultado.getInt("codigo"));
                colab.setNomeCompleto(resultado.getString("nomecompleto"));
                colab.setFuncao(resultado.getString("funcao"));
                colab.setEmail(resultado.getString("email"));
                colab.setUsuario(resultado.getString("usuario"));
                colab.setSenhaUsuario(resultado.getString("senhausuario"));
                colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                colab.setAtivo(resultado.getBoolean("ativo"));

            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            return null;
        }

        return colab;

    }

    public ArrayList<Colaborador> consultarTodosColaboradoresAtivos() {

        //Cria um ArrayList de Objetos
        ArrayList<Colaborador> colaboradores = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM colaborador WHERE ativo = true");

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Colaborador colab = new Colaborador();

                    colab.setCodigo(resultado.getInt("codigo"));
                    colab.setNomeCompleto(resultado.getString("nomecompleto"));
                    colab.setFuncao(resultado.getString("funcao"));
                    colab.setEmail(resultado.getString("email"));
                    colab.setUsuario(resultado.getString("usuario"));
                    colab.setSenhaUsuario(resultado.getString("senhausuario"));
                    colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                    colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                    colab.setAtivo(resultado.getBoolean("ativo"));

                    colaboradores.add(colab);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return colaboradores;
    }

    

    public ArrayList<Colaborador> consultarTodosColaboradores() {

        //Cria um ArrayList de Objetos
        ArrayList<Colaborador> colaboradores = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM colaborador");

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Colaborador colab = new Colaborador();

                    colab.setCodigo(resultado.getInt("codigo"));
                    colab.setNomeCompleto(resultado.getString("nomecompleto"));
                    colab.setFuncao(resultado.getString("funcao"));
                    colab.setEmail(resultado.getString("email"));
                    colab.setUsuario(resultado.getString("usuario"));
                    colab.setSenhaUsuario(resultado.getString("senhausuario"));
                    colab.setTipoUsuario(resultado.getString("tipousuario").charAt(0));
                    colab.setComissaoPorServico(resultado.getInt("comissaoporservico"));
                    colab.setAtivo(resultado.getBoolean("ativo"));

                    colaboradores.add(colab);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return colaboradores;
    }

   
    
    public Boolean consultarSenha(String usuario, String senha) {

        boolean retorno = false;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT senhausuario FROM colaborador WHERE usuario ilike ? and senhausuario ilike md5(?)";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, usuario);
            st.setString(2, senha);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {

                retorno = true;

            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador com este usuário e senha: " + e);
            retorno = false;
        }

        return retorno;
    }

    public String alteraSenha(Object o) {

        String retorno = "";

        Colaborador colab = (Colaborador) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE colaborador SET senhausuario = md5(?)  WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, colab.getSenhaUsuario());
            st.setInt(2, colab.getCodigo());

            if (st.executeUpdate() == 1) {
                return null;
            }

            st.close();
            //con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar colaborador: " + e);
            return e.toString();
        }

        return retorno;

    }

}
