/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Produto;
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
public class FuncoesProduto {
    
    private ModuloConector mc = new ModuloConector();
    private Produto produto = new Produto();
    private ArrayList<Produto> produtos = new ArrayList();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ResultSet listaProdutos() throws ClassNotFoundException{
        String sql = "SELECT `nomeProduto` AS Nome, `marcaProduto` AS Marca, `valorVendaProduto` AS Preço, `qtdEstoqueProduto` AS Estoque FROM `dba.produtos` ORDER BY `nomeProduto` ASC";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public Produto pesquisaProduto(String nomeProduto) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.produtos` WHERE `nomeProduto` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeProduto);
            rs = pst.executeQuery();
            if (rs.next()) {
                produto.setIdProduto(rs.getInt(1));
                produto.setNomeProduto(rs.getString(2));
                produto.setMarcaProtudo(rs.getString(3));
                produto.setModeloProtudo(rs.getString(4));
                produto.setCaracteristicaProduto(rs.getString(5));
                produto.setQtdEstoqueProduto(rs.getInt(6));
                produto.setValorCompraProduto(rs.getDouble(7));
                produto.setValorVendaProduto(rs.getDouble(8));
                produto.setPercentualLucro(rs.getDouble(9));
                return produto;
            } else {
                JOptionPane.showMessageDialog(null, "", "", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void salvaProduto(Produto prod) throws ClassNotFoundException{
        String sql = "INSERT INTO `dba.produtos`(`nomeProduto`, `marcaProduto`, `modeloProduto`, `caracteristicaProduto`, `qtdEstoqueProduto`, `valorCompraProduto`, `valorVendaProduto`, `percentualLucro`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, prod.getNomeProduto());
            pst.setString(2, prod.getMarcaProtudo());
            pst.setString(3, prod.getModeloProtudo());
            pst.setString(4, prod.getCaracteristicaProduto());
            pst.setInt(5, prod.getQtdEstoqueProduto());
            pst.setDouble(6, prod.getValorCompraProduto());
            pst.setDouble(7, prod.getValorVendaProduto());
            pst.setDouble(8, prod.getPercentualLucro());
            //System.out.println(pst);
            boolean salvou = pst.execute();
            if (salvou) {
                JOptionPane.showMessageDialog(null, "Falha ao salvar o produto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto Salvo com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ResultSet buscaAvancada(String nomeProduto) throws ClassNotFoundException{
        String sql = "SELECT `nomeProduto` AS Nome, `marcaProduto` AS Marca, `valorVendaProduto` AS Preço, `qtdEstoqueProduto` AS Estoque FROM `dba.produtos` WHERE `nomeProduto` LIKE ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeProduto);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void alteraProduto(Produto prod) throws ClassNotFoundException{
        String sql = "UPDATE `dba.produtos` SET `nomeProduto` = ?, `marcaProduto` = ?, `modeloProduto` = ?, `caracteristicaProduto` = ?, `qtdEstoqueProduto` = ?, `valorCompraProduto` = ?, `valorVendaProduto` = ?, `percentualLucro` = ? WHERE `idProduto` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, prod.getNomeProduto());
            pst.setString(2, prod.getMarcaProtudo());
            pst.setString(3, prod.getModeloProtudo());
            pst.setString(4, prod.getCaracteristicaProduto());
            pst.setInt(5, prod.getQtdEstoqueProduto());
            pst.setDouble(6, prod.getValorCompraProduto());
            pst.setDouble(7, prod.getValorVendaProduto());
            pst.setDouble(8, prod.getPercentualLucro());
            pst.setInt(9, prod.getIdProduto());
            int atualizou = pst.executeUpdate();
            if (atualizou == 1) {
                JOptionPane.showMessageDialog(null, "Produto Atualizado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar o produto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void excluiProduto(int idProduto) throws ClassNotFoundException{
        String sql = "DELETE FROM `dba.produtos` WHERE `idProduto` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idProduto);
            boolean excluiu = pst.execute();
            if (excluiu) {
                JOptionPane.showMessageDialog(null, "Falha ao excluir o produto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Produto excluido com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void aumentaQuantidade(int qtdAumentar, Produto produto) throws ClassNotFoundException{
        String sql = "UPDATE `dba.produtos` SET `qtdEstoqueProduto` = ? WHERE `idProduto` = ?";
        int qtdEstoque = verificaQuantidade(produto.getNomeProduto());
        int qtdEstPVenda = qtdEstoque + qtdAumentar;
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
    
    public int verificaQuantidade(String nomeProduto) throws ClassNotFoundException{
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
    
    public Produto pesquisaProdCod(int idProduto) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.produtos` WHERE `idProduto` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idProduto);
            rs = pst.executeQuery();
            if (rs.next()) {
                produto.setIdProduto(rs.getInt(1));
                produto.setNomeProduto(rs.getString(2));
                produto.setMarcaProtudo(rs.getString(3));
                produto.setModeloProtudo(rs.getString(4));
                produto.setCaracteristicaProduto(rs.getString(5));
                produto.setQtdEstoqueProduto(rs.getInt(6));
                produto.setValorCompraProduto(rs.getDouble(7));
                produto.setValorVendaProduto(rs.getDouble(8));
                produto.setPercentualLucro(rs.getDouble(9));
                return produto;
            } else {
                JOptionPane.showMessageDialog(null, "", "", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}
