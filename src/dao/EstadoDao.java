/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Idao;
import entidade.Colaborador;
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
public class EstadoDao implements Idao{

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
        ArrayList<Object> estados = new ArrayList<>();

        try {
            //Cria um objeto do tipo "Statement" chamando a classe de ConexãoDB
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //Cria um objeto do tipo ResultSet que armazena o resultado adquirido da query
            ResultSet resultado = st.executeQuery("SELECT * FROM estado");

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    // captura dados do ResultSet
                    Estado est = new Estado();

                    est.setNome(resultado.getString("nome"));
                    est.setUf(resultado.getString("uf"));
                   

                    est.setCodigo(resultado.getInt("codigo"));

                    estados.add(est);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro consultar xxx = " + e);
        }
        return estados;

    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        
        Estado estado =  new Estado(); 
 
        Connection con = ConexaoBD.getInstance().getConnection();

        String sql = "SELECT * FROM estado  WHERE codigo = ?";
        
        
         try {

            
            PreparedStatement st = con.prepareStatement(sql); 
          
            st.setInt(1, id); 

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                
                estado.setCodigo(resultado.getInt("codigo"));
                estado.setNome(resultado.getString("nome"));
                estado.setUf(resultado.getString("uf"));

                
            } 

            //con.close(); 
            st.close(); 
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar colaborador: " + e);
            return null;
        }
       
        return estado; 
        
        
        
        
        
        
    }
    
}
