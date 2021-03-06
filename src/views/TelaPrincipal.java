/*
 * TelaPrincipal.java
 *
 * Created on 23 de Novembro de 2002, 15:11
 */

package views;

//import negocios.*;

/**
 *
 * @author  leme
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    /** Creates new form TelaPrincipal */
    public TelaPrincipal() {
        initComponents();
//        Consultorio.Instance().efetuaBalanco(false);
        setSize(1029,764);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        menuPrincipal = new javax.swing.JMenuBar();
        menuPacientes = new javax.swing.JMenu();
        menuCadastro = new javax.swing.JMenuItem();
        menuConsulta = new javax.swing.JMenuItem();
        menuRelatorio = new javax.swing.JMenuItem();
        menuConsultaV = new javax.swing.JMenuItem();
        menuContabilidade = new javax.swing.JMenu();
        menuCobranca = new javax.swing.JMenuItem();
        menuDespesa = new javax.swing.JMenuItem();
        menuBalanco = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        menuGeraBalanco = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Agendamento");
        setName("telaPrincipal"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/como-montar-uma-barbearia.jpg"))); // NOI18N
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        desktop.add(jButton1);
        jButton1.setBounds(0, 0, 1100, 660);

        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);

        menuPrincipal.setName("menuPrincipal"); // NOI18N

        menuPacientes.setMnemonic('P');
        menuPacientes.setText("Clientes");
        menuPacientes.setName("menuPacientes"); // NOI18N
        menuPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPacientesActionPerformed(evt);
            }
        });

        menuCadastro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuCadastro.setMnemonic('C');
        menuCadastro.setText("Cadastro de Profissional");
        menuCadastro.setName("menuCadastro"); // NOI18N
        menuCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroActionPerfored(evt);
            }
        });
        menuPacientes.add(menuCadastro);

        menuConsulta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuConsulta.setMnemonic('A');
        menuConsulta.setText("Agendamento");
        menuConsulta.setName("menuConsulta"); // NOI18N
        menuConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultaActionPerformed(evt);
            }
        });
        menuPacientes.add(menuConsulta);

        menuRelatorio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuRelatorio.setMnemonic('R');
        menuRelatorio.setText("Relatórios");
        menuRelatorio.setName("menuRelatorio"); // NOI18N
        menuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioActionPerformed(evt);
            }
        });
        menuPacientes.add(menuRelatorio);

        menuConsultaV.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuConsultaV.setMnemonic('P');
        menuConsultaV.setText("Trabalho Marcadas");
        menuConsultaV.setName("menuExame"); // NOI18N
        menuConsultaV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultaVActionPerformed(evt);
            }
        });
        menuPacientes.add(menuConsultaV);

        menuPrincipal.add(menuPacientes);

        menuContabilidade.setMnemonic('C');
        menuContabilidade.setLabel("Contabilidade");
        menuContabilidade.setName("menuContabilidade"); // NOI18N

        menuCobranca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menuCobranca.setMnemonic('P');
        menuCobranca.setText("Previsão de pagamento");
        menuCobranca.setName("menuCobranca"); // NOI18N
        menuCobranca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCobrancaActionPerformed(evt);
            }
        });
        menuContabilidade.add(menuCobranca);

        menuDespesa.setMnemonic('R');
        menuDespesa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuDespesa.setText("Registro de despesa");
        menuDespesa.setName("menuDespesa"); // NOI18N
        menuDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDespesaActionPerformed(evt);
            }
        });
        menuContabilidade.add(menuDespesa);

        menuBalanco.setMnemonic('B');
        menuBalanco.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        menuBalanco.setText("Balanço mensal");
        menuBalanco.setName("menuBalanco"); // NOI18N
        menuBalanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBalancoActionPerformed(evt);
            }
        });
        menuContabilidade.add(menuBalanco);
        menuContabilidade.add(jSeparator1);

        menuGeraBalanco.setText("Gera balanço");
        menuGeraBalanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGeraBalancoActionPerformed(evt);
            }
        });
        menuContabilidade.add(menuGeraBalanco);

        menuPrincipal.add(menuContabilidade);

        jMenu4.setText("voltar");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4volar(evt);
            }
        });
        menuPrincipal.add(jMenu4);

        jMenu2.setText("sair");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2sair(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        menuPrincipal.add(jMenu2);

        setJMenuBar(menuPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuGeraBalancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGeraBalancoActionPerformed
        //Consultorio.Instance().efetuaBalanco(true);
    }//GEN-LAST:event_menuGeraBalancoActionPerformed

    private void menuPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPacientesActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_menuPacientesActionPerformed

    private void menuBalancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBalancoActionPerformed
        new TelaBalanco(this,true).show();
    }//GEN-LAST:event_menuBalancoActionPerformed

    private void menuDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDespesaActionPerformed
        new TelaDespesa(this,true).show();
    }//GEN-LAST:event_menuDespesaActionPerformed

    private void menuCobrancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCobrancaActionPerformed
       new TelaCobranca(this,true).show();
    }//GEN-LAST:event_menuCobrancaActionPerformed

    private void menuConsultaVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultaVActionPerformed
        new TelaTrabalhos(this,true).show();
    }//GEN-LAST:event_menuConsultaVActionPerformed

    private void menuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioActionPerformed
        new TelaRelatorio(this,true).show();
    }//GEN-LAST:event_menuRelatorioActionPerformed

    private void menuConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultaActionPerformed
        Agendamento tela = new Agendamento();
        tela.setVisible(true);
        dispose();
    }//GEN-LAST:event_menuConsultaActionPerformed

    private void menuCadastroActionPerfored(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroActionPerfored
         TelaProfissional tela = new TelaProfissional();
         tela.setVisible(true);
              
    }//GEN-LAST:event_menuCadastroActionPerfored
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
          longin jfi = new longin();
        jfi.setVisible(true);
        dispose();
    }//GEN-LAST:event_exitForm

    private void jMenu2sair(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2sair
        // TODO add your handling code here:
        //conex.desconecta();
        longin tela = new longin();
        tela.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu2sair

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu4volar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4volar
        // TODO add your handling code here:
        Principal tela = new Principal();
        tela.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu4volar

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new TelaPrincipal().show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem menuBalanco;
    private javax.swing.JMenuItem menuCadastro;
    private javax.swing.JMenuItem menuCobranca;
    private javax.swing.JMenuItem menuConsulta;
    private javax.swing.JMenuItem menuConsultaV;
    private javax.swing.JMenu menuContabilidade;
    private javax.swing.JMenuItem menuDespesa;
    private javax.swing.JMenuItem menuGeraBalanco;
    private javax.swing.JMenu menuPacientes;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JMenuItem menuRelatorio;
    // End of variables declaration//GEN-END:variables
    
}
