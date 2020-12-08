/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Caixa;
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
public class FuncoesCaixa {
    
    private Caixa cx = new Caixa();
    private ArrayList<Caixa> caixas = new ArrayList();
    private ModuloConector mc = new ModuloConector();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public void abrirCaixa(Caixa caixa) throws ClassNotFoundException{
        String sql = "INSERT INTO `dba.caixa`(`nomeCaixa`, `dataCaixa`, `valorAberturaCaixa`, `valorFechamentoCaixa`, `dba.saida_idSaida`, `dba.entrada_idEntrada`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, caixa.getNomeCaixa());
            pst.setString(2, caixa.getDataCaixa());
            pst.setDouble(3, caixa.getValorAbertura());
            pst.setDouble(4, caixa.getValorFechamento());
            pst.setDouble(5, 0.0);
            pst.setDouble(6, 0.0);
            boolean salvou = pst.execute();
            if (salvou) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o caixa", "Aviso", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Caixa Aberto", "Aviso", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Caixa pesquisaCaixa(String dataCaixa) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.caixa` WHERE `nomeCaixa` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataCaixa);
            rs = pst.executeQuery();
            if (rs.next()) {
                cx.setIdCodigoCaixa(rs.getInt(1));
                cx.setNomeCaixa(rs.getString(2));
                cx.setDataCaixa(rs.getString(3));
                cx.setValorAbertura(rs.getDouble(4));
                cx.setValorFechamento(rs.getDouble(5));
                return cx;
            } else {
                JOptionPane.showMessageDialog(null, "A data Inserida não tem nenhum caixa aberto, verifique a data e tente novamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ResultSet listaCaixas() throws ClassNotFoundException{
        String sql = "SELECT `nomeCaixa` AS Data, `valorAberturaCaixa` AS Abertura, `valorFechamentoCaixa` AS Fechamento FROM `dba.caixa`";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void atualizaCaixa(Caixa caixa) throws ClassNotFoundException{
        String sql = "UPDATE `dba.caixa` SET `nomeCaixa` = ?,`dataCaixa` = ?,`valorAberturaCaixa` = ?,`valorFechamentoCaixa` = ? WHERE `idCaixa` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, caixa.getNomeCaixa());
            pst.setString(2, caixa.getDataCaixa());
            pst.setDouble(3, caixa.getValorAbertura());
            pst.setDouble(4, caixa.getValorFechamento());
            pst.setInt(5, caixa.getIdCodigoCaixa());
            boolean atualizou = pst.execute();
            if (atualizou) {
                JOptionPane.showMessageDialog(null, "Erro ao Fechar caixa e (ou) atualizar dados", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Caixa pegaUltimoCaixa() throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.caixa` WHERE `idCaixa` ORDER BY `idCaixa` DESC LIMIT 1";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                cx.setIdCodigoCaixa(rs.getInt(1));
                cx.setNomeCaixa(rs.getString(2));
                cx.setDataCaixa(rs.getString(3));
                cx.setValorAbertura(rs.getDouble(4));
                cx.setValorFechamento(rs.getDouble(5));
                return cx;
            }else{
                JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ArrayList pesquisaCaixaPeriodo(String dataInicio, String dataFim) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.caixa` WHERE `dataCaixa` BETWEEN ? AND ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataInicio);
            pst.setString(2, dataFim);
            rs = pst.executeQuery();
            if (rs.next()) {
                do {                    
                    Caixa box = new Caixa();
                    box.setIdCodigoCaixa(0);
                    box.setNomeCaixa(dataFim);
                    box.setValorAbertura(0);
                    box.setValorFechamento(0);
                    caixas.add(box);
                } while (rs.next());
                return caixas;
            } else {
                JOptionPane.showMessageDialog(null, "Não há caixas registrados no periodo informado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public double somaCaixas(ArrayList<Caixa> caixas){
        double valorCaixas = 0.0;
        for (Caixa caixa : caixas) {
            valorCaixas = valorCaixas + caixa.getValorFechamento();
        }
        return valorCaixas;
    }
    
    public double somaAberturas(ArrayList<Caixa> caixas){
        double valorAbertura = 0.0;
        for (Caixa caixa : caixas) {
            valorAbertura = valorAbertura + caixa.getValorAbertura();
        }
        return valorAbertura;
    }
}
