/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Usuario;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class FuncoesUsuario {
    
    private ModuloConector mc = new ModuloConector();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private Usuario usuario = new Usuario();
    
    public Usuario loginUsuario(String login, String senha) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.usuarios` WHERE `login_usuario` = ? AND `senha_usuario`  = ?";
        
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId_Usuario(rs.getInt("id_usuario"));
                usu.setNome_Usuario(rs.getString("nome_usuario"));
                usu.setPerfil_Usuario(rs.getString("perfil_usuario"));
                usu.setLogin_Usuario(rs.getString("login_usuario"));
                usu.setSenha_Usuario(rs.getString("senha_usuario"));
                return usu;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não encontrado. \n"
                        + "Por favor verifique se o login e a senha estão corretos.", "Aviso", 1);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + e + "\nFalha ao conectar ao banco de dados", "Alerta", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void cadastraUsuario(Usuario usuario){
        String sql = "INSERT INTO `dba.usuarios`(`nome_usuario`, `perfil_usuario`, `login_usuario`, `senha_usuario`) VALUES (?, ?, ?, ?)";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario.getNome_Usuario());
            pst.setString(2, usuario.getLogin_Usuario());
            pst.setString(3, usuario.getSenha_Usuario());
            pst.setString(4, usuario.getPerfil_Usuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuario Cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + e + "\nFalha ao conectar ao banco de dados", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Usuario pesquisaUsuario(String nomeUsuario) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.usuarios` WHERE `nome_usuario` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeUsuario);
            rs = pst.executeQuery();
            if (rs.next()) {
                usuario.setId_Usuario(rs.getInt(1));
                usuario.setNome_Usuario(rs.getString(2));
                usuario.setPerfil_Usuario(rs.getString(3));
                usuario.setLogin_Usuario(rs.getString(4));
                usuario.setSenha_Usuario(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum usuario com o nome: " + nomeUsuario + " foi encontrado,\n verifique se o nome esta correto e tente novamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            return usuario;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + e + "\nFalha ao conectar ao banco de dados", "Alerta", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void excluiUsuario(int idUsuario) throws ClassNotFoundException{
        String sql = "DELETE FROM `dba.usuarios` WHERE `id_usuario` = ?";
        usuario = pesquisaUsuarioCodigo(idUsuario);
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, usuario.getId_Usuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuario Excluido com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + e + "\nFalha ao conectar ao banco de dados", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    public void atualizaUsuario(Usuario user) throws ClassNotFoundException{
        String sql = "UPDATE `dba.usuarios` SET `nome_usuario` = ?, `perfil_usuario` = ?, `login_usuario` = ?, `senha_usuario` = ? WHERE `id_usuario` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, user.getNome_Usuario());
            pst.setString(2, user.getPerfil_Usuario());
            pst.setString(3, user.getLogin_Usuario());
            pst.setString(4, user.getSenha_Usuario());
            pst.setInt(5, user.getId_Usuario());
            boolean atualizou = pst.execute();
            if (atualizou) {
                JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar o Usuario", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + e + "\nFalha ao conectar ao banco de dados", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        public Usuario pesquisaUsuarioCodigo(int idUsuario) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.usuarios` WHERE `id_usuario` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            if (rs.next()) {
                usuario.setId_Usuario(rs.getInt(1));
                usuario.setNome_Usuario(rs.getString(2));
                usuario.setPerfil_Usuario(rs.getString(3));
                usuario.setLogin_Usuario(rs.getString(4));
                usuario.setSenha_Usuario(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum usuario foi encontrado,\n verifique se o nome esta correto e tente novamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            return usuario;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + e + "\nFalha ao conectar ao banco de dados", "Alerta", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
