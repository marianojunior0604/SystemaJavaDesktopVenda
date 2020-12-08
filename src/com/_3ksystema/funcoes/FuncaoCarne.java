/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.classes.FormataData;
import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Carne;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class FuncaoCarne {

    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private FuncoesVenda fv = new FuncoesVenda();
    private FuncoesCliente fc = new FuncoesCliente();
    private ModuloConector mc = new ModuloConector();
    private ArrayList<Carne> carnes = new ArrayList();
    private FormataData fd = new FormataData();
    //private LocalDate data;// = LocalDate.now();
    private DateTimeFormatter formatDateBar = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Carne carne = new Carne();
    private Venda venda;
    private Cliente cliente;

    public void criarCarnes(Venda venda) throws ClassNotFoundException {
        String sql = "INSERT INTO `dba.carne`(`qtdParcelas`, `valorParcela`, `dataVencimento`, `dba.venda_idVenda`, `dba.clientes_id_cliente`, `statusParcela`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            cliente = fc.pesquisaClienteCodigo(venda.getCliente());
            double valorDividido = venda.getValorVenda() - venda.getValorEntrada();
            //System.out.println("Valor da Venda: " + venda.getValorVenda() + "\nValor da Entrada: " + venda.getValorEntrada());
            int nParcela = venda.getQtdParcelas();
            double valorParcela = valorDividido / nParcela;
            //System.out.println("Valor à ser Parceldo: " + valorDividido + "\nNumero de Parcelas: " + nParcela + "\nO Valor das parcelas: " + valorParcela);
            for (int i = 0; i < nParcela; i++) {
                String dataVencimento = LocalDate.parse(venda.getDataPgtoParcela()).plusMonths(i + 1).format(formatDateBar);
                pst.setInt(1, venda.getQtdParcelas());
                pst.setDouble(2, valorParcela);
                pst.setString(3, dataVencimento);
                pst.setInt(4, venda.getIdVenda());
                pst.setInt(5, venda.getCliente());
                pst.setBoolean(6, false);
                pst.execute();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Carne pesquisaCarne(int idCompra) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.carne` WHERE `idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idCompra);
            rs = pst.executeQuery();
            if (rs.next()) {
                carne.setIdCarne(rs.getInt(1));
                carne.setIdCompra(rs.getInt(2));
                carne.setNumeroParcela(rs.getInt(3));
                carne.setValorParcela(rs.getDouble(4));
                carne.setDataVencimento(rs.getString(5));
                carne.setParcelaPaga(rs.getBoolean(6));
                return carne;
            }else{
                JOptionPane.showMessageDialog(null, "Carnê não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public Carne pesquisaCarnePagamento(int idCarne) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.carne` WHERE `idCarne` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idCarne);
            rs = pst.executeQuery();
            if (rs.next()) {
                carne.setIdCarne(rs.getInt(1));
                carne.setNumeroParcela(rs.getInt(2));
                carne.setValorParcela(rs.getDouble(3));
                carne.setDataVencimento(rs.getString(4));
                carne.setIdCompra(rs.getInt(5));
                carne.setIdCliente(rs.getInt(6));
                carne.setParcelaPaga(rs.getBoolean(7));
                carne.setDataPagamento(rs.getString(8));
                return carne;
            } else {
                JOptionPane.showMessageDialog(null, "Carnê não encontrado, verifique o codigo e tente novament", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void receberCarne(int idCarne, String dataPagamento) throws ClassNotFoundException{
        String sql = "UPDATE `dba.carne` SET `statusParcela` = true, `dataPagamento` = ? WHERE `idCarne` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataPagamento);
            pst.setInt(2, idCarne);
            int atualizou = pst.executeUpdate();
            if (atualizou == 1) {
                JOptionPane.showMessageDialog(null, "Pagamento Realizado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:\n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList<Carne> listaCarnes (int idCompra) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.carne` WHERE `dba.venda_idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idCompra);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Carne c = new Carne();
                    c.setIdCarne(rs.getInt(1));
                    c.setValorParcela(rs.getDouble(2));
                    c.setNumeroParcela(rs.getInt(3));
                    c.setDataVencimento(fd.dataBr(rs.getString(4)));
                    c.setIdCompra(rs.getInt(5));
                    c.setIdCliente(rs.getInt(6));
                    c.setParcelaPaga(rs.getBoolean(7));
                    carnes.add(c);
                } while (rs.next());
            }
            return carnes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ResultSet listaCarnesAtrasados(String dataHoje) throws ClassNotFoundException{
        String sql = "SELECT cliente.`nome_cliente` AS Nome, cliente.`fone_cliente` AS Telefone, carne.`valorParcela` AS Valor, carne.`dataVencimento` AS Vencimento FROM `dba.carne`AS carne INNER JOIN `dba.clientes` AS cliente ON cliente.`id_cliente` = carne.`dba.clientes_id_cliente` WHERE `dataVencimento` < ? AND `statusParcela` = 0";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataHoje);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<Carne> listaCarnesNPagos(int idCliente) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.carne` WHERE `dba.clientes_id_cliente` = ? AND `statusParcela` = 0";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idCliente);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Carne boleto = new Carne();
                    boleto.setIdCarne(rs.getInt(1));
                    boleto.setNumeroParcela(rs.getInt(2));
                    boleto.setValorParcela(rs.getDouble(3));
                    boleto.setDataVencimento(rs.getString(4));
                    boleto.setIdCompra(rs.getInt(5));
                    boleto.setIdCliente(rs.getInt(6));
                    carnes.add(boleto);
                } while (rs.next());
            }
            return carnes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ResultSet historicoCarnesCliente(int idVenda, int idCliente){
        String sql = "SELECT `valorParcela` AS \"Valor da Parcela\", `dataVencimento` AS \"Vencimento\", `dataPagamento` AS \"Pagamento\" FROM `dba.carne` WHERE `dba.clientes_id_cliente` = ? AND `dba.venda_idVenda` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idVenda);
            pst.setInt(2, idCliente);
            return pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
}
