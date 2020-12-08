/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class FuncoesEmpresa {
    
    private Empresa empresa = new Empresa();
    private ModuloConector mc = new ModuloConector();
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public void cadastraEmpresa(Empresa empresa) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO `dba.empresa`(`nomeEmpresa`, `cidadeEmpresa`, `ruaEmpresa`, `numEmpresa`, `bairroEmpresa`, `estadoEmpresa`, `cnpjEmpresa`, `cadEstEmpresa`, `diretorEmpresa`, `rgDirEmpresa`, `cpfDirEmpresa`, `foneDirEmpresa`, `emailEmpresa`, `logoEmpresa`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, empresa.getNomeEmpresa());
            pst.setString(2, empresa.getCidadeEmpresa());
            pst.setString(3, empresa.getRuaEmpresa());
            pst.setInt(4, empresa.getNumEmpresa());
            pst.setString(5, empresa.getBairroEmpresa());
            pst.setString(6, empresa.getEstadoEmpresa());
            pst.setString(7, empresa.getCnpjEmpresa());
            pst.setString(8, empresa.getIncrEmpresa());
            pst.setString(9, empresa.getDirEmpresa());
            pst.setString(10, empresa.getRgDirEmpresa());
            pst.setString(11, empresa.getCpfDirEmpresa());
            pst.setString(12, empresa.getFoneEmpresa());
            pst.setString(13, empresa.getEmailEmpresa());
            pst.setString(14, empresa.getLogoEmpresa());
            //System.out.println(pst);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso", "Aviso", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Empresa pesquisaEmpresa() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM `dba.empresa` WHERE `idEmpresa` = 1";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                empresa.setIdEmpresa(rs.getInt(1));
                empresa.setNomeEmpresa(rs.getString(2));
                empresa.setCidadeEmpresa(rs.getString(3));
                empresa.setRuaEmpresa(rs.getString(4));
                empresa.setNumEmpresa(rs.getInt(5));
                empresa.setBairroEmpresa(rs.getString(6));
                empresa.setEstadoEmpresa(rs.getString(7));
                empresa.setCnpjEmpresa(rs.getString(8));
                empresa.setIncrEmpresa(rs.getString(9));
                empresa.setDirEmpresa(rs.getString(10));
                empresa.setRgDirEmpresa(rs.getString(11));
                empresa.setCpfDirEmpresa(rs.getString(12));
                empresa.setFoneEmpresa(rs.getString(13));
                empresa.setEmailEmpresa(rs.getString(14));
                empresa.setLogoEmpresa(rs.getString(15));
            }
            return empresa;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void atualizaEmpresa(Empresa empresa) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE `dba.empresa` SET `nomeEmpresa` = ?, `cidadeEmpresa` = ?, "
                + "`ruaEmpresa` = ?, `numEmpresa` = ?, `bairroEmpresa` = ?, "
                + "`estadoEmpresa` = ?, `cnpjEmpresa` = ?, `cadEstEmpresa` = ?, "
                + "`diretorEmpresa` = ?, `rgDirEmpresa` = ?, `cpfDirEmpresa` = ?, "
                + "`foneDirEmpresa` = ?, `emailEmpresa` = ?, `logoEmpresa` = ? WHERE `idEmpresa` = 1";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, empresa.getNomeEmpresa());
            pst.setString(2, empresa.getCidadeEmpresa());
            pst.setString(3, empresa.getRuaEmpresa());
            pst.setInt(4, empresa.getNumEmpresa());
            pst.setString(5, empresa.getBairroEmpresa());
            pst.setString(6, empresa.getEstadoEmpresa());
            pst.setString(7, empresa.getCnpjEmpresa());
            pst.setString(8, empresa.getIncrEmpresa());
            pst.setString(9, empresa.getDirEmpresa());
            pst.setString(10, empresa.getRgDirEmpresa());
            pst.setString(11, empresa.getCpfDirEmpresa());
            pst.setString(12, empresa.getFoneEmpresa());
            pst.setString(13, empresa.getEmailEmpresa());
            pst.setString(14, empresa.getLogoEmpresa());
            pst.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
}
