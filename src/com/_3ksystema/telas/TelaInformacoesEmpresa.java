/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.telas;

import com._3ksystema.funcoes.FuncoesEmpresa;
import com._3ksystema.funcoes.FuncoesParcelas;
import com._3ksystema.modelos.Empresa;
import com._3ksystema.modelos.Parcelas;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mariano Junior
 */
public class TelaInformacoesEmpresa extends javax.swing.JInternalFrame {

    private Empresa empresa = new Empresa();
    private Empresa verifica = new Empresa();
    private Parcelas parcela = new Parcelas();
    private FuncoesEmpresa fe = new FuncoesEmpresa();
    private ArrayList<Parcelas> parcelas = new ArrayList();
    private FuncoesParcelas fp = new FuncoesParcelas();

    /**
     * Creates new form TelaInformacoesEmpresa
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public TelaInformacoesEmpresa() throws ClassNotFoundException, SQLException {
        initComponents();
        verifica = verificaCadastro();
        jcbEstEmpresa.setSelectedIndex(17);
        //System.out.println("Nome no DBA: " + verifica.getNomeEmpresa() + "\nNome no Campo: " + txtNomeEmpresa.getText());
        setarCampos(verifica);
        pegarParcelas();
        if (verifica.getNomeEmpresa() == null) {
            btnSalvar.setText("Salvar");
        } else {
            btnSalvar.setText("Atualizar");
        }
    }

    private void sairTela() {
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair da Tela de Atualização da Empresa?", "Sair", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private Empresa verificaCadastro() throws ClassNotFoundException, SQLException {
        verifica = fe.pesquisaEmpresa();
        return verifica;
    }

    private String pegaImagem(){
        JFileChooser seletor = new JFileChooser();
        seletor.showOpenDialog(this);
        seletor.setMultiSelectionEnabled(false);
        seletor.setDialogTitle("Selecione a logo da empresa");
        seletor.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter ff = new FileNameExtensionFilter("Imagens", "jpg");
        seletor.setFileFilter(ff);
        String endereco = "";
        int retorno = seletor.showSaveDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletor.getCurrentDirectory();
            String caminho = arquivo.getPath();
            arquivo = seletor.getSelectedFile();
            String nomeArquivo = arquivo.getName() + ".jpg";
            endereco = caminho + "\\" + nomeArquivo;
            System.out.println(endereco);
        }
        return endereco;
    }
    
    private void setarCampos(Empresa empre) {
        txtNomeEmpresa.setText(empre.getNomeEmpresa());
        txtCidEmpresa.setText(empre.getCidadeEmpresa());
        txtCPFEmpresa.setText(empre.getCpfDirEmpresa());
        txtEndEmpresa.setText(empre.getRuaEmpresa());
        txtCnpjEmpresa.setText(empre.getCnpjEmpresa());
        txtDirEmpresa.setText(empre.getDirEmpresa());
        txtFoneEmpresa.setText(empre.getFoneEmpresa());
        txtEmailEmpresa.setText(empre.getEmailEmpresa());
        txtNumEmpresa.setText(String.valueOf(empre.getNumEmpresa()));
        txtIEsEmpresa.setText(empre.getIncrEmpresa());
        txtBairroEmpresa.setText(empre.getBairroEmpresa());
        txtRGDEmpresa.setText(empre.getRgDirEmpresa());
        jcbEstEmpresa.setSelectedItem(empre.getEstadoEmpresa());
    }

    private void salvarEmpresa() throws ClassNotFoundException, SQLException {
        empresa.setNomeEmpresa(txtNomeEmpresa.getText());
        empresa.setCidadeEmpresa(txtCidEmpresa.getText());
        empresa.setBairroEmpresa(txtBairroEmpresa.getText());
        empresa.setCnpjEmpresa(txtCnpjEmpresa.getText());
        empresa.setNumEmpresa(Integer.parseInt(txtNumEmpresa.getText()));
        empresa.setDirEmpresa(txtDirEmpresa.getText());
        empresa.setRgDirEmpresa(txtRGDEmpresa.getText());
        empresa.setCpfDirEmpresa(txtCPFEmpresa.getText());
        empresa.setFoneEmpresa(txtFoneEmpresa.getText());
        empresa.setRuaEmpresa(txtEndEmpresa.getText());
        empresa.setEmailEmpresa(txtEmailEmpresa.getText());
        empresa.setEstadoEmpresa(jcbEstEmpresa.getSelectedItem().toString());
        empresa.setIncrEmpresa(txtIEsEmpresa.getText());
        String funcao = btnSalvar.getText();
        if (funcao.equals("Salvar")) {
            fe.cadastraEmpresa(empresa);
        } else {
            fe.atualizaEmpresa(empresa);
        }
    }

    private void cadastrarParcelas() throws ClassNotFoundException, SQLException{
        Parcelas parcela1 = new Parcelas();
        Parcelas parcela2 = new Parcelas();
        Parcelas parcela3 = new Parcelas();
        Parcelas parcela4 = new Parcelas();
        Parcelas parcela5 = new Parcelas();
        Parcelas parcela6 = new Parcelas();
        Parcelas parcela7 = new Parcelas();
        Parcelas parcela8 = new Parcelas();
        Parcelas parcela9 = new Parcelas();
        Parcelas parcela10 = new Parcelas();
        Parcelas parcela11 = new Parcelas();
        Parcelas parcela12 = new Parcelas();
        parcela1.setIdParcela(1);
        parcela1.setPercento(Integer.parseInt(txtJurosAVista.getText()));
        parcela2.setIdParcela(2);
        parcela2.setPercento(Integer.parseInt(txtJurosDuasParcelas.getText()));
        parcela3.setIdParcela(3);
        parcela3.setPercento(Integer.parseInt(txtJurosTresParcelas.getText()));
        parcela4.setIdParcela(4);
        parcela4.setPercento(Integer.parseInt(txtJurosQuatroParcelas.getText()));
        parcela5.setIdParcela(5);
        parcela5.setPercento(Integer.parseInt(txtJurosCincoParcelas.getText()));
        parcela6.setIdParcela(6);
        parcela6.setPercento(Integer.parseInt(txtJurosSeisParcelas.getText()));
        parcela7.setIdParcela(7);
        parcela7.setPercento(Integer.parseInt(txtJurosSeteParcelas.getText()));
        parcela8.setIdParcela(8);
        parcela8.setPercento(Integer.parseInt(txtJurosOitoParcelas.getText()));
        parcela9.setIdParcela(9);
        parcela9.setPercento(Integer.parseInt(txtJurosNoveParcelas.getText()));
        parcela10.setIdParcela(10);
        parcela10.setPercento(Integer.parseInt(txtJurosDezParcelas.getText()));
        parcela11.setIdParcela(11);
        parcela11.setPercento(Integer.parseInt(txtJurosOnzeParcelas.getText()));
        parcela12.setIdParcela(12);
        parcela12.setPercento(Integer.parseInt(txtJurosDozeParcelas.getText()));
        parcelas.add(parcela1);
        parcelas.add(parcela2);
        parcelas.add(parcela3);
        parcelas.add(parcela4);
        parcelas.add(parcela5);
        parcelas.add(parcela6);
        parcelas.add(parcela7);
        parcelas.add(parcela8);
        parcelas.add(parcela9);
        parcelas.add(parcela10);
        parcelas.add(parcela11);
        parcelas.add(parcela12);
        fp.salvaParcelas(parcelas);
    }
    
    private void pegarParcelas(){
        parcelas = fp.pesquisaParcelas();
        parcela = parcelas.get(0);
        txtJurosAVista.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(1);
        txtJurosDuasParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(2);
        txtJurosTresParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(3);
        txtJurosQuatroParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(4);
        txtJurosCincoParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(5);
        txtJurosSeisParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(6);
        txtJurosSeteParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(7);
        txtJurosOitoParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(8);
        txtJurosNoveParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(9);
        txtJurosDezParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(10);
        txtJurosOnzeParcelas.setText(String.valueOf(parcela.getPercento()));
        parcela = parcelas.get(11);
        txtJurosDozeParcelas.setText(String.valueOf(parcela.getPercento()));
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNomeEmpresa = new javax.swing.JTextField();
        txtEndEmpresa = new javax.swing.JTextField();
        txtNumEmpresa = new javax.swing.JTextField();
        txtCidEmpresa = new javax.swing.JTextField();
        txtIEsEmpresa = new javax.swing.JTextField();
        txtDirEmpresa = new javax.swing.JTextField();
        txtRGDEmpresa = new javax.swing.JTextField();
        txtEmailEmpresa = new javax.swing.JTextField();
        jcbEstEmpresa = new javax.swing.JComboBox<>();
        txtCnpjEmpresa = new javax.swing.JFormattedTextField();
        txtCPFEmpresa = new javax.swing.JFormattedTextField();
        txtFoneEmpresa = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtBairroEmpresa = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnPegarLogo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtJurosAVista = new javax.swing.JTextField();
        txtJurosDuasParcelas = new javax.swing.JTextField();
        txtJurosTresParcelas = new javax.swing.JTextField();
        txtJurosQuatroParcelas = new javax.swing.JTextField();
        txtJurosCincoParcelas = new javax.swing.JTextField();
        txtJurosSeisParcelas = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtJurosDozeParcelas = new javax.swing.JTextField();
        txtJurosOnzeParcelas = new javax.swing.JTextField();
        txtJurosDezParcelas = new javax.swing.JTextField();
        txtJurosNoveParcelas = new javax.swing.JTextField();
        txtJurosOitoParcelas = new javax.swing.JTextField();
        txtJurosSeteParcelas = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btnSalvarJuros = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setTitle("Dados da Empresa");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dados da Empresa");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Empresa:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Endereco:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Cidade:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Estado:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Inscrição Estadual:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("CNPJ:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Diretor:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("RG Diretor:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("CPF Diretor:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Telefone:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Nº:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Email:");

        txtNomeEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEndEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNumEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCidEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtIEsEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDirEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtRGDEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEmailEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jcbEstEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbEstEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        try {
            txtCnpjEmpresa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCnpjEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtCPFEmpresa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPFEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtFoneEmpresa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFoneEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Bairro:");

        txtBairroEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Logo:");

        btnPegarLogo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPegarLogo.setText("Imagem");
        btnPegarLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegarLogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomeEmpresa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEndEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCidEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBairroEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbEstEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIEsEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCnpjEmpresa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDirEmpresa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRGDEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCPFEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFoneEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPegarLogo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(txtEndEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtCidEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbEstEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtBairroEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtIEsEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCnpjEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDirEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(txtRGDEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPFEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(txtEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFoneEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(btnPegarLogo))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados da Empresa", jPanel1);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Numero de Parcelas:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("À vista");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("2 Parcelas");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("3 Parcelas");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("4 Parcelas");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("5 Parcelas");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("6 Parcelas");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Taxa de Juros:");

        txtJurosAVista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosDuasParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosTresParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosQuatroParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosCincoParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosSeisParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Numero de Parcelas:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("7 Parcelas");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("8 Parcelas");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("9 Parcelas");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("10 Parcelas");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("11 Parcelas");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("12 Parcelas");

        txtJurosDozeParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosOnzeParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosDezParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosNoveParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosOitoParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtJurosSeteParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Taxa de Juros:");

        btnSalvarJuros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvarJuros.setText("Salvar Juros");
        btnSalvarJuros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarJurosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(txtJurosAVista))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtJurosDuasParcelas))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtJurosTresParcelas))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtJurosQuatroParcelas))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtJurosCincoParcelas))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(txtJurosSeisParcelas)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSalvarJuros)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtJurosSeteParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtJurosOitoParcelas))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtJurosNoveParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtJurosDezParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtJurosOnzeParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(txtJurosDozeParcelas))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtJurosAVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosDuasParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosTresParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosQuatroParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosCincoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosSeisParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtJurosSeteParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosOitoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosNoveParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosDezParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosOnzeParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosDozeParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSalvarJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Juros e Parcelas", jPanel2);

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        sairTela();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            // TODO add your handling code here:
            salvarEmpresa();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TelaInformacoesEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnPegarLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegarLogoActionPerformed
        // TODO add your handling code here:
        pegaImagem();
    }//GEN-LAST:event_btnPegarLogoActionPerformed

    private void btnSalvarJurosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarJurosActionPerformed
        try {
            // TODO add your handling code here:
            cadastrarParcelas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TelaInformacoesEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarJurosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPegarLogo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvarJuros;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbEstEmpresa;
    private javax.swing.JTextField txtBairroEmpresa;
    private javax.swing.JFormattedTextField txtCPFEmpresa;
    private javax.swing.JTextField txtCidEmpresa;
    private javax.swing.JFormattedTextField txtCnpjEmpresa;
    private javax.swing.JTextField txtDirEmpresa;
    private javax.swing.JTextField txtEmailEmpresa;
    private javax.swing.JTextField txtEndEmpresa;
    private javax.swing.JFormattedTextField txtFoneEmpresa;
    private javax.swing.JTextField txtIEsEmpresa;
    private javax.swing.JTextField txtJurosAVista;
    private javax.swing.JTextField txtJurosCincoParcelas;
    private javax.swing.JTextField txtJurosDezParcelas;
    private javax.swing.JTextField txtJurosDozeParcelas;
    private javax.swing.JTextField txtJurosDuasParcelas;
    private javax.swing.JTextField txtJurosNoveParcelas;
    private javax.swing.JTextField txtJurosOitoParcelas;
    private javax.swing.JTextField txtJurosOnzeParcelas;
    private javax.swing.JTextField txtJurosQuatroParcelas;
    private javax.swing.JTextField txtJurosSeisParcelas;
    private javax.swing.JTextField txtJurosSeteParcelas;
    private javax.swing.JTextField txtJurosTresParcelas;
    private javax.swing.JTextField txtNomeEmpresa;
    private javax.swing.JTextField txtNumEmpresa;
    private javax.swing.JTextField txtRGDEmpresa;
    // End of variables declaration//GEN-END:variables
}
