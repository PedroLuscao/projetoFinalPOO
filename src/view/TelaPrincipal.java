/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ControleLancamento;
import model.Despesa;
import model.Lancamento;
import model.Receita;
import model.TipoDespesa;
import model.TipoReceita;

/**
 *
 * @author pedro
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal2
     */
    public TelaPrincipal() {
        initComponents();
        atualizarTabela();
    }
    
    public void atualizarTabela(){
        String filtro = (String) cmbFiltroOrigem.getSelectedItem();
        if(filtro.equals("Todos")){
            listarAmbos();
            cmbFiltroDespesa.setEnabled(false);
            cmbFiltroReceita.setEnabled(false);
        } else if (filtro.equals("Receitas")){
            listarReceitas();
            cmbFiltroReceita.setEnabled(true);
            cmbFiltroDespesa.setEnabled(false);
        } else if (filtro.equals("Despesas")){
            listarDespesas();
            cmbFiltroDespesa.setEnabled(true);
            cmbFiltroReceita.setEnabled(false);
        }
    }
    
    private void listarDespesas(){
        ArrayList<Lancamento> lista = ControleLancamento.getListaLancamentos();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TipoDespesa filtro = null;
        if(cmbFiltroDespesa.isEnabled()){
            filtro = (TipoDespesa) cmbFiltroDespesa.getSelectedItem();
        }
        model.setRowCount(0);
        Object rowData[] = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            if (lista.get(i) instanceof Despesa){
                if (cmbFiltroDespesa.isEnabled()){
                    if(((Despesa) lista.get(i)).getTipoDespesa()== filtro){
                        rowData[0] = "Despesa";
                        rowData[1] = lista.get(i).getValor();
                        rowData[2] = lista.get(i).getDataLancamento();
                        rowData[3] = ((Despesa) lista.get(i)).getTipoDespesa();
                        model.addRow(rowData);
                    }
                } else {
                    rowData[0] = "Despesa";
                    rowData[1] = lista.get(i).getValor();
                    rowData[2] = lista.get(i).getDataLancamento();
                    rowData[3] = ((Despesa) lista.get(i)).getTipoDespesa();
                    model.addRow(rowData);
                }
            }
        }
    }
    
    private void listarReceitas(){
        ArrayList<Lancamento> lista = ControleLancamento.getListaLancamentos();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TipoReceita filtro = null;
        if(cmbFiltroReceita.isEnabled()){
            filtro = (TipoReceita) cmbFiltroReceita.getSelectedItem();
        }
        System.out.println(filtro);
        model.setRowCount(0);
        Object rowData[] = new Object[4];
        for(int i = 0; i < lista.size(); i++){   
            if (lista.get(i) instanceof Receita){
                if(cmbFiltroReceita.isEnabled()){  
                    if(((Receita) lista.get(i)).getTipoReceita() == filtro){
                        rowData[0] = "Receita";
                        rowData[1] = lista.get(i).getValor();
                        rowData[2] = lista.get(i).getDataLancamento();
                        rowData[3] = ((Receita) lista.get(i)).getTipoReceita();
                        model.addRow(rowData);
                    }
                } else {
                    rowData[0] = "Receita";
                    rowData[1] = lista.get(i).getValor();
                    rowData[2] = lista.get(i).getDataLancamento();
                    rowData[3] = ((Receita) lista.get(i)).getTipoReceita();
                    model.addRow(rowData);
                }
            }
        }

    }
        
    
    
    private void listarAmbos(){
        ArrayList<Lancamento> lista = ControleLancamento.getListaLancamentos();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            if (lista.get(i) instanceof Receita){
                rowData[0] = "Receita";
                rowData[1] = lista.get(i).getValor();
                rowData[2] = lista.get(i).getDataLancamento();
                rowData[3] = ((Receita) lista.get(i)).getTipoReceita();
            }
            if (lista.get(i) instanceof Despesa){
                rowData[0] = "Despesa";
                rowData[1] = lista.get(i).getValor();
                rowData[2] = lista.get(i).getDataLancamento();
                rowData[3] = ((Despesa) lista.get(i)).getTipoDespesa();
            }
            model.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbFiltroOrigem = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCheckSaldoAteHoje = new javax.swing.JButton();
        btnChegarSaldoAteHoje = new javax.swing.JButton();
        btnRemoveLancamento = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnAddLancamento = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbFiltroDespesa = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbFiltroReceita = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle de Receitas e Despesas");

        jLabel2.setText("Origem de Lançamento");

        cmbFiltroOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Receitas", "Despesas" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origem", "Valor", "Data", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setMinWidth(150);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        btnCheckSaldoAteHoje.setText("Checar Saldo até Hoje");
        btnCheckSaldoAteHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckSaldoAteHojeActionPerformed(evt);
            }
        });

        btnChegarSaldoAteHoje.setText("Checar Saldo Total");
        btnChegarSaldoAteHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChegarSaldoAteHojeActionPerformed(evt);
            }
        });

        btnRemoveLancamento.setEnabled(false);
        btnRemoveLancamento.setText("Remover Lançamento");
        btnRemoveLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLancamentoActionPerformed(evt);
            }
        });

        jButton1.setText("Editar Lançamento");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAddLancamento.setText("Adicionar Lançamento");
        btnAddLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLancamentoActionPerformed(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo Despesa");

        cmbFiltroDespesa.setModel(new javax.swing.DefaultComboBoxModel<>(TipoDespesa.values()));
        cmbFiltroDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroDespesaActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo Receita");

        cmbFiltroReceita.setModel(new javax.swing.DefaultComboBoxModel<>(TipoReceita.values()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cmbFiltroOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cmbFiltroDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cmbFiltroReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisar)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAddLancamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(129, 129, 129)
                        .addComponent(btnRemoveLancamento))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(btnCheckSaldoAteHoje)
                        .addGap(117, 117, 117)
                        .addComponent(btnChegarSaldoAteHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbFiltroOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFiltroDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFiltroReceita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChegarSaldoAteHoje)
                    .addComponent(btnCheckSaldoAteHoje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddLancamento)
                    .addComponent(btnRemoveLancamento)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChegarSaldoAteHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChegarSaldoAteHojeActionPerformed
        JOptionPane.showMessageDialog(null,"Seu saldo total é " + String.format("%1$,.2f", ControleLancamento.calcularSaldo()));
    }//GEN-LAST:event_btnChegarSaldoAteHojeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAddLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLancamentoActionPerformed
        new EscolherOrigem(this, true).setVisible(true);
        atualizarTabela();
    }//GEN-LAST:event_btnAddLancamentoActionPerformed

    private void btnRemoveLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLancamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoveLancamentoActionPerformed

    private void btnCheckSaldoAteHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckSaldoAteHojeActionPerformed
        JOptionPane.showMessageDialog(null,"Seu saldo até hoje é " + String.format("%1$,.2f", ControleLancamento.calcularSaldoAteHj()));
    }//GEN-LAST:event_btnCheckSaldoAteHojeActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void cmbFiltroDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroDespesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFiltroDespesaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLancamento;
    private javax.swing.JButton btnCheckSaldoAteHoje;
    private javax.swing.JButton btnChegarSaldoAteHoje;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnRemoveLancamento;
    private javax.swing.JComboBox<TipoDespesa> cmbFiltroDespesa;
    private javax.swing.JComboBox<String> cmbFiltroOrigem;
    private javax.swing.JComboBox<TipoReceita> cmbFiltroReceita;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
