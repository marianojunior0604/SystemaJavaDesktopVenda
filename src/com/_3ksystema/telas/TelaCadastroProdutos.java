/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.telas;

import com._3ksystema.classes.FormataNumeros;
import com._3ksystema.funcoes.FuncoesProduto;
import com._3ksystema.modelos.Produto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Mariano Junior
 */
public class TelaCadastroProdutos extends javax.swing.JInternalFrame {

    Produto produto = new Produto();
    FuncoesProduto fp = new FuncoesProduto();
    FormataNumeros fn = new FormataNumeros();
    //ArrayList<Produto> produtos = new ArrayList();

    /**
     * Creates new form TelaCadastroProdutos
     */
    public TelaCadastroProdutos() throws ClassNotFoundException {
        initComponents();
        preencherTabela();
        btnEditaProduto.setEnabled(false);
        btnExcluiProduto.setEnabled(false);
        txtLucroProduto.setText("0,0");
        txtLucroProduto.setEditable(false);
    }

    private void sairTelaCadProduto() {
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair da tela de cadastro de produtos?", "Sair", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private void preencherTabela() throws ClassNotFoundException {
        tblProdutos.setModel(DbUtils.resultSetToTableModel(fp.listaProdutos()));
    }

    private void cadastraProduto() throws ClassNotFoundException {
        if (txtNomePrduto.getText().isEmpty() && txtValorVendaProduto.getText().isEmpty() && txtQtdProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha ao menos o nome do produto Quantidade em estoque\n"
                    + " e o preço para poder concluir o cadastro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String valorCompra = txtValorCompraProduto.getText();
            String valorVenda = txtValorVendaProduto.getText();
            String valorPerce = txtLucroProduto.getText();
            String valorCConvertido = fn.ConverterFloat(valorCompra);
            String valorVConvertido = fn.ConverterFloat(valorVenda);
            String valorPConvertido = fn.ConverterFloat(valorPerce);
            double vC = Double.parseDouble(valorCConvertido);
            double vV = Double.parseDouble(valorVConvertido);
            double vP = Double.parseDouble(valorPConvertido);
            produto.setNomeProduto(txtNomePrduto.getText());
            produto.setMarcaProtudo(txtMarcaProduto.getText());
            produto.setModeloProtudo(txtModeloProduto.getText());
            produto.setCaracteristicaProduto(txtDescProduto.getText());
            produto.setQtdEstoqueProduto(Integer.parseInt(txtQtdProduto.getText()));
            produto.setValorCompraProduto(vC);
            produto.setValorVendaProduto(vV);
            produto.setPercentualLucro(vP);
            /*System.out.println("O nome do Prouto: " + produto.getNomeProduto()
            + "\nA marca do Produto: " + produto.getMarcaProtudo()
            + "\nO modelo do Produto: " + produto.getModeloProtudo()
            + "\nA descrição do Produto: " + produto.getCaracteristicaProduto()
            + "\nO valor de Compra do Produto: " + produto.getValorCompraProduto()
            + "\nO valor de Venda do Produto: " + produto.getValorVendaProduto()
            + "\nA quantidade em estoque: " + produto.getQtdEstoqueProduto()
            + "\nO lucro Percentural é: " + produto.getPercentualLucro());*/
            fp.salvaProduto(produto);
            limpaCampos();
        }
        preencherTabela();
    }

    private void excluiProduto() throws ClassNotFoundException {
        fp.excluiProduto(produto.getIdProduto());
        limpaCampos();
        btnEditaProduto.setEnabled(false);
        btnExcluiProduto.setEnabled(false);
        btnSalvarProduto.setEnabled(true); 
        preencherTabela();
    }

    private void editaProduto() throws ClassNotFoundException {
        if (txtNomePrduto.getText().isEmpty() && txtValorVendaProduto.getText().isEmpty() && txtQtdProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha ao menos o nome do produto Quantidade em estoque\n"
                    + " e o preço para poder concluir o cadastro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            produto.setNomeProduto(txtNomePrduto.getText());
            produto.setMarcaProtudo(txtMarcaProduto.getText());
            produto.setModeloProtudo(txtModeloProduto.getText());
            produto.setCaracteristicaProduto(txtDescProduto.getText());
            produto.setQtdEstoqueProduto(Integer.parseInt(txtQtdProduto.getText()));
            produto.setValorCompraProduto(Double.parseDouble(fn.ConverterFloat(txtValorCompraProduto.getText())));
            produto.setValorVendaProduto(Double.parseDouble(fn.ConverterFloat(txtValorVendaProduto.getText())));
            produto.setPercentualLucro(Double.parseDouble(fn.ConverterFloat(txtLucroProduto.getText())));
            fp.alteraProduto(produto);
            btnEditaProduto.setEnabled(false);
            btnExcluiProduto.setEnabled(false);
            btnSalvarProduto.setEnabled(true);
            limpaCampos();
        }
        preencherTabela();
    }

    private void limpaCampos() {
        txtDescProduto.setText("");
        txtNomePrduto.setText("");
        txtLucroProduto.setText("");
        txtMarcaProduto.setText("");
        txtPesquisaProduto.setText("");
        txtQtdProduto.setText("");
        txtValorCompraProduto.setText("");
        txtValorVendaProduto.setText("");
        txtModeloProduto.setText("");
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
        txtPesquisaProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        btnExcluiProduto = new javax.swing.JButton();
        btnEditaProduto = new javax.swing.JButton();
        btnSalvarProduto = new javax.swing.JButton();
        txtNomePrduto = new javax.swing.JTextField();
        txtMarcaProduto = new javax.swing.JTextField();
        txtModeloProduto = new javax.swing.JTextField();
        txtDescProduto = new javax.swing.JTextField();
        txtQtdProduto = new javax.swing.JTextField();
        txtLucroProduto = new javax.swing.JTextField();
        txtValorCompraProduto = new javax.swing.JFormattedTextField();
        txtValorVendaProduto = new javax.swing.JFormattedTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Produtos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Pesquisa");

        txtPesquisaProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisaProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisaProdutoKeyPressed(evt);
            }
        });

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nome:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Marca:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Modelo:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Desc.:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Qtd.:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("V. Compra:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("V. Venda:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Lucro %:");

        btnSair.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSair.setText("Sair");
        btnSair.setPreferredSize(new java.awt.Dimension(110, 30));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnExcluiProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluiProduto.setText("Excluir");
        btnExcluiProduto.setPreferredSize(new java.awt.Dimension(110, 30));
        btnExcluiProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluiProdutoActionPerformed(evt);
            }
        });

        btnEditaProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditaProduto.setText("Editar");
        btnEditaProduto.setPreferredSize(new java.awt.Dimension(110, 30));
        btnEditaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditaProdutoActionPerformed(evt);
            }
        });

        btnSalvarProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvarProduto.setText("Salvar");
        btnSalvarProduto.setPreferredSize(new java.awt.Dimension(110, 30));
        btnSalvarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarProdutoActionPerformed(evt);
            }
        });

        txtNomePrduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtMarcaProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtModeloProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDescProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtQtdProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtLucroProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtValorCompraProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtValorCompraProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtValorVendaProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtValorVendaProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValorVendaProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValorVendaProdutoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisaProduto))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluiProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtModeloProduto))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtQtdProduto))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNomePrduto, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtValorVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMarcaProduto))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtValorCompraProduto))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLucroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDescProduto)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtNomePrduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarcaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtModeloProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtQtdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorCompraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtLucroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtValorVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluiProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        sairTelaCadProduto();
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtPesquisaProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaProdutoKeyPressed
        // TODO add your handling code here:
        if (txtPesquisaProduto.getText().isEmpty()) {
            try {
                preencherTabela();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaCadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                tblProdutos.setModel(DbUtils.resultSetToTableModel(fp.buscaAvancada(txtPesquisaProduto.getText())));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaCadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtPesquisaProdutoKeyPressed

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        int mudarQTD = JOptionPane.showConfirmDialog(null, "Deseja mudar apenas a quantidade em estoque?", "Aviso", JOptionPane.YES_NO_OPTION);
        try {
            produto = fp.pesquisaProduto(tblProdutos.getModel().getValueAt(tblProdutos.getSelectedRow(), 0).toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mudarQTD == JOptionPane.YES_OPTION) {
            String qtdAumentar = JOptionPane.showInputDialog(null, "Digite quantos itens deseja adicionar em estoque");
            int qtdAdicionar = Integer.parseInt(qtdAumentar);
            try {
                fp.aumentaQuantidade(qtdAdicionar, produto);
                preencherTabela();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaCadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // TODO add your handling code here:
            //produto = fp.pesquisaProduto(tblProdutos.getModel().getValueAt(tblProdutos.getSelectedRow(), 0).toString());
            txtDescProduto.setText(produto.getCaracteristicaProduto());
            txtNomePrduto.setText(produto.getNomeProduto());
            txtLucroProduto.setText(fn.FloatConvertido(Double.toString(produto.getPercentualLucro())));
            txtMarcaProduto.setText(produto.getMarcaProtudo());
            txtPesquisaProduto.setText("");
            txtQtdProduto.setText(Integer.toString(produto.getQtdEstoqueProduto()));
            txtValorCompraProduto.setText(fn.FloatConvertido(Double.toString(produto.getValorCompraProduto())));
            txtValorVendaProduto.setText(fn.FloatConvertido(Double.toString(produto.getValorVendaProduto())));
            txtModeloProduto.setText(produto.getModeloProtudo());
            btnSalvarProduto.setEnabled(false);
            btnEditaProduto.setEnabled(true);
            btnExcluiProduto.setEnabled(true);
        }
    }//GEN-LAST:event_tblProdutosMouseClicked

    private void btnSalvarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProdutoActionPerformed
        try {
            // TODO add your handling code here:
            cadastraProduto();
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarProdutoActionPerformed

    private void btnEditaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditaProdutoActionPerformed
        try {
            // TODO add your handling code here:
            editaProduto();
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditaProdutoActionPerformed

    private void btnExcluiProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluiProdutoActionPerformed
        try {
            // TODO add your handling code here:
            excluiProduto();
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluiProdutoActionPerformed

    private void txtValorVendaProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorVendaProdutoFocusLost
        // TODO add your handling code here:
        double valorVenda = Double.parseDouble(fn.ConverterFloat(txtValorVendaProduto.getText()));
        double valorCompr = Double.parseDouble(fn.ConverterFloat(txtValorCompraProduto.getText()));
        //System.out.println(valorVenda + " " + valorCompr);
        double valorPerce = ((valorVenda / valorCompr) - 1) * 100;
        //System.out.println(valorPerce);
        String percento = String.format("%.2f", valorPerce);
        txtLucroProduto.setText(percento);
    }//GEN-LAST:event_txtValorVendaProdutoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditaProduto;
    private javax.swing.JButton btnExcluiProduto;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvarProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtDescProduto;
    private javax.swing.JTextField txtLucroProduto;
    private javax.swing.JTextField txtMarcaProduto;
    private javax.swing.JTextField txtModeloProduto;
    private javax.swing.JTextField txtNomePrduto;
    private javax.swing.JTextField txtPesquisaProduto;
    private javax.swing.JTextField txtQtdProduto;
    private javax.swing.JFormattedTextField txtValorCompraProduto;
    private javax.swing.JFormattedTextField txtValorVendaProduto;
    // End of variables declaration//GEN-END:variables
}
