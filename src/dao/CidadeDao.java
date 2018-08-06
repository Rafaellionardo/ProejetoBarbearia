
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Cidade;
import entidade.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author renan
 */
public class CidadeDao implements Idao {

    @Override
    public boolean salvar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String atualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        
         //Cria um ArrayList de Objetos
        ArrayList<Object> cidades = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM cidade");

            if (resultado.isBeforeFirst()) {
                
                Estado est = new Estado(); 
                EstadoDao dao =  new EstadoDao(); 
               
                
                while (resultado.next()) {
                    est = (Estado) dao.consultarId(resultado.getInt("estado")); 
                    Cidade cid = new Cidade();
                    cid.setNome(resultado.getString("nome"));
                    cid.setCodigo(resultado.getInt("codigo"));
                    cid.setEstado(est);
                    
                    cidades.add(cid);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return cidades;

        
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> cidades =  new ArrayList(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cidade  WHERE nome ilike ?";
        
        
         try {

            
            PreparedStatement st = con.prepareStatement(sql); 
          
             st.setString(1, '%'+criterio+'%'); 

            ResultSet resultado = st.executeQuery();
            
            EstadoDao dao = new EstadoDao(); 
            
             if (resultado.isBeforeFirst()) {
               
                 while (resultado.next()) {
                   
                    Cidade cid = new Cidade(); 
                    cid.setCodigo(resultado.getInt("codigo"));
                    cid.setNome(resultado.getString("nome"));
                    cid.setEstado((Estado) dao.consultarId(resultado.getInt("estado")));
                    cidades.add(cid);
                }
            }  
            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            return null;
        }
       
        return cidades; 
    }
    
     public ArrayList<Cidade> consultarTodasCidades() {
        
         //Cria um ArrayList de Objetos
        ArrayList<Cidade> cidades = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM cidade");

            if (resultado.isBeforeFirst()) {
                
                Estado est = new Estado(); 
                EstadoDao dao =  new EstadoDao(); 
               
                
                while (resultado.next()) {
                    est = (Estado) dao.consultarId(resultado.getInt("estado")); 
                    Cidade cid = new Cidade();
                    cid.setNome(resultado.getString("nome"));
                    cid.setCodigo(resultado.getInt("codigo"));
                    cid.setEstado(est);
                    
                    cidades.add(cid);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return cidades;
     }
    
    
    @Override
    public Object consultarId(int id) {
        Cidade cidade =  new Cidade(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cidade  WHERE codigo = ?";
        
        
         try {

            
            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setInt(1, id); 

            ResultSet resultado = st.executeQuery();
            
            EstadoDao dao = new EstadoDao(); 
            
            if (resultado.next()) {
                
                cidade.setCodigo(resultado.getInt("codigo"));
                cidade.setNome(resultado.getString("nome"));
                cidade.setEstado((Estado) dao.consultarId(resultado.getInt("estado")));
  
            } 

            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            return null;
        }
       
        return cidade; 

    }
    
    public ArrayList<Cidade> consultarPorNome(String criterio) {
        ArrayList<Cidade> cidades =  new ArrayList(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cidade  WHERE nome ilike ?";
        
        
         try {

            
            PreparedStatement st = con.prepareStatement(sql); 
          
             st.setString(1, criterio+'%'); 

            ResultSet resultado = st.executeQuery();
            
            EstadoDao dao = new EstadoDao(); 
            
             if (resultado.isBeforeFirst()) {
               
                 while (resultado.next()) {
                   
                    Cidade cid = new Cidade(); 
                    cid.setCodigo(resultado.getInt("codigo"));
                    cid.setNome(resultado.getString("nome"));
                    cid.setEstado((Estado) dao.consultarId(resultado.getInt("estado")));
                    cidades.add(cid);
                }
            }  
            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar cidade: " + e);
            return null;
        }
       
        return cidades; 
    }
    
    
     
    public Cidade consultarPorCodigo(int id) {
        Cidade cidade =  new Cidade(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM cidade  WHERE codigo = ?";
        
        
         try {

            
            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setInt(1, id); 

            ResultSet resultado = st.executeQuery();
            
            EstadoDao dao = new EstadoDao(); 
            
            if (resultado.next()) {
                
                cidade.setCodigo(resultado.getInt("codigo"));
                cidade.setNome(resultado.getString("nome"));
                cidade.setEstado((Estado) dao.consultarId(resultado.getInt("estado")));
  
            } 

            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            return null;
        }
       
        return cidade; 

    }
}
