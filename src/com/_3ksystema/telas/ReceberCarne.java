/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.telas;

import com._3ksystema.classes.FormataData;
import com._3ksystema.funcoes.FuncaoCarne;
import com._3ksystema.funcoes.FuncoesCliente;
import com._3ksystema.funcoes.FuncoesVenda;
import com._3ksystema.modelos.Carne;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Venda;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class ReceberCarne extends javax.swing.JInternalFrame {

    Carne carne = new Carne();
    FuncaoCarne fcn = new FuncaoCarne();
    Cliente cliente = new Cliente();
    FuncoesCliente fc = new FuncoesCliente();
    Venda venda = new Venda();
    FuncoesVenda fv = new FuncoesVenda();
    FormataData fd = new FormataData();
    DecimalFormat df = new DecimalFormat("#,###.00");
    
    /**
     * Creates new form ReceberCarne
     */
    public ReceberCarne() {
        initComponents();
    }

    private void buscarCarne() throws ClassNotFoundException{
        int codigoCarne = Integer.parseInt(txtPesquisaCarne.getText());
        carne = fcn.pesquisaCarnePagamento(codigoCarne);
        //System.out.println(carne.isParcelaPaga());
        if (carne.isParcelaPaga()) {
            JOptionPane.showMessageDialog(null, "A parcela pesquisada ja foi paga na data: " + fd.dataBr(carne.getDataPagamento()), "Carnê ja pago", JOptionPane.INFORMATION_MESSAGE);
        } else {
            cliente = fc.pesquisaClienteCodigo(carne.getIdCliente());
        lblNome.setText(cliente.getNome_Cliente());
        lblVencimento.setText(fd.dataBr(carne.getDataVencimento()));
        lblValor.setText(String.valueOf(df.format(carne.getValorParcela())));
        }
        
    }
    
    private void pagarCarne() throws ClassNotFoundException{
        int codigoCarne = Integer.parseInt(txtPesquisaCarne.getText());
        fcn.receberCarne(codigoCarne, fd.dataSQL());
        limparCampos();
    }
    
    private void sairTela(){
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair da tela de recebimento de carnê?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }
    
    private void limparCampos(){
        txtPesquisaCarne.setText("");
        lblNome.setText("");
        lblVencimento.setText("");
        lblValor.setText("");
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
        txtPesquisaCarne = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblVencimento = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Recebimento de parcelas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código do Carnê:");

        txtPesquisaCarne.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Recebimento de Carnês");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nome:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Vencimento:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Valor:");

        lblVencimento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVencimento.setPreferredSize(new java.awt.Dimension(0, 17));

        lblNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNome.setPreferredSize(new java.awt.Dimension(0, 17));

        lblValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValor.setPreferredSize(new java.awt.Dimension(0, 17));

        btnPagar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.setPreferredSize(new java.awt.Dimension(89, 25));
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Sair");
        jButton3.setPreferredSize(new java.awt.Dimension(89, 25));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisaCarne, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPesquisaCarne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(lblVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            // TODO add your handling code here:
            buscarCarne();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceberCarne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        try {
            // TODO add your handling code here:
            pagarCarne();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceberCarne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        sairTela();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblVencimento;
    private javax.swing.JTextField txtPesquisaCarne;
    // End of variables declaration//GEN-END:variables
}
