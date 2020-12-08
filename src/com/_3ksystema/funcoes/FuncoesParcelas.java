/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Parcelas;
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
public class FuncoesParcelas {

    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ModuloConector mc = new ModuloConector();
    private Parcelas parcela = new Parcelas();
    private ArrayList<Parcelas> parcelas = new ArrayList();

    public void salvaParcelas(ArrayList<Parcelas> parcelas) {
        String sql = "UPDATE `dba.parcelas` SET `percento` = ? WHERE `idParcela` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            for (Parcelas parcela1 : parcelas) {
                pst.setDouble(1, parcela1.getPercento());
                pst.setInt(2, parcela1.getIdParcela());
                pst.execute();
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ArrayList<Parcelas> pesquisaParcelas(){
        String sql = "SELECT * FROM `dba.parcelas`";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                do{
                    Parcelas parc = new Parcelas();
                    parc.setIdParcela(rs.getInt(1));
                    parc.setPercento(rs.getDouble(2));
                    parcelas.add(parc);
                }while(rs.next());
            }
            return parcelas;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public Parcelas buscarPercentual(int idParcela){
        String sql = "SELECT * FROM `dba.parcelas` WHERE `idParcela` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idParcela);
            rs = pst.executeQuery();
            if (rs.next()) {
                parcela.setIdParcela(rs.getInt("idParcela"));
                parcela.setPercento(rs.getDouble("percento"));
            }
            return parcela;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "Falha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
}
