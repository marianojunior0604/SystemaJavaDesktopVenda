/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.classes.FormataData;
import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Entradas;
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
public class FuncoesEntrada {
    
    private ModuloConector mc = new ModuloConector();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private Entradas entrada = new Entradas();
    private ArrayList<Entradas> entradas = new ArrayList();
    private FuncoesCaixa fc = new FuncoesCaixa();
    private FormataData fd = new FormataData();
    
    public Entradas pesquisaEntrada(String motivoEntrada) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.entrada` WHERE `naturezaEntrada` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, motivoEntrada);
            rs = pst.executeQuery();
            if (rs.next()) {
                entrada.setIdEntrada(rs.getInt(1));
                entrada.setNaturezaEntrada(rs.getString(2));
                entrada.setDataEntrada(rs.getString(3));
                entrada.setValorEntrada(rs.getDouble(4));
                return entrada;
            }else{
                JOptionPane.showMessageDialog(null, "Entrada não encontrada no banco de dados", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ResultSet listaEntradas(String dataPesquisa) throws ClassNotFoundException{
        String sql = "SELECT `naturezaEntrada` AS Motivo, `dataEntrada` AS Data, `valorEntrada` AS Valor FROM `dba.entrada` WHERE `dataEntrada` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataPesquisa);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma entrada registrada na data especificada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void atualizaEntrada(Entradas entradaAtualizar) throws ClassNotFoundException{
        String sql = "UPDATE `dba.entrada` SET `naturezaEntrada` = ?,`dataEntrada` = ?,`valorEntrada` = ? WHERE `idEntrada` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, entradaAtualizar.getNaturezaEntrada());
            pst.setString(2, entradaAtualizar.getDataEntrada());
            pst.setDouble(3, entradaAtualizar.getValorEntrada());
            pst.setInt(4, entradaAtualizar.getIdEntrada());
            int atualizou = pst.executeUpdate();
            if (atualizou == 1) {
                JOptionPane.showMessageDialog(null, "Entrada Atualizada com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar o registro de entrada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void excluiEntrada(int idEntrada) throws ClassNotFoundException{
        String sql = "DELETE FROM `dba.entrada` WHERE `idEntrada` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idEntrada);
            boolean excluiu = pst.execute();
            if (excluiu) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir a entrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Entrada excluida com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void salvaEntrada(Entradas entr) throws ClassNotFoundException{
        String sql = "INSERT INTO `dba.entrada`(`naturezaEntrada`, `dataEntrada`, `valorEntrada`) VALUES (?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, entr.getNaturezaEntrada());
            pst.setString(2, entr.getDataEntrada());
            pst.setDouble(3, entr.getValorEntrada());
            boolean salvou = pst.execute();
            if (salvou) {
                JOptionPane.showMessageDialog(null, "Falha ao salvar a entrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Entrada Salva com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList listaEntradasPeriodo(String dataInicio, String dataFim) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.entrada` WHERE `dataEntrada` BETWEEN ? AND ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataInicio);
            pst.setString(2, dataFim);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Entradas enter = new Entradas();
                    enter.setDataEntrada(rs.getString(1));
                    enter.setIdEntrada(rs.getInt(1));
                    enter.setNaturezaEntrada(rs.getString(1));
                    enter.setValorEntrada(rs.getDouble(1));
                    entradas.add(enter);
                } while (rs.next());
                return entradas;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhma entrada encontrada no periodo descrito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ArrayList entradasDoDia(String dataDia) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.entrada` WHERE `dataEntrada` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataDia);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Entradas enter = new Entradas();
                    enter.setIdEntrada(rs.getInt(1));
                    enter.setNaturezaEntrada(rs.getString(2));
                    enter.setDataEntrada(fd.dataBr(rs.getString(3)));
                    enter.setValorEntrada(rs.getDouble(4));
                    entradas.add(enter);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma entrada encontrada na data de hoje", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                entradas = null;
            }
            return entradas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public double somaValorEntradaData(ArrayList<Entradas> entradas){
        double valorDia = 0.0;
        for (Entradas entrada1 : entradas) {
            valorDia = valorDia + entrada1.getValorEntrada();
        }
        return valorDia;
    }
    
    public ArrayList listaEntradaDatas(String dataIncio, String dataFim) throws ClassNotFoundException{
        String sql = "";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataIncio);
            pst.setString(2, dataFim);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Entradas enter = new Entradas();
                    enter.setIdEntrada(rs.getInt(1));
                    enter.setNaturezaEntrada(rs.getString(2));
                    enter.setDataEntrada(rs.getString(3));
                    enter.setValorEntrada(rs.getDouble(4));
                    entradas.add(enter);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma entrada encontrada na data de hoje", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                entradas = null;
            }
            return entradas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}
