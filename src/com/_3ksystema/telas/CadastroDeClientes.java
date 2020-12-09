/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.telas;

import com._3ksystema.classes.ClienteTableModel;
import com._3ksystema.classes.FormataData;
import com._3ksystema.classes.FormataNumeros;
import com._3ksystema.funcoes.FuncoesCliente;
import com._3ksystema.modelos.Cliente;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
//import net.proteanit.sql.DbUtils;

/**
 *
 * @author Mariano Junior
 */
public class CadastroDeClientes extends javax.swing.JInternalFrame {

    private FuncoesCliente fc = new FuncoesCliente();
    private ClienteTableModel ctm = new ClienteTableModel();
    private Cliente cliente = new Cliente();
    private FormataData fd = new FormataData();
    private FormataNumeros fn = new FormataNumeros();
    private ArrayList<Cliente> clientes = new ArrayList();
    //private final int idPesquisado = 0;

    //TelaPrincipal tp = null;
    /**
     * Creates new form CadastroDeClientes
     *
     * @throws java.lang.ClassNotFoundException
     */
    public CadastroDeClientes() throws ClassNotFoundException {
        initComponents();
        preencherTabela();
        jcbEstadoCliente.setSelectedIndex(17);
        jcbEstadoEmissorRGCliente.setSelectedIndex(17);
        txtNomeConjujeCliente.setEditable(false);
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        //listaClientes();
        /*txtNomeCliente.setText("Mariano");
        txtApelidoCliente.setText("Junior");
        txtFoneCliente.setText("86999732001");
        txtDataNascimentoCliente.setText("06041985");
        txtNomeMaeCliente.setText("Maria");
        txtNomePaiCliente.setText("Francisco");
        txtRuaCasaCliente.setText("Rua A");
        txtNumeroCasaCliente.setText("07");
        txtBairroCliente.setText("NS Fatima");
        txtCidadeCliente.setText("Cocal");
        txtRGCliente.setText("2699819");
        txtOrgEmissorRGCliente.setText("SSP");
        txtCPFCliente.setText("01117775348");
        txtRendaCliente.setText("2500");
        txtProfissãoCliente.setText("Programador");
        txtLocalTrabalhoCliente.setText("Autonomo");
        txtNomeConjujeCliente.setText("Rosana");*/
    }

    private void preencherTabela() throws ClassNotFoundException {
        tblClientes.setModel(DbUtils.resultSetToTableModel(fc.listaClientes()));
    }

    private void limpaCampos() {
        txtNomeCliente.setText("");
        txtApelidoCliente.setText("");
        txtFoneCliente.setText("");
        txtDataNascimentoCliente.setText("");
        txtNomeMaeCliente.setText("");
        txtNomePaiCliente.setText("");
        txtRuaCasaCliente.setText("");
        txtNumeroCasaCliente.setText("");
        txtBairroCliente.setText("");
        txtCidadeCliente.setText("");
        txtRGCliente.setText("");
        txtOrgEmissorRGCliente.setText("");
        txtCPFCliente.setText("");
        txtRendaCliente.setText("");
        txtProfissãoCliente.setText("");
        txtLocalTrabalhoCliente.setText("");
        txtNomeConjujeCliente.setText("");
    }

    private Cliente pegaCampos() {
        String estadoCivil = ("Solteiro".equals(jcbEstadoCivilCliente.getSelectedItem().toString())) ? "solt" : "casa";
        String rendaS = fn.ConverterFloat(txtRendaCliente.getText());
        float renda = Float.parseFloat(rendaS);
        cliente.setNome_Cliente(txtNomeCliente.getText());
        cliente.setApelido_Cliente(txtApelidoCliente.getText());
        cliente.setFone_Cliente(txtFoneCliente.getText());
        cliente.setData_Nascimento_Cliente(fd.formataData(txtDataNascimentoCliente.getText()));
        cliente.setEstado_Civil_Cliente(estadoCivil);
        cliente.setNome_Mae_Cliente(txtNomeMaeCliente.getText());
        cliente.setNome_Pai_Cliente(txtNomePaiCliente.getText());
        cliente.setRua_Casa_Cliente(txtRuaCasaCliente.getText());
        cliente.setNumero_Casa_Cliente(txtNumeroCasaCliente.getText());
        cliente.setBairro_Casa_Cliente(txtBairroCliente.getText());
        cliente.setCidade_Casa_Cliente(txtCidadeCliente.getText());
        cliente.setEstado_Casa_Cliente(jcbEstadoCliente.getSelectedItem().toString());
        cliente.setRg_Cliente(txtRGCliente.getText());
        cliente.setOrg_Emissor_RG_Cliente(txtOrgEmissorRGCliente.getText());
        cliente.setEstado_Emissor_RG_Cliente(jcbEstadoEmissorRGCliente.getSelectedItem().toString());
        cliente.setCpf_Cliente(txtCPFCliente.getText());
        cliente.setRenda_Cliente(renda);
        cliente.setNome_Conjuje_Cliente(txtNomeConjujeCliente.getText());
        cliente.setProfissao_Cliente(txtProfissãoCliente.getText());
        cliente.setLocal_Trabalho_Cliente(txtLocalTrabalhoCliente.getText());
        return cliente;
    }

    private void atualizaCliente() throws ClassNotFoundException {
        if (txtNomeCliente.getText().isEmpty() && txtCPFCliente.getText().isEmpty()
                && txtFoneCliente.getText().isEmpty() && txtRuaCasaCliente.getText().isEmpty()
                && txtNumeroCasaCliente.getText().isEmpty() && txtBairroCliente.getText().isEmpty()
                && txtCidadeCliente.getText().isEmpty() && txtApelidoCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios", "Aviso", JOptionPane.PLAIN_MESSAGE);
        } else {
            cliente = pegaCampos();
            fc.atualizaCliente(cliente);
            limpaCampos();
        }
    }

    private void cadastrarCliente() throws ClassNotFoundException {
        if (txtNomeCliente.getText().isEmpty() && txtCPFCliente.getText().isEmpty()
                && txtFoneCliente.getText().isEmpty() && txtRuaCasaCliente.getText().isEmpty()
                && txtNumeroCasaCliente.getText().isEmpty() && txtBairroCliente.getText().isEmpty()
                && txtCidadeCliente.getText().isEmpty() && txtApelidoCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios", "Aviso", JOptionPane.PLAIN_MESSAGE);
        } else {
            String estadoCivil = ("Solteiro".equals(jcbEstadoCivilCliente.getSelectedItem().toString())) ? "solt" : "casa";
            float renda;
            if (txtRendaCliente.getText().isEmpty()) {
                renda = (float) 0.0;
            } else {
                String rendaS = fn.ConverterFloat(txtRendaCliente.getText());
                renda = Float.parseFloat(rendaS);
            }
            cliente.setNome_Cliente(txtNomeCliente.getText());
            cliente.setApelido_Cliente(txtApelidoCliente.getText());
            cliente.setFone_Cliente(txtFoneCliente.getText());
            if (txtDataNascimentoCliente.getText().equals("  /  /    ")) {
                cliente.setData_Nascimento_Cliente(fd.dataSQL());
            } else {
                System.out.println(txtDataNascimentoCliente.getText());
                cliente.setData_Nascimento_Cliente(fd.formataData(txtDataNascimentoCliente.getText()));
            }
            cliente.setEstado_Civil_Cliente(estadoCivil);
            cliente.setNome_Mae_Cliente(txtNomeMaeCliente.getText());
            cliente.setNome_Pai_Cliente(txtNomePaiCliente.getText());
            cliente.setRua_Casa_Cliente(txtRuaCasaCliente.getText());
            cliente.setNumero_Casa_Cliente(txtNumeroCasaCliente.getText());
            cliente.setBairro_Casa_Cliente(txtBairroCliente.getText());
            cliente.setCidade_Casa_Cliente(txtCidadeCliente.getText());
            cliente.setEstado_Casa_Cliente(jcbEstadoCliente.getSelectedItem().toString());
            cliente.setRg_Cliente(txtRGCliente.getText());
            cliente.setOrg_Emissor_RG_Cliente(txtOrgEmissorRGCliente.getText());
            cliente.setEstado_Emissor_RG_Cliente(jcbEstadoEmissorRGCliente.getSelectedItem().toString());
            cliente.setCpf_Cliente(txtCPFCliente.getText());
            cliente.setRenda_Cliente(renda);
            cliente.setNome_Conjuje_Cliente(txtNomeConjujeCliente.getText());
            cliente.setProfissao_Cliente(txtProfissãoCliente.getText());
            cliente.setLocal_Trabalho_Cliente(txtLocalTrabalhoCliente.getText());
            fc.cadastraCliente(cliente);
            limpaCampos();
        }
        preencherTabela();
    }

    private void excluiCliente() throws ClassNotFoundException {
        cliente = fc.pesquisaCliente(txtNomeCliente.getText());
        int deleta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse cliente da sua base de dados?", "Excluir Cliente", JOptionPane.YES_NO_OPTION);
        if (deleta == JOptionPane.YES_OPTION) {
            fc.excluirCliente(cliente.getId_Cliente());
            limpaCampos();
            btnExcluir.setEnabled(false);
            btnEditar.setEnabled(false);
            btnSalvar.setEnabled(true);
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
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNomePesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        txtApelidoCliente = new javax.swing.JTextField();
        txtNomeMaeCliente = new javax.swing.JTextField();
        txtNomePaiCliente = new javax.swing.JTextField();
        txtRuaCasaCliente = new javax.swing.JTextField();
        txtNumeroCasaCliente = new javax.swing.JTextField();
        txtBairroCliente = new javax.swing.JTextField();
        txtCidadeCliente = new javax.swing.JTextField();
        txtRGCliente = new javax.swing.JTextField();
        txtNomeConjujeCliente = new javax.swing.JTextField();
        txtProfissãoCliente = new javax.swing.JTextField();
        txtLocalTrabalhoCliente = new javax.swing.JTextField();
        txtDataNascimentoCliente = new javax.swing.JFormattedTextField();
        txtCPFCliente = new javax.swing.JFormattedTextField();
        txtRendaCliente = new javax.swing.JFormattedTextField();
        jcbEstadoCivilCliente = new javax.swing.JComboBox<>();
        jcbEstadoCliente = new javax.swing.JComboBox<>();
        jcbEstadoEmissorRGCliente = new javax.swing.JComboBox<>();
        txtOrgEmissorRGCliente = new javax.swing.JTextField();
        txtFoneCliente = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();

        setTitle("Tela de Cadastro de Clientes");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Clientes");

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditar.setText("Atualizar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Pesquisar:");

        txtNomePesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNomePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomePesquisaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomePesquisaKeyReleased(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pesquisa", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("D. N.:");
        jLabel5.setToolTipText("Data de Nascimento");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("*Apelido:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("*Nome:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Cidade:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Estado:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("*Bairro:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("*Rua:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("*Numero:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Nome da Mãe:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Nome do Pai:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("*Celular:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("R.G.:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Org. Emisso:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Estado:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("*CPF:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Renda:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Estado Civil:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Nome do Conjuge:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Profisão:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Local de Trabalho:");

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtApelidoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNomeMaeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNomePaiCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtRuaCasaCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNumeroCasaCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtBairroCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCidadeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtRGCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNomeConjujeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtProfissãoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtLocalTrabalhoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtDataNascimentoCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimentoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtCPFCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPFCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtRendaCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtRendaCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jcbEstadoCivilCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbEstadoCivilCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro", "Casado", "União estável" }));
        jcbEstadoCivilCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEstadoCivilClienteActionPerformed(evt);
            }
        });

        jcbEstadoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbEstadoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        jcbEstadoEmissorRGCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbEstadoEmissorRGCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        txtOrgEmissorRGCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtFoneCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)##### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFoneCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtApelidoCliente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomeMaeCliente)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomePaiCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomeConjujeCliente))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRuaCasaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumeroCasaCliente))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(10, 10, 10)
                                .addComponent(txtProfissãoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLocalTrabalhoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(txtDataNascimentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbEstadoCivilCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(10, 10, 10)
                                .addComponent(txtCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRendaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(10, 10, 10)
                                .addComponent(txtRGCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOrgEmissorRGCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbEstadoEmissorRGCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(10, 10, 10)
                                .addComponent(txtBairroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtApelidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDataNascimentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(jcbEstadoCivilCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtFoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeMaeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(txtNomePaiCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRuaCasaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(txtNumeroCasaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBairroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jcbEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRGCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOrgEmissorRGCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jcbEstadoEmissorRGCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtRendaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNomeConjujeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel21))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProfissãoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(txtLocalTrabalhoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cadastro", jPanel2);

        jLabel23.setText("Campos com * são obrigatorios no cadastro de clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // Sair do Cadastro de Clientes
        int sair = JOptionPane.showConfirmDialog(null, "Deseja fechar a tela de cadastro de Clientes?", "Aviso", JOptionPane.YES_NO_OPTION, 1);
        if (sair == JOptionPane.YES_OPTION) {
            //tp.ativaCC = true;
            this.dispose();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void jcbEstadoCivilClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEstadoCivilClienteActionPerformed
        // TODO add your handling code here
        txtNomeConjujeCliente.setEditable((jcbEstadoCivilCliente.getSelectedItem() == "Solteiro") ? false : true);
    }//GEN-LAST:event_jcbEstadoCivilClienteActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            // TODO add your handling code here:
            cadastrarCliente();
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroDeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtNomePesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomePesquisaKeyReleased
        // TODO add your handling code here:
        if (txtNomePesquisa.getText().isEmpty()) {
            try {
                preencherTabela();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CadastroDeClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                //ctm.setLinhas(fc.pesquisaAvancada(txtNomePesquisa.getText()));
                tblClientes.setModel(DbUtils.resultSetToTableModel(fc.pesquisaAvancada(txtNomePesquisa.getText())));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CadastroDeClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtNomePesquisaKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        try {
            // TODO add your handling code here:
            //System.out.println(tblClientes.getSelectedRow());
            cliente = fc.pesquisaCliente(tblClientes.getModel().getValueAt(tblClientes.getSelectedRow(), 0).toString());
            //System.out.println(cliente.getNome_Cliente());
            txtNomeCliente.setText(cliente.getNome_Cliente());
            txtApelidoCliente.setText(cliente.getApelido_Cliente());
            txtFoneCliente.setText(cliente.getFone_Cliente());
            String dana = cliente.getData_Nascimento_Cliente();
            String data = fd.dataBr(dana);
            //System.out.println(data);
            txtDataNascimentoCliente.setText(data);
            txtNomeMaeCliente.setText(cliente.getNome_Mae_Cliente());
            txtNomePaiCliente.setText(cliente.getNome_Pai_Cliente());
            txtRuaCasaCliente.setText(cliente.getRua_Casa_Cliente());
            txtNumeroCasaCliente.setText(cliente.getNumero_Casa_Cliente());
            txtBairroCliente.setText(cliente.getBairro_Casa_Cliente());
            txtCidadeCliente.setText(cliente.getCidade_Casa_Cliente());
            txtRGCliente.setText(cliente.getRg_Cliente());
            txtOrgEmissorRGCliente.setText(cliente.getOrg_Emissor_RG_Cliente());
            txtCPFCliente.setText(cliente.getCpf_Cliente());
            txtRendaCliente.setText(fn.FloatConvertido(String.valueOf(cliente.getRenda_Cliente())));
            txtProfissãoCliente.setText(cliente.getProfissao_Cliente());
            txtLocalTrabalhoCliente.setText(cliente.getLocal_Trabalho_Cliente());
            txtNomeConjujeCliente.setText(cliente.getNome_Conjuje_Cliente());
            btnEditar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnSalvar.setEnabled(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroDeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtNomePesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomePesquisaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomePesquisaKeyPressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            // TODO add your handling code here:
            atualizaCliente();
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnSalvar.setEnabled(true);
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroDeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            // TODO add your handling code here:
            excluiCliente();
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroDeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbEstadoCivilCliente;
    private javax.swing.JComboBox<String> jcbEstadoCliente;
    private javax.swing.JComboBox<String> jcbEstadoEmissorRGCliente;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtApelidoCliente;
    private javax.swing.JTextField txtBairroCliente;
    private javax.swing.JFormattedTextField txtCPFCliente;
    private javax.swing.JTextField txtCidadeCliente;
    private javax.swing.JFormattedTextField txtDataNascimentoCliente;
    private javax.swing.JFormattedTextField txtFoneCliente;
    private javax.swing.JTextField txtLocalTrabalhoCliente;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeConjujeCliente;
    private javax.swing.JTextField txtNomeMaeCliente;
    private javax.swing.JTextField txtNomePaiCliente;
    private javax.swing.JTextField txtNomePesquisa;
    private javax.swing.JTextField txtNumeroCasaCliente;
    private javax.swing.JTextField txtOrgEmissorRGCliente;
    private javax.swing.JTextField txtProfissãoCliente;
    private javax.swing.JTextField txtRGCliente;
    private javax.swing.JFormattedTextField txtRendaCliente;
    private javax.swing.JTextField txtRuaCasaCliente;
    // End of variables declaration//GEN-END:variables
}
