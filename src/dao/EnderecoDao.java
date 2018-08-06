/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Cidade;
import entidade.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class EnderecoDao implements Idao{

    @Override
    public boolean salvar(Object o) {
        Endereco end = (Endereco) o;
        
        boolean retorno = false;
        
        String sql ="INSERT INTO endereco VALUES(DEFAULT,?,?,?,?,?,?)";  
       
        Connection con =  ConexaoBD.getInstance().getConnection();

        
        try {
           
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, end.getCidade().getCodigo());
            st.setString(2, end.getBairro());
            st.setString(3, end.getRua());
            st.setString(4, end.getNumero());
            st.setString(5, end.getComplemento());          
            st.setBoolean(6 ,  true); 
           
            
            
            System.out.println("Query SALVAR endereco: " + sql);

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
        
        Endereco end = (Endereco) o;

        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "UPDATE endereco SET cidade  = ?, bairro = ?, rua = ?, numero = ?, complemento = ?, ativo = ?  WHERE codigo = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, end.getCidade().getCodigo());
            st.setString(2, end.getBairro());
            st.setString(3, end.getRua());
            st.setString(4, end.getNumero());          
            st.setString(5 , end.getComplemento()); 
            st.setBoolean(6 , end.isAtivo());
            st.setInt(7 , end.getCodigo());
            
            if (st.executeUpdate() == 1) {
                return null;
            }
            
            st.close();
            //con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar endereço: " + e);
            return e.toString();
        }
        
        return retorno; 
   
    }

    @Override
    public Boolean excluir(int id) {
        
        Boolean retorno =  false; 
        
        Endereco end =  new Endereco(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "DELETE from endereco WHERE codigo = ?";
        
        
         try {

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
           
      

            if (st.executeUpdate() == 1) {
                retorno = true; 
            }
            
            st.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao excluír endereço: " + e);
           
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
         
         Endereco end = new Endereco(); 
        
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM endereco WHERE codigo = ?";
        
        
         try {

            
            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setInt(1, id); 

            ResultSet resultado = st.executeQuery();
            
            CidadeDao cidDao = new CidadeDao(); 
            
            if (resultado.next()) {
                
                System.out.println(resultado.getInt("cidade"));
               
                end.setCodigo(resultado.getInt("codigo"));
                end.setBairro(resultado.getString("bairro"));
                end.setRua(resultado.getString("rua"));
                end.setComplemento(resultado.getString("complemento"));   
                end.setNumero(resultado.getString("numero"));  
                end.setAtivo(resultado.getBoolean("ativo"));
                end.setCidade((Cidade) cidDao.consultarId(resultado.getInt("cidade")));
            } 

            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar endereço: " + e);
            return null;
        }
       
        return end;   

        
    }
    
    
    public int salvarRetornandoId(Endereco endereco) {
        
        int idObjeto = 0;
        
        String sql ="INSERT INTO endereco VALUES(DEFAULT,?,?,?,?,?,?)";  
       
        Connection con =  ConexaoBD.getInstance().getConnection();

        
        try {
           
            PreparedStatement st = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            st.setInt(1, endereco.getCidade().getCodigo());
            st.setString(2, endereco.getBairro());
            st.setString(3, endereco.getRua());
            st.setString(4, endereco.getNumero());
            st.setString(5, endereco.getComplemento());          
            st.setBoolean(6 ,  true); 
           
            
            
            System.out.println("Query SALVAR endereco: " + sql);

            int resultado = st.executeUpdate();
            
            //pega chave do objeto           
            ResultSet rs = st.getGeneratedKeys();
            
            
            while (rs.next()) {
                idObjeto = rs.getInt(1); 
            }
        } catch (Exception e) {
            System.out.println("Erro salvar servico  = " + e);
        }
        return idObjeto;
    
    }
    
    
    public Endereco  consultarPorCodigo(int codigo) {
        Endereco e =  new Endereco(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM endereco  WHERE codigo = ?";
        
        
         
            
            PreparedStatement st; 
        try {
            st = con.prepareStatement(sql);
       
          
            st.setInt(1, codigo); 

            ResultSet resultado = st.executeQuery();
            
            CidadeDao dao = new CidadeDao(); 
            
            if (resultado.next()) {
                
                e.setCodigo(resultado.getInt("codigo"));
                e.setBairro(resultado.getString("bairro"));
                e.setNumero(resultado.getString("numero"));
                e.setComplemento(resultado.getString("complemento"));
                e.setRua(resultado.getString("rua"));
                e.setCidade((Cidade) dao.consultarId(resultado.getInt("cidade")));
  
            } 

            //con.close(); 
            st.close(); 
             } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
       
        return e;
        
    
    }
    
}
