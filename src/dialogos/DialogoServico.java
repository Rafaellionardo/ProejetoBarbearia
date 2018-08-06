/*
 * DialogoServico.java
 *
 * Created on 6 de Dezembro de 2002, 10:17
 */

package dialogos;

import negocios.*;
import java.util.*;
import java.lang.*;

/**
 *
 * @author  regisb
 */
public class DialogoServico extends javax.swing.JDialog {
    Servico servico = null;
    
    /** Creates new form DialogoServico */
    public DialogoServico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextFieldServico = new javax.swing.JTextField();
        jTextFieldValor = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setTitle("Adiciona Servi\u00e7o");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(null);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 250));
        jLabel1.setText("Servi\u00e7o");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 60, 60, 23);

        jLabel2.setText("Valor");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 110, 52, 23);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.add(jButton1);
        jButton1.setBounds(90, 180, 90, 33);

        jPanel1.add(jTextFieldServico);
        jTextFieldServico.setBounds(110, 60, 230, 27);

        jPanel1.add(jTextFieldValor);
        jTextFieldValor.setBounds(110, 110, 90, 27);

        jButton2.setText("Cancelar");
        jPanel1.add(jButton2);
        jButton2.setBounds(200, 180, 95, 33);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            servico = new Servico (jTextFieldServico.getText (), new Quantity(new Double (jTextFieldValor.getText()).doubleValue(),Unit.get(Unit.REAL)));
            new DialogoConfirmacao(new javax.swing.JFrame(), true, "", "Servi�o adicionado").show();
            setVisible(false);
            dispose();
        } 
        catch (NumberFormatException e) {
            new DialogoConfirmacao(new javax.swing.JFrame(), true, "", "Erro no campo Valor").show();
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    
    public Servico getServico () {
        return servico;
    }
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new DialogoServico(new javax.swing.JFrame(), true).show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextFieldValor;
    private javax.swing.JTextField jTextFieldServico;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
}
