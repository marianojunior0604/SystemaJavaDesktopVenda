/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.classes.FormataData;
import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Caixa;
import com._3ksystema.modelos.Saidas;
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
public class FuncoesSaida {
    
    private ModuloConector mc = new ModuloConector();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private Saidas saida = new Saidas();
    private Caixa caixa = new Caixa();
    private FuncoesCaixa fc;
    private ArrayList<Saidas> saidas = new ArrayList();
    private FormataData fd = new FormataData();
    
    public void salvaSaida(Saidas sd) throws ClassNotFoundException{
        String sql = "INSERT INTO `dba.saida`(`naturezaSaida`, `dataSaida`, `valorSaida`) VALUES (?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, sd.getNaturezaSaida());
            pst.setString(2, sd.getDataSaida());
            pst.setDouble(3, sd.getValorSaida());
            boolean salvou = pst.execute();
            if (salvou) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar a saída", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Informação de saída salva", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Saidas pesquisaSaida(String nomeSaida) throws ClassNotFoundException{
        String sql = "SELECT  FROM `dba.saida` WHERE `naturezaSaida` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeSaida);
            rs = pst.executeQuery();
            if (rs.next()) {
                saida.setIdSaida(rs.getInt(1));
                saida.setNaturezaSaida(rs.getString(2));
                saida.setDataSaida(rs.getString(3));
                saida.setValorSaida(rs.getDouble(4));
                return saida;
            }else{
                JOptionPane.showMessageDialog(null, "Saida não encontrada no banco de dados, verifique os dados e tente novamente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ResultSet resgataSaidas(String dataSaida) throws ClassNotFoundException{
        String sql = "SELECT `naturezaSaida` AS Motivo, `dataSaida` AS Data, `valorSaida` AS Valor FROM `dba.saida` WHERE `dataSaida` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataSaida);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs;
            }else{
                JOptionPane.showMessageDialog(null, "Nenhuma saida registrada para a data especificada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void alteraSaida(Saidas sd) throws ClassNotFoundException{
        String sql = "UPDATE `dba.saida` SET `naturezaSaida` = ?, `dataSaida` = ?, `valorSaida` = ? WHERE `idSaida` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, sd.getNaturezaSaida());
            pst.setString(2, sd.getDataSaida());
            pst.setDouble(3, sd.getValorSaida());
            pst.setInt(4, sd.getIdSaida());
            int alterou = pst.executeUpdate();
            if (alterou == 1) {
                JOptionPane.showMessageDialog(null, "Saida alterada com sucesso.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao alterar a saida do caixa", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void excluirSaida(int idSaida) throws ClassNotFoundException{
        String sql = "DELETE FROM `dba.saida` WHERE `idSaida` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idSaida);
            boolean excluiu = pst.execute();
            if (excluiu) {
                JOptionPane.showMessageDialog(null, "Falha ao excluir a saida do caixa", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Saida excluida com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList saidasDia(String dataDia) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.saida` WHERE `dataSaida` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataDia);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Saidas exit = new Saidas();
                    exit.setIdSaida(rs.getInt(1));
                    exit.setNaturezaSaida(rs.getString(2));
                    exit.setDataSaida(fd.dataBr(rs.getString(3)));
                    exit.setValorSaida(rs.getDouble(4));
                    saidas.add(exit);
                } while (rs.next());
                return saidas;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma Saida registrada nessa data", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return saidas;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public double calculaTotalSaidas(ArrayList<Saidas> saidas){
        double exits = 0.0;
        for (Saidas saida1 : saidas) {
            exits = exits + saida1.getValorSaida();
        }
        return exits;
    }
    
    public ArrayList listaSaidasDatas(String dataInicio, String dataFim) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.saida` WHERE `dataSaida` BETWEEN ? AND ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataInicio);
            pst.setString(2, dataFim);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Saidas exit = new Saidas();
                    exit.setIdSaida(rs.getInt(1));
                    exit.setNaturezaSaida(rs.getString(2));
                    exit.setDataSaida(rs.getString(3));
                    exit.setValorSaida(rs.getDouble(4));
                    saidas.add(exit);
                } while (rs.next());
                return saidas;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma Saida registrada nessa data", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return saidas;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}
