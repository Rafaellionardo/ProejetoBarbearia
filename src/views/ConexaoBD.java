/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class ConexaoBD {
   public Statement stm;
   public ResultSet rs;
   public String driver = "org.postgree.Driver";
   public String caminho="jdbc:postgresql://localhost:5432/Barbearia";
   public String usuario ="postgres";
   public String senha="lionardo";
   public Connection con;

    public ConexaoBD() {
       
    }
   
   public void conexao(){
       
       try {
           System.setProperty("jdbc.Dribers",driver);
           con=DriverManager.getConnection(caminho,usuario,senha);
          
       } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Conexão nao  realizada \n"+ex.getMessage());
       }
        
   }
   public void executaSql(String sql){
       try {
           stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
           rs = stm.executeQuery(sql);
       }catch (SQLException ex){
         //  JOptionPane.showMessageDialog(null,"erro ao executa sql \n"+ex.getMessage());

       }
   
   }
   
   public void desconecta(){
       try {
           con.close();
            JOptionPane.showMessageDialog(null,"desconectado");

       } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null," erro ao fechar o banco de dados \n"+e.getMessage());

       }
   }

    void executeSql(String sql) {
       try {
           stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           rs = stm.executeQuery(sql);
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," erro ao executar executa sql \n"+ex.getMessage());

       }
    }

    
    PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} 
