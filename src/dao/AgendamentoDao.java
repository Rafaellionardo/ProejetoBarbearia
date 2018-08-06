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
import entidade.Colaborador;
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
public class AgendamentoDao implements Idao {

    @Override
    public boolean salvar(Object o) {

        Agendamento agen = (Agendamento) o;

        boolean retorno = false;
            //alterar string para conversar
        String sql = "INSERT INTO agenda "
                + "VALUES(DEFAULT,?,?,?,)";

        Connection con = ConexaoBD.getInstance().getConnection();

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, agen.getCliente().getCodigo());
            st.setInt(2, agen.getColaborador().getCodigo());
            st.setDouble(3, agen.getValorTotal());
            st.setDate(4, agen.getDia());
            
            System.out.println("Query SALVAR agendamento: " + sql);
            int resultado = st.executeUpdate();

            if (resultado != 0) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("Erro inserir agendamento  = " + e);
        }
        return retorno;

    }

    public int salvarRetornandoId(Agendamento agen) {
        int idObjeto = 0;

        boolean retorno = false;

        String sql = "INSERT INTO agendamento "
                + "VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?,?)";

        Connection con = ConexaoBD.getInstance().getConnection();

        try {

            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            st.setInt(1, agen.getCliente().getCodigo());
            st.setInt(2, agen.getColaborador().getCodigo());
            st.setDouble(3, agen.getValorTotal());
            st.setDate(4, agen.getDia());
            st.setString(5, agen.getComentario());
            String tmp = agen.getStatus() + "";
            st.setString(6, tmp);
            st.setDouble(7, agen.getDescontoVenda());
            st.setString(8, agen.getDescricaoDesconto());
            st.setString(9, agen.getMotivoCancelamento());
            st.setBoolean(10, true);
            st.setBoolean(11, false);

            System.out.println("Query SALVAR agendamento: " + sql);

            int resultado = st.executeUpdate();

            //pega chave do objeto           
            ResultSet rs = st.getGeneratedKeys();

            while (rs.next()) {
                idObjeto = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Erro inserir agendamento  = " + e);
        }
        return idObjeto;

    }

    @Override
    public String atualizar(Object o) {
        String retorno = "";

        Agendamento agen = (Agendamento) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE agendamento "
                + "SET cliente = ?,"
                + "colaborador = ?, "
                + "valortotal = ?,"
                + "dia = ?,"
                + "comentario = ?,"
                + "status = ?, "
                + "descontovenda = ?,"
                + "descricaodesconto = ?,"
                + "motivocancelamento = ?,"
                + "pago = ?"
                + " WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, agen.getCliente().getCodigo());
            st.setInt(2, agen.getColaborador().getCodigo());
            st.setDouble(3, agen.getValorTotal());
            st.setDate(4, agen.getDia());
            st.setString(5, agen.getComentario());
            st.setString(6, agen.getStatus() + "");
            st.setDouble(7, agen.getDescontoVenda());
            st.setString(8, agen.getDescricaoDesconto());
            st.setString(9, agen.getMotivoCancelamento());
            st.setInt(11, agen.getCodigo());
            st.setBoolean(10, agen.getPago());

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

        Boolean retorno = false;

        Cliente clie = new Cliente();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE agendamento SET ativo = ? WHERE codigo = ?";

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

    public Boolean cancelaAgendamento(Agendamento agendamento) {

        Boolean retorno = false;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE agendamento "
                + "SET status = ?"
                //+ "SET descricaocancelamento ?,"
                + "WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, agendamento.getStatus() + "");
            //st.setString(2, agendamento.getMotivoCancelamento()); 
            st.setInt(2, agendamento.getCodigo());

            if (st.executeUpdate() == 1) {
                retorno = true;
            }

            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao cancelar agendamento: " + e);

        }

        return retorno;

    }

    @Override
    public ArrayList<Object> consultarTodos() {
        //Cria um ArrayList de Objetos
        ArrayList<Object> agendamentos = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM agendamento");

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("motivocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Agendamento> consultarTodosAgendamentos() {
        //Cria um ArrayList de Objetos
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM agendamento");

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("descricaocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    @Override
    public Object consultarId(int id) {

        Agendamento agen = new Agendamento();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM agendamento WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet resultado = st.executeQuery();

            ClienteDao daoc = new ClienteDao();
            ColaboradorDao daoco = new ColaboradorDao();

            if (resultado.next()) {

                agen.setCodigo(resultado.getInt("codigo"));
                agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                agen.setMoticoCancelamento(resultado.getString("motivocancelamento"));
                agen.setComentario(resultado.getString("comentario"));
                agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                agen.setDia(resultado.getDate("dia"));
                agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                agen.setPago(resultado.getBoolean("pago"));

                String tmp = resultado.getString("status");

                agen.setStatus(tmp.charAt(0));
                agen.setValorTotal(resultado.getDouble("valortotal"));

            }

            //con.close(); 
            st.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
            return null;
        }

        return agen;

    }

    public ArrayList<Agendamento> consultarPorData(Date data) {
        //Cria um ArrayList de Objetos
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM agendamento WHERE dia = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setDate(1, data);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("descricaocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    public ArrayList<Agendamento> consultarPorPeriodo(Date dataInicial, Date dataFinal) {
        //Cria um ArrayList de Objetos
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM agendamento WHERE dia BETWEEN  ? and ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setDate(1, dataInicial);
            st.setDate(2, dataFinal);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("descricaocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    public ArrayList<Agendamento> consultarPorStatus(String status) {
        //Cria um ArrayList de Objetos
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM agendamento WHERE status ilike  ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, status);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("descricaocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    public ArrayList<Agendamento> consultarPorCliente(String cliente) {
        //Cria um ArrayList de Objetos
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cliente c, agendamento a where c.nomecompleto ilike ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, cliente);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("descricaocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    public ArrayList<Agendamento> consultarPorColaborador(String cliente) {
        //Cria um ArrayList de Objetos
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM colaborador c, agendamento a where c.nomecompleto ilike ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, cliente);

            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("descricaocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    public ArrayList<Agendamento> consultaFiltros(Date dataInicial, Date dataFinal, String nomeCliente, char Status) {
        //Cria um ArrayList de Objetos
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT a.codigo,\n"
                + "a.colaborador,\n"
                + "a.cliente,\n"
                + "a.comentario,\n"
                + "a.descontovenda,\n"
                + "a.dia,\n"
                + "a.descricaoDesconto,\n"
                + "a.status,\n"
                + "a.motivocancelamento,\n"
                + "a.pago,\n"
                + "a.valorTotal  "
                + "FROM cliente c INNER JOIN  agendamento a ON c.codigo = a.cliente "
                + "where  c.ativo = true";

        if (!nomeCliente.equals("")) {
            sql += " AND c.nomecompleto ilike '"+nomeCliente+"%'";
        }

        if (dataInicial != null && dataFinal != null) {
            sql += " AND a.dia BETWEEN '"+dataInicial+"' AND '"+dataFinal+"'";
            System.out.println("Teste entrou na data");
            System.out.println(dataInicial);
            System.out.println(dataFinal);
            
        }

        if (Status != '\0') {

            sql += " and a.status = '"+Status+"'";
        }else if(Status == '\0'){
            
               sql += " and a.status = 'A'";
        
        }

        sql += " order by a.dia";

        System.out.println(sql);
        try {
            
            PreparedStatement st = con.prepareStatement(sql);


            ResultSet resultado = st.executeQuery();

            if (resultado.isBeforeFirst()) {

                ClienteDao daoc = new ClienteDao();
                ColaboradorDao daoco = new ColaboradorDao();

                while (resultado.next()) {
                    // captura dados do ResultSet
                    Agendamento agen = new Agendamento();

                    agen.setCodigo(resultado.getInt("codigo"));
                    agen.setCliente((Cliente) daoc.consultarId(resultado.getInt("cliente")));
                    agen.setColaborador((Colaborador) daoco.consultarId(resultado.getInt("colaborador")));
                    agen.setMoticoCancelamento(resultado.getString("motivocancelamento"));
                    agen.setComentario(resultado.getString("comentario"));
                    agen.setDescontoVenda(resultado.getDouble("descontovenda"));
                    agen.setDia(resultado.getDate("dia"));
                    agen.setDescricaoDesconto(resultado.getString("descricaodesconto"));
                    agen.setPago(resultado.getBoolean("pago"));

                    String tmp = resultado.getString("status");

                    agen.setStatus(tmp.charAt(0));
                    agen.setValorTotal(resultado.getDouble("valortotal"));

                    agendamentos.add(agen);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return agendamentos;

    }

    public ArrayList<Agendamento> consultarPorPerido(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
