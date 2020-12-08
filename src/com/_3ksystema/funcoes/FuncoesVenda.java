/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.classes.FormataData;
import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Produto;
import com._3ksystema.modelos.ProdutosVenda;
import com._3ksystema.modelos.RelVendaPer;
import com._3ksystema.modelos.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class FuncoesVenda {

    private ModuloConector mc = new ModuloConector();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private Venda venda = new Venda();
    private ArrayList<Venda> vendas = new ArrayList();
    private Cliente cliente = new Cliente();
    private Produto produto = new Produto();
    private ProdutosVenda pv = new ProdutosVenda();
    private ArrayList<RelVendaPer> rvps = new ArrayList();
    private FuncoesProdutoVenda fpv;
    private FormataData fd = new FormataData();
    private FuncoesCliente fc = new FuncoesCliente();

    public ResultSet pesquisaVenda() throws ClassNotFoundException {
        String sql = "SELECT * FROM `dba.venda`";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public void cadastraVenda(Venda vend) throws ClassNotFoundException {
        String sql = "INSERT INTO `dba.venda`(`dba.clientes_id_cliente`, `valorVenda`, `dataVenda`, `tipoVenda`, `qtdParcelaVenda`, `valorEntradaVenda`, `dataPgtoPrimeiraparcela`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, vend.getCliente());
            pst.setDouble(2, vend.getValorVenda());
            pst.setString(3, vend.getDataVenda());
            pst.setString(4, vend.getTipoVenda());
            pst.setInt(5, vend.getQtdParcelas());
            pst.setDouble(6, vend.getValorEntrada());
            pst.setString(7, vend.getDataPgtoParcela());
            boolean salvou = pst.execute();
            if (salvou) {
                JOptionPane.showMessageDialog(null, "Falha ao gravar as informações no banco de dados", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //fpv.salvaProdutosVenda(vend.getProdutos());
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int buscaUltimaVenda() throws ClassNotFoundException {
        String sql = "SELECT `idVenda` FROM `dba.venda` ORDER BY `idVenda` DESC LIMIT 1";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                venda.setIdVenda(rs.getInt(1));
            }
            return venda.getIdVenda();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
    }

    public void atualizaVenda(Venda vend) throws ClassNotFoundException {
        String sql = "UPDATE `dba.venda` SET `dba.clientes_id_cliente` = ?, `valorVenda` = ?, `dataVenda` = ?, `tipoVenda` = ?, `qtdParcelaVenda` = ?, `valorEntradaVenda` = ?, `dataPgtoPrimeiraparcela` = ? WHERE `idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, vend.getCliente());
            fpv.venda.setIdVenda(vend.getIdVenda());
            fpv.alteraProdutosVenda(vend.getProdutos());
            pst.setDouble(2, vend.getValorVenda());
            pst.setString(3, vend.getDataVenda());
            pst.setString(4, vend.getTipoVenda());
            pst.setInt(5, vend.getQtdParcelas());
            pst.setDouble(6, vend.getValorEntrada());
            pst.setString(7, vend.getDataPgtoParcela());
            pst.setInt(8, vend.getIdVenda());
            int atualizou = pst.executeUpdate();
            if (atualizou == 1) {

            } else {
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void excluiVenda(Venda vend) throws ClassNotFoundException {
        String sql = "DELETE FROM `dba.venda` WHERE `idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, vend.getIdVenda());
            boolean excluiu = pst.execute();
            if (excluiu) {
                fpv.excluiProdutosVendas(vend.getIdVenda());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void diminuiQuantidade(int qtdVenda, Produto produto) throws ClassNotFoundException {
        String sql = "UPDATE `dba.produtos` SET `qtdEstoqueProduto` = ? WHERE `idProduto` = ?";
        int qtdEstoque = verificaQuantidade(produto.getNomeProduto());
        int qtdEstPVenda = qtdEstoque - qtdVenda;
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, qtdEstPVenda);
            pst.setInt(2, produto.getIdProduto());
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int verificaQuantidade(String nomeProduto) throws ClassNotFoundException {
        String sql = "SELECT `qtdEstoqueProduto` FROM `dba.produtos` WHERE `nomeProduto` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeProduto);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("qtdEstoqueProduto");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado no sistema:\n"
                        + "Verifique o nome e tente novamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return -1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
    }

    public Venda buscaVendaId(int idVenda) throws ClassNotFoundException {
        String sql = "SELECT * FROM `dba.venda` WHERE `idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idVenda);
            rs = pst.executeQuery();
            if (rs.next()) {
                venda.setIdVenda(rs.getInt(1));
                venda.setCliente(rs.getInt(2));
                venda.setValorVenda(rs.getDouble(3));
                venda.setDataVenda(rs.getString(4));
                venda.setTipoVenda(rs.getString(5));
                venda.setQtdParcelas(rs.getInt(6));
                venda.setValorEntrada(rs.getDouble(7));
                venda.setDataPgtoParcela(rs.getString(8));
            }
            return venda;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public ResultSet buscaVendaCliente(int idCliente) {
        String sql = "SELECT `dataVenda` AS \"Data da Venda\", `tipoVenda` AS \"Forma de pagamento\", `valorVenda` AS \"Valor da Venda\" FROM `dba.venda` WHERE `dba.clientes_id_cliente` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idCliente);
            return pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public ArrayList<RelVendaPer> listaVendasPeriodo(String dataInicio, String dataFim) {
        String sql = "SELECT `dba.clientes_id_cliente`, `dataVenda`, `valorVenda` FROM `dba.venda` WHERE `dataVenda` BETWEEN ? AND ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, fd.formataData(dataInicio));
            pst.setString(2, fd.formataData(dataFim));
            rs = pst.executeQuery();
            if (rs.next()) {
                do {
                    RelVendaPer rvp = new RelVendaPer();
                    Cliente clie = new Cliente();
                    clie = fc.pesquisaClienteCodigo(rs.getInt(1));
                    rvp.setNomeCliente(clie.getNome_Cliente());
                    rvp.setDataVenda(fd.dataBr(rs.getString(2)));
                    rvp.setValorVenda(rs.getDouble(3));
                    rvps.add(rvp);
                } while (rs.next());
            }
            return rvps;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public Venda pesquisaClienteData(String dataVenda, int idCliente) {
        String sql = "SELECT * FROM `dba.venda` WHERE `dataVenda` = ? AND `dba.clientes_id_cliente` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataVenda);
            pst.setInt(2, idCliente);
            rs = pst.executeQuery();
            if (rs.next()) {
                venda.setIdVenda(rs.getInt(1));
                venda.setCliente(rs.getInt(2));
                venda.setValorVenda(rs.getDouble(3));
                venda.setDataVenda(rs.getString(4));
                venda.setTipoVenda(rs.getString(5));
                venda.setQtdParcelas(rs.getInt(6));
                venda.setValorEntrada(rs.getDouble(7));
                venda.setDataPgtoParcela(rs.getString(8));
            }
            return venda;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<String> pesquisaVendasData(String data){
        String sql = "SELECT cliente.`nome_cliente` AS \"Nome Cliente\" FROM `dba.venda` AS venda INNER JOIN `dba.clientes` AS cliente WHERE venda.`dataVenda` = ? AND venda.`dba.clientes_id_cliente` = cliente.`id_cliente`";
        ArrayList<String> listas = new ArrayList();
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            rs = pst.executeQuery();
            if (rs.next()) {
                do{
                    String vend = new String();
                    vend = (rs.getString(1));
                    listas.add(vend);
                }while(rs.next());
            }
            return listas;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}
