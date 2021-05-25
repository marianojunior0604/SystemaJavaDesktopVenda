/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.telas;

import com._3ksystema.classes.FormataData;
import com._3ksystema.funcoes.FuncoesCaixa;
import com._3ksystema.funcoes.FuncoesEmpresa;
import com._3ksystema.funcoes.FuncoesProdutoVenda;
import com._3ksystema.funcoes.FuncoesVenda;
import com._3ksystema.modelos.Caixa;
import com._3ksystema.modelos.Empresa;
import com._3ksystema.modelos.ProdutosVenda;
import com._3ksystema.modelos.Venda;
import com._3ksystema.relatorios.GerarNotaVenda;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class TelaPrincipal extends javax.swing.JFrame {

    public boolean ativaCC = true;
    public String perfil;
    FuncoesCaixa fcx = new FuncoesCaixa();
    Caixa cx = new Caixa();
    FormataData fd = new FormataData();
    Empresa empresa = new Empresa();
    FuncoesEmpresa fe = new FuncoesEmpresa();
    Venda venda = new Venda();
    FuncoesVenda fv = new FuncoesVenda();

    /**
     * Creates new form TelaPrincipal
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public TelaPrincipal() throws ClassNotFoundException, SQLException {
        initComponents();
        //btnVenda.setEnabled(false);
        //jMenuItem2.setEnabled(false);
        //jMenuItem3.setEnabled(false);
        //jMenuItem7.setEnabled(false);
        //verificaUltimoCaixa();
        venda = fv.buscaVendaId(2);
        empresa = fe.pesquisaEmpresa();
        btnVendaRapida.setVisible(true);
        this.setTitle("Tela Principal - " + empresa.getNomeEmpresa());
    }

    private void cadastroClientes() throws ClassNotFoundException {
        CadastroDeClientes cc = new CadastroDeClientes();
        cc.setVisible(true);
        desktopPainel.add(cc);
    }

    private void relatorioVendaPeriodo() {
        RelatorioVendasPeriodo rvp = new RelatorioVendasPeriodo();
        rvp.setVisible(true);
        desktopPainel.add(rvp);
    }

    private void relatorioClientes() throws ClassNotFoundException {
        HistoricoComprasCliente hcc = new HistoricoComprasCliente();
        hcc.setVisible(true);
        desktopPainel.add(hcc);
    }

    private void cadastroProdutos() throws ClassNotFoundException {
        TelaCadastroProdutos tcp = new TelaCadastroProdutos();
        tcp.setVisible(true);
        desktopPainel.add(tcp);
    }

    private void abrirVenda() throws ClassNotFoundException, SQLException {
        TelaVendas tv = new TelaVendas();
        tv.setVisible(true);
        desktopPainel.add(tv);
    }

    private void cadastroCaixa() throws ClassNotFoundException, SQLException {
        TelaAbreEFechaCaixa faefc = new TelaAbreEFechaCaixa();
        faefc.setVisible(true);
        desktopPainel.add(faefc);
    }

    private void cadastraEntrada() {
        TelaEntradaCaixa tec = new TelaEntradaCaixa();
        tec.setVisible(true);
        desktopPainel.add(tec);
    }

    private void cadastraSaida() {
        TelaSaidaCaixa tsc = new TelaSaidaCaixa();
        tsc.setVisible(true);
        desktopPainel.add(tsc);
    }

    private void receberCarne() {
        ReceberCarne rc = new ReceberCarne();
        rc.setVisible(true);
        desktopPainel.add(rc);
    }

    private void vendaRapida(){
        TelaVendasRapidas tvr = new TelaVendasRapidas();
        tvr.setVisible(true);
        desktopPainel.add(tvr);
    }
    
    private void listaClientesAtrasados() throws ClassNotFoundException, SQLException {
        ListaClienteAtrasados lca = new ListaClienteAtrasados();
        lca.setVisible(true);
        desktopPainel.add(lca);
    }

    private void abrirCadastroEmpresa() throws ClassNotFoundException, SQLException {
        TelaInformacoesEmpresa tie = new TelaInformacoesEmpresa();
        tie.setVisible(true);
        desktopPainel.add(tie);
    }

    private void verificaUltimoCaixa() throws ClassNotFoundException, SQLException {
        cx = fcx.pegaUltimoCaixa();
        String dataHoje = fd.dataSQL();
        //System.out.println(dataHoje + " " + cx.getDataCaixa());
        if (dataHoje.equals(cx.getDataCaixa()) || cx.getDataCaixa().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Caixa de hoje ja Aberto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            btnVenda.setEnabled(true);
            jMenuItem2.setEnabled(true);
            jMenuItem3.setEnabled(true);
            jMenuItem7.setEnabled(true);
        } else {
            int abrirCaixa = JOptionPane.showConfirmDialog(null, "O caixa do dia ainda não foi aberto,\nDeseja abrir agora?", "Alerta", JOptionPane.YES_NO_OPTION);
            if (abrirCaixa == JOptionPane.YES_OPTION) {
                cadastroCaixa();
            }
        }
    }

    private void abreDeletaVenda() {
        DeletaVenda dv = new DeletaVenda();
        dv.setVisible(true);
        desktopPainel.add(dv);
    }

    private void backupDB() throws ClassNotFoundException, SQLException {
        TelaFazBackup taefc = new TelaFazBackup();
        taefc.setVisible(true);
        desktopPainel.add(taefc);
    }

    private void mostraInformacoes() {
        FormInformacoes fi = new FormInformacoes();
        fi.setVisible(true);
        desktopPainel.add(fi);
    }

    private void reimprimeCarnes() {
        ReimprimiBoletos rb = new ReimprimiBoletos();
        rb.setVisible(true);
        desktopPainel.add(rb);
    }

    private void cadastrarUsuario() {
        TelaCadastraUsuarios tcu = new TelaCadastraUsuarios();
        tcu.setVisible(true);
        desktopPainel.add(tcu);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCadastroClientes = new javax.swing.JButton();
        btnCadastraProduto = new javax.swing.JButton();
        btnVenda = new javax.swing.JButton();
        btnVendaRapida = new javax.swing.JButton();
        desktopPainel = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal - Sistema Crediario São Jorge");

        btnCadastroClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/_3ksystema/icones/cliente.png"))); // NOI18N
        btnCadastroClientes.setToolTipText("Cadastro de Clientes");
        btnCadastroClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCadastroClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroClientesActionPerformed(evt);
            }
        });

        btnCadastraProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/_3ksystema/icones/produtos.png"))); // NOI18N
        btnCadastraProduto.setToolTipText("Cadastrar Produtos");
        btnCadastraProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastraProdutoActionPerformed(evt);
            }
        });

        btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/_3ksystema/icones/venda.png"))); // NOI18N
        btnVenda.setToolTipText("Vendas Grandes");
        btnVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaActionPerformed(evt);
            }
        });

        btnVendaRapida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/_3ksystema/icones/fast.png"))); // NOI18N
        btnVendaRapida.setToolTipText("Vendas Rapidas");
        btnVendaRapida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaRapidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCadastroClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCadastraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnVendaRapida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCadastroClientes)
                .addGap(18, 18, 18)
                .addComponent(btnCadastraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVendaRapida, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout desktopPainelLayout = new javax.swing.GroupLayout(desktopPainel);
        desktopPainel.setLayout(desktopPainelLayout);
        desktopPainelLayout.setHorizontalGroup(
            desktopPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        desktopPainelLayout.setVerticalGroup(
            desktopPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("Cadastros");

        jMenuItem10.setText("Cadastrar Empresa");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem13.setText("Cadastrar Usuarios");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Caixa");

        jMenuItem1.setText("Abrir e Fechar Caixa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Entrada de dinheiro");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Saida de dinheiro");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem7.setText("Receber Carnê");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Vendas");

        jMenuItem14.setText("Excluir Venda");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        jMenu3.setText("Funções");

        jMenuItem4.setText("Fazer Backup");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Restaurar Banco de Dados");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Relatórios");

        jMenuItem8.setText("Parcelas Atrasadas");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem9.setText("Relatório de Clientes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem11.setText("Reimpressão de Carnês");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuItem12.setText("Relatório de Vendas");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuBar1.add(jMenu6);

        jMenu4.setText("Sobre");

        jMenuItem6.setText("Informações");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desktopPainel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(desktopPainel))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1016, 729));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastroClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroClientesActionPerformed
        try {
            // Botão Cadastrar Clientes
            cadastroClientes();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastroClientesActionPerformed

    private void btnCadastraProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastraProdutoActionPerformed
        try {
            // TODO add your handling code here:
            cadastroProdutos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastraProdutoActionPerformed

    private void btnVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaActionPerformed
        try {
            // TODO add your handling code here:
            abrirVenda();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVendaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            // TODO add your handling code here:
            cadastroCaixa();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        cadastraEntrada();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        mostraInformacoes();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        receberCarne();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            try {
                // TODO add your handling code here:
                listaClientesAtrasados();
            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        Venda venda = new Venda();
        FuncoesVenda fv = new FuncoesVenda();
        FuncoesProdutoVenda fpv = new FuncoesProdutoVenda();
        ArrayList<ProdutosVenda> produtosVenda = new ArrayList();
        try {
            venda = fv.buscaVendaId(47);
            produtosVenda = fpv.pesquisaProdutosVenda(47);
            GerarNotaVenda gnv = new GerarNotaVenda();
            gnv.gerarNotavenda(venda, produtosVenda);
            System.out.println(venda.getDataVenda());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInformacoesSoftware.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | DocumentException | IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            // TODO add your handling code here:
            relatorioClientes();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        try {
            // TODO add your handling code here:
            abrirCadastroEmpresa();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        reimprimeCarnes();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        cadastraSaida();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        relatorioVendaPeriodo();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        cadastrarUsuario();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            // TODO add your handling code here:
            backupDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        abreDeletaVenda();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void btnVendaRapidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaRapidaActionPerformed
        // TODO add your handling code here:
        vendaRapida();
    }//GEN-LAST:event_btnVendaRapidaActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaPrincipal().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCadastraProduto;
    public javax.swing.JButton btnCadastroClientes;
    public javax.swing.JButton btnVenda;
    public javax.swing.JButton btnVendaRapida;
    public static javax.swing.JDesktopPane desktopPainel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
