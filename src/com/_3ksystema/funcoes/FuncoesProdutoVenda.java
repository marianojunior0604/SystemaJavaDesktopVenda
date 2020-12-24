/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Produto;
import com._3ksystema.modelos.ProdutosVenda;
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
public class FuncoesProdutoVenda {

    private ModuloConector mc = new ModuloConector();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private ProdutosVenda pv;
    public Venda venda = new Venda();
    private FuncoesVenda fv = new FuncoesVenda();
    private Produto produto = new Produto();
    private ArrayList<ProdutosVenda> produtosVenda = new ArrayList();

    public void salvaProdutosVenda(ArrayList<ProdutosVenda> produtosVenda) throws ClassNotFoundException {
        String sql = "INSERT INTO `dba.produtos.venda`(`dba.produtos_idProduto`, `dba.venda_idVenda`, `qtdPr`) VALUES (?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            //int idVenda = fv.buscaUltimaVenda();
            for (ProdutosVenda produto : produtosVenda) {
                pst.setInt(1, produto.getIdproduto());
                //produto.setIdVenda(idVenda);
                pst.setInt(2, produto.getIdVenda());
                pst.setInt(3, produto.getQtdProdutoVenda());
                //System.out.println(pst);
                pst.execute();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ArrayList<ProdutosVenda> pesquisaProdutosVenda(int idVenda) throws ClassNotFoundException {
        String sql = "SELECT * FROM `dba.produtos.venda` WHERE `dba.venda_idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idVenda);
            System.out.println(pst);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {
                    pv.setIdListaProdutosVenda(idVenda);
                    pv.setIdVenda(idVenda);
                    pv.setIdproduto(idVenda);
                } while (rs.next());
                produtosVenda.add(pv);
                return produtosVenda;
            } else {
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public ProdutosVenda buscaVenda(int idClientVenda) throws ClassNotFoundException {
        String sql = "SELECT * FROM `dba.produtos.venda` WHERE `dba.venda_idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idClientVenda);
            rs = pst.executeQuery();
            if (rs.next()) {
                pv.setIdListaProdutosVenda(rs.getInt(1));
                pv.setIdproduto(rs.getInt(2));
                pv.setIdVenda(rs.getInt(3));
            }
            return pv;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void alteraProdutosVenda(ArrayList<ProdutosVenda> produtosVenda) throws ClassNotFoundException{
        excluiProdutosVendas(venda.getIdVenda());
        salvaProdutosVenda(produtosVenda);
    }
    
    public void excluiProdutosVendas(int idVenda) throws ClassNotFoundException{
        String sql = "DELETE FROM `dba.produtos.venda` WHERE `dba.venda_idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idVenda);
            pst.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Produto pesquisaProdutoVenda(int idProdutoVenda){
        String sql = "SELECT * FROM `dba.produtos` WHERE `idProduto` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idProdutoVenda);
            rs = pst.executeQuery();
            if (rs.next()) {
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setMarcaProtudo(rs.getString("marcaProduto"));
                produto.setModeloProtudo(rs.getString("modeloProduto"));
                produto.setValorVendaProduto(rs.getDouble("valorVendaProduto"));
            }
            return produto;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}
