/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.telas;

import com._3ksystema.classes.FormataData;
import com._3ksystema.classes.FormataNumeros;
import com._3ksystema.classes.ProdutosVendaTableModel;
import com._3ksystema.funcoes.FuncaoCarne;
import com._3ksystema.funcoes.FuncoesCliente;
import com._3ksystema.funcoes.FuncoesEmpresa;
import com._3ksystema.funcoes.FuncoesEntrada;
import com._3ksystema.funcoes.FuncoesParcelas;
import com._3ksystema.funcoes.FuncoesProduto;
import com._3ksystema.funcoes.FuncoesProdutoVenda;
import com._3ksystema.funcoes.FuncoesVenda;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Entradas;
import com._3ksystema.modelos.Parcelas;
import com._3ksystema.modelos.Produto;
import com._3ksystema.modelos.ProdutoListaVenda;
import com._3ksystema.modelos.ProdutosVenda;
import com._3ksystema.modelos.Venda;
import com._3ksystema.relatorios.GerarCarneRetrato;
import com._3ksystema.relatorios.GerarContrato;
import com._3ksystema.relatorios.GerarOrcamento;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 * @author Mariano Junior
 */
public class TelaVendas extends javax.swing.JInternalFrame {

    private FuncoesCliente fc = new FuncoesCliente();
    private FuncoesProduto fp = new FuncoesProduto();
    private FormataData fd = new FormataData();
    private FormataNumeros fn = new FormataNumeros();
    private FuncoesVenda fv = new FuncoesVenda();
    private FuncoesEntrada fe = new FuncoesEntrada();
    private FuncoesProdutoVenda fpv = new FuncoesProdutoVenda();
    private FuncaoCarne fcn = new FuncaoCarne();
    private GerarCarneRetrato gc = new GerarCarneRetrato();
    private Cliente cliente = new Cliente();
    private Produto produto = new Produto();
    private ProdutosVendaTableModel pvtm = new ProdutosVendaTableModel();
    private ArrayList<ProdutosVenda> produtosVenda = new ArrayList();
    private ProdutosVenda produtoVenda = new ProdutosVenda();
    private ArrayList<ProdutoListaVenda> prodVenda = new ArrayList();
    private Venda venda = new Venda();
    private Entradas entradaCaixa = new Entradas();
    private GerarContrato gct = new GerarContrato();
    private FuncoesEmpresa fep = new FuncoesEmpresa();
    private GerarOrcamento go = new GerarOrcamento();
    private Parcelas parcela = new Parcelas();
    private FuncoesParcelas fPar = new FuncoesParcelas();
    private String bloqueiaLetras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\"\\|!@#$%¨&*()_-+=§ª[{`´^~}]:;Çç";

    /**
     * Creates new form TelaVendas
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public TelaVendas() throws ClassNotFoundException, SQLException {
        initComponents();
        listaClientes();
        listaProdutosLoja();
        listaProdutosVenda();
        //Empresa empresa = fep.pesquisaEmpresa();
        txtDataVenda.setText(fd.dataAtualBr());
        txtDataVenda.setEditable(false);
        txtDataPagamento.setText(fd.dataAtualBr());
        rbtAvista.setSelected(true);
        txtQtdParcelas.setText("1");
        txtQtdParcelas.setEnabled(false);
    }

    private void listaClientes() throws ClassNotFoundException {
        tblClienteVenda.setModel(DbUtils.resultSetToTableModel(fc.listaNomesClientes()));
    }

    private void listaClientesPesquisa(String nome) throws ClassNotFoundException {
        tblClienteVenda.setModel(DbUtils.resultSetToTableModel(fc.pesquisaClienteVenda(nome)));
    }

    private void listaProdutosLoja() throws ClassNotFoundException {
        tblProdutosLoja.setModel(DbUtils.resultSetToTableModel(fp.listaProdutos()));
    }

    private void listaProdutoPesquisa(String nomeProduto) throws ClassNotFoundException {
        tblProdutosLoja.setModel(DbUtils.resultSetToTableModel(fp.buscaAvancada(nomeProduto)));
    }

    private Cliente selecionaClienteCompra() throws ClassNotFoundException {
        cliente = fc.pesquisaCliente(tblClienteVenda.getModel().getValueAt(tblClienteVenda.getSelectedRow(), 0).toString());
        return cliente;
    }

    private int selecionaProdutoVenda() {
        return tblProdutosVenda.getModel().getRowCount();
    }

    private void geraOrcamento() throws ClassNotFoundException, SQLException, FileNotFoundException, DocumentException, IOException {
        try {
            go.gerarOrcamento(cliente, fep.pesquisaEmpresa(), venda, produtosVenda);
        } catch (IOException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int bloqueiaLetrasFunc(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
        int qtdDesejada = 0;
        if (bloqueiaLetras.contains(evt.getKeyChar() + "")) {
            qtdDesejada = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade desejada"));
            evt.consume();
        }
        return qtdDesejada;
    } 
    
    private void adcionaProdutoVenda() throws ClassNotFoundException {
        ProdutosVenda produtoVenda = new ProdutosVenda();
        ProdutoListaVenda plv = new ProdutoListaVenda();
        String qtdProd = JOptionPane.showInputDialog(null, "Digite a quantidade que o cliente ira comprar", "Quantidade da compra?", JOptionPane.PLAIN_MESSAGE);
        int qtdProdutosVendido = 0;
        System.out.println(qtdProd);
        if (qtdProd != null) {
            qtdProdutosVendido = Integer.parseInt(qtdProd);

            int codigoProduto;
            produto = fp.pesquisaProduto(tblProdutosLoja.getModel().getValueAt(tblProdutosLoja.getSelectedRow(), 0).toString());
            codigoProduto = produto.getIdProduto();
            int qtdEstoque = produto.getQtdEstoqueProduto();
            if (qtdProdutosVendido <= qtdEstoque) {
                produtoVenda.setIdproduto(codigoProduto);
                produtoVenda.setQtdProdutoVenda(qtdProdutosVendido);
                produtosVenda.add(produtoVenda);
                plv.setNomeProduto(produto.getNomeProduto());
                plv.setPrecoProduto(produto.getValorVendaProduto());
                plv.setQtdProdutoComprado(qtdProdutosVendido);
                plv.setPrecoTotal(produto.getValorVendaProduto(), qtdProdutosVendido);
                prodVenda.add(plv);
                pvtm.addProduto(plv);
            } else {
                JOptionPane.showMessageDialog(null, "A quantidade digitada é superior a quantidade em estoque", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void produzirCarnes(Venda venda) throws ClassNotFoundException, DocumentException, IOException, FileNotFoundException, SQLException {
        gc.criaCarne(venda);
    }

    private void listaProdutosVenda() {
        tblProdutosVenda.setModel(pvtm);
    }

    private Venda montaVenda(Cliente clien, ArrayList<ProdutosVenda> prodVend, double valorEntrada) throws ClassNotFoundException {
        venda.setCliente(clien.getId_Cliente());
        venda.setProdutos(prodVend);
        venda.setDataVenda(fd.formataData(txtDataVenda.getText()));
        venda.setDataPgtoParcela(fd.formataData(txtDataPagamento.getText()));
        venda.setQtdParcelas(Integer.parseInt(txtQtdParcelas.getText()));
        String tpVend = (rbtAvista.isSelected()) ? "AV" : "AP";
        venda.setTipoVenda(tpVend);
        double valorVenda = 0.0;
        for (ProdutosVenda produtosVenda1 : prodVend) {
            Produto produt = fp.pesquisaProdCod(produtosVenda1.getIdproduto());
            double valorProduto = produt.getValorVendaProduto();
            double valorProdVend = valorProduto * produtosVenda1.getQtdProdutoVenda();
            valorVenda = valorVenda + valorProdVend;
        }
        if (tpVend.equals("AV")) {
            int desconto = JOptionPane.showConfirmDialog(null, "O cliente terá algum desconto?", "Desconto?", JOptionPane.YES_NO_OPTION);
            if (desconto == JOptionPane.YES_OPTION) {
                String percento = JOptionPane.showInputDialog(null, "Quantos % de desconto?", "Desconto", JOptionPane.QUESTION_MESSAGE);
                double desc = Double.parseDouble(percento);
                valorVenda = valorVenda - (valorVenda * (desc / 100));
                venda.setValorEntrada(valorVenda);
            } else {
                venda.setValorEntrada(valorEntrada);
            }
        }
        venda.setValorVenda(valorVenda);
        return venda;
    }

    private void sairTelaVenda() {
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair do Sistema de Venda?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private double percentoJuros(int nParcelas) {
        parcela = fPar.buscarPercentual(nParcelas);
        double acrescimo = parcela.getPercento();
        return acrescimo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtFormaPagamento = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDataVenda = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        rbtAvista = new javax.swing.JRadioButton();
        rbtAprazo = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClienteVenda = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtQtdParcelas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDataPagamento = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        txtProdutosPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutosLoja = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProdutosVenda = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnFechaVenda = new javax.swing.JButton();

        setTitle("Cadastro de vendas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tela de Vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente e dados da compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeClienteKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Data da Compra:");

        try {
            txtDataVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataVenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Forma de Pagamento:");

        rbtFormaPagamento.add(rbtAvista);
        rbtAvista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtAvista.setText("À vista");
        rbtAvista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAvistaActionPerformed(evt);
            }
        });

        rbtFormaPagamento.add(rbtAprazo);
        rbtAprazo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtAprazo.setText("A prazo");
        rbtAprazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAprazoActionPerformed(evt);
            }
        });

        tblClienteVenda.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClienteVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteVendaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblClienteVenda);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Qtd. Parcelas:");

        txtQtdParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQtdParcelas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQtdParcelasFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Data Pagamento:");

        try {
            txtDataPagamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataPagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addComponent(txtNomeCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQtdParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtAvista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtAprazo))
                    .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtAvista)
                                .addComponent(rbtAprazo))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtQtdParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos Loja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtProdutosPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProdutosPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProdutosPesquisaKeyPressed(evt);
            }
        });

        tblProdutosLoja.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProdutosLoja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosLojaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutosLoja);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProdutosPesquisa)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtProdutosPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        tblProdutosVenda.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProdutosVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosVendaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProdutosVenda);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Sair");
        jButton1.setPreferredSize(new java.awt.Dimension(110, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("orçamento");
        jButton3.setPreferredSize(new java.awt.Dimension(110, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnFechaVenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFechaVenda.setText("Vender");
        btnFechaVenda.setPreferredSize(new java.awt.Dimension(110, 30));
        btnFechaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFechaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtAprazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAprazoActionPerformed
        // TODO add your handling code here:
        txtQtdParcelas.setText("1");
        txtQtdParcelas.setEnabled(true);
    }//GEN-LAST:event_rbtAprazoActionPerformed

    private void txtNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeClienteKeyPressed
        // TODO add your handling code here:
        if (txtNomeCliente.getText().isEmpty()) {
            try {
                listaClientes();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                listaClientesPesquisa(txtNomeCliente.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtNomeClienteKeyPressed

    private void txtProdutosPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdutosPesquisaKeyPressed
        // TODO add your handling code here:
        if (txtProdutosPesquisa.getText().isEmpty()) {
            try {
                listaProdutosLoja();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                listaProdutoPesquisa(txtProdutosPesquisa.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtProdutosPesquisaKeyPressed

    private void tblClienteVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteVendaMouseClicked
        try {
            // TODO add your handling code here:
            selecionaClienteCompra();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblClienteVendaMouseClicked

    private void tblProdutosLojaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosLojaMouseClicked
        try {
            // TODO add your handling code here:
            adcionaProdutoVenda();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblProdutosLojaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        sairTelaVenda();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFechaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaVendaActionPerformed
        if (cliente.getId_Cliente() != 0) {
            if (produtosVenda.size() != 0) {
                try {
                    venda.setCliente(selecionaClienteCompra().getId_Cliente());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (venda.getCliente() == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione o Cliente que ira fazer a compra", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //Verificação de clientes cadastrados
                    double valorEntrada = 0.0;
                    try {
                        venda = montaVenda(cliente, produtosVenda, valorEntrada);
                        String tipoVenda = (rbtAvista.isSelected()) ? "AV" : "AP";
                        if (tipoVenda.equals("AV")) {
                            fv.cadastraVenda(venda);
                            int idVenda = fv.buscaUltimaVenda();
                            for (ProdutosVenda produtosVenda1 : produtosVenda) {
                                produtosVenda1.setIdVenda(idVenda);
                            }
                            fpv.salvaProdutosVenda(produtosVenda);
                            entradaCaixa.setDataEntrada(fd.formataData(txtDataVenda.getText()));
                            entradaCaixa.setNaturezaEntrada("Venda a vista - " + cliente.getNome_Cliente());
                            entradaCaixa.setValorEntrada(venda.getValorVenda());
                            fe.salvaEntrada(entradaCaixa);
                        } else {
                            int entrada = JOptionPane.showConfirmDialog(null, "O cliente vai dar algum valor de entrada?", "Entrada", JOptionPane.YES_NO_OPTION);
                            if (entrada == JOptionPane.YES_OPTION) {
                                String valor = JOptionPane.showInputDialog(null, "Quanto o cliente ira da de entrada", "Valor da Entrada", JOptionPane.QUESTION_MESSAGE);
                                valorEntrada = Double.parseDouble(fn.ConverterFloat(valor));
                            }
                            double percento = percentoJuros(Integer.parseInt(txtQtdParcelas.getText()));
                            double valorvenda = venda.getValorVenda() + (venda.getValorVenda() * (percento / 100));
                            venda.setValorVenda(valorvenda);
                            fv.cadastraVenda(venda);
                            int idVenda = fv.buscaUltimaVenda();
                            for (ProdutosVenda produtosVenda1 : produtosVenda) {
                                produtosVenda1.setIdVenda(idVenda);
                            }
                            fpv.salvaProdutosVenda(produtosVenda);
                            if (valorEntrada > 0.0) {
                                entradaCaixa.setDataEntrada(fd.formataData(txtDataVenda.getText()));
                                entradaCaixa.setNaturezaEntrada("Entrada da Venda - " + cliente.getNome_Cliente());
                                entradaCaixa.setValorEntrada(valorEntrada);
                                fe.salvaEntrada(entradaCaixa);
                            }
                            venda = fv.buscaVendaId(fv.buscaUltimaVenda());
                            fcn.criarCarnes(venda);
                            try {
                                produzirCarnes(venda);
                            } catch (DocumentException | IOException ex) {
                                Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        for (ProdutosVenda produtosVenda1 : produtosVenda) {
                            fv.diminuiQuantidade(produtosVenda1.getQtdProdutoVenda(), produto);
                        }
                        gct.gerarContrato(cliente, fep.pesquisaEmpresa(), venda);
                        cliente = new Cliente();
                        venda = new Venda();
                        produtosVenda.clear();
                        prodVenda.forEach(_item -> pvtm.removerProduto(0));
                        prodVenda.clear();
                        rbtAvista.isSelected();
                        txtQtdParcelas.setText("1");
                        txtQtdParcelas.setEditable(false);
                    } catch (ClassNotFoundException | SQLException | DocumentException | IOException ex) {
                        Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Produto adicionado na venda");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Cliente selecionado");
        }
    }//GEN-LAST:event_btnFechaVendaActionPerformed

    private void rbtAvistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAvistaActionPerformed
        // TODO add your handling code here:
        txtQtdParcelas.setText("1");
        txtQtdParcelas.setEnabled(false);
    }//GEN-LAST:event_rbtAvistaActionPerformed

    private void txtQtdParcelasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtdParcelasFocusLost
        // TODO add your handling code here:
        if (Integer.parseInt(txtQtdParcelas.getText()) < 1 || Integer.parseInt(txtQtdParcelas.getText()) > 12) {
            JOptionPane.showMessageDialog(null, "O numero de parcelas não é permitido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            txtQtdParcelas.setText("1");
        }
    }//GEN-LAST:event_txtQtdParcelasFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            geraOrcamento();
        } catch (ClassNotFoundException | SQLException | FileNotFoundException | DocumentException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblProdutosVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosVendaMouseClicked
        // TODO add your handling code here:
        produtosVenda.remove(selecionaProdutoVenda() - 1);
        prodVenda.remove(selecionaProdutoVenda() - 1);
        pvtm.removerProduto(selecionaProdutoVenda() - 1);
    }//GEN-LAST:event_tblProdutosVendaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechaVenda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rbtAprazo;
    private javax.swing.JRadioButton rbtAvista;
    private javax.swing.ButtonGroup rbtFormaPagamento;
    private javax.swing.JTable tblClienteVenda;
    private javax.swing.JTable tblProdutosLoja;
    private javax.swing.JTable tblProdutosVenda;
    private javax.swing.JFormattedTextField txtDataPagamento;
    private javax.swing.JFormattedTextField txtDataVenda;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtProdutosPesquisa;
    private javax.swing.JTextField txtQtdParcelas;
    // End of variables declaration//GEN-END:variables
}
