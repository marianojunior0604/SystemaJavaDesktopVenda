/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.Cliente;
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
public class FuncoesCliente {

    private ModuloConector mc = new ModuloConector();
    private Cliente cliente = new Cliente();
    private ArrayList<Cliente> clientes = new ArrayList();
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;

    public Cliente pesquisaCliente(String nomeCliente) throws ClassNotFoundException {
        String sql = "SELECT * FROM `dba.clientes` WHERE `nome_cliente` = ?";
        //System.out.println("O nome do Cliente é: " + nomeCliente);
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeCliente);
            //System.out.println(pst);
            rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId_Cliente(rs.getInt(1));
                cliente.setNome_Cliente(rs.getString(2));
                cliente.setApelido_Cliente(rs.getString(3));
                cliente.setData_Nascimento_Cliente(rs.getString(4));
                cliente.setNumero_Casa_Cliente(rs.getString(5));
                cliente.setRua_Casa_Cliente(rs.getString(6));
                cliente.setBairro_Casa_Cliente(rs.getString(7));
                cliente.setCidade_Casa_Cliente(rs.getString(8));
                cliente.setEstado_Casa_Cliente(rs.getString(9));
                cliente.setNome_Mae_Cliente(rs.getString(10));
                cliente.setNome_Pai_Cliente(rs.getString(11));
                cliente.setFone_Cliente(rs.getString(12));
                cliente.setProfissao_Cliente(rs.getString(13));
                cliente.setLocal_Trabalho_Cliente(rs.getString(14));
                cliente.setEstado_Civil_Cliente(rs.getString(15));
                cliente.setNome_Conjuje_Cliente(rs.getString(16));
                cliente.setRg_Cliente(rs.getString(17));
                cliente.setOrg_Emissor_RG_Cliente(rs.getString(18));
                cliente.setEstado_Emissor_RG_Cliente(rs.getString(19));
                cliente.setCpf_Cliente(rs.getString(20));
                cliente.setRenda_Cliente(rs.getFloat(21));
                //System.out.println("Nome: "+cliente.getNome_Cliente() + "\nApelido: " + cliente.getApelido_Cliente() + "\nFone: " + cliente.getFone_Cliente() + "\nData de Nascimento: " + cliente.getData_Nascimento_Cliente() + "\nNome da mãe: " + cliente.getNome_Mae_Cliente() + "\nNome do pai: " + cliente.getNome_Pai_Cliente() + "\nEstado Civil" + cliente.getEstado_Civil_Cliente() + "\nRua: " + cliente.getRua_Casa_Cliente() + "\nNumero da Casa: " + cliente.getNumero_Casa_Cliente() + "\nCidade: " + cliente.getCidade_Casa_Cliente() + "\nEstado: " + cliente.getEstado_Casa_Cliente() + "\nRG: " + cliente.getRg_Cliente() + "\nOrg. Emissor RG: " + cliente.getOrg_Emissor_RG_Cliente() + "\nEstado Emissor RG: " + cliente.getEstado_Emissor_RG_Cliente() + "\nNome Conjuge: " + cliente.getNome_Conjuje_Cliente() + "\nProfissão: " + cliente.getProfissao_Cliente() + "\nLocal de Trabalho: " + cliente.getLocal_Trabalho_Cliente() + "\nCPF: " + cliente.getCpf_Cliente() + "\nRenda Mensal: " + cliente.getRenda_Cliente());
                return cliente;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado, verifique o nome e tente novamente", "Aviso", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e + "\nFalha ao acessar o banco de dados", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public void excluirCliente(int idCliente) throws ClassNotFoundException {
        String sql = "DELETE FROM `dba.clientes` WHERE `id_cliente` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idCliente);
            boolean excluiu = pst.execute();
            if (excluiu) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o Cliente", "Alerta", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Aviso", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void atualizaCliente(Cliente client) throws ClassNotFoundException {
        String sql = "UPDATE `dba.clientes` SET `nome_cliente` = ?, `apelido_cliente` = ?, "
                + "`data_nascimento_cliente` = ?, `numero_casa_cliente` = ?, "
                + "`rua_casa_cliente` = ?, `bairro_casa_cliente` = ?, "
                + "`cidade_casa_cliente` = ?, `estado_Casa_Cliente` = ?, "
                + "`nome_mae_cliente` = ?, `nome_pai_cliente` = ?, `fone_cliente` = ?, "
                + "`profissao_cliente` = ?, `local_trabalho_cliente` = ?, "
                + "`estado_civil_cliente` = ?, `nome_conjuje_cliente` = ?, `rg_cliente` = ?, "
                + "`org_emissor_rg_cliente` = ?, `estado_emisso_rg_cliente` = ?, "
                + "`cpf_cliente` = ?, `renda_cliente` = ? WHERE `id_cliente` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, client.getNome_Cliente());
            pst.setString(2, client.getApelido_Cliente());
            pst.setString(3, client.getData_Nascimento_Cliente());
            pst.setString(4, client.getNumero_Casa_Cliente());
            pst.setString(5, client.getRua_Casa_Cliente());
            pst.setString(6, client.getBairro_Casa_Cliente());
            pst.setString(7, client.getCidade_Casa_Cliente());
            pst.setString(8, client.getEstado_Casa_Cliente());
            pst.setString(9, client.getNome_Mae_Cliente());
            pst.setString(10, client.getNome_Pai_Cliente());
            pst.setString(11, client.getFone_Cliente());
            pst.setString(12, client.getProfissao_Cliente());
            pst.setString(13, client.getLocal_Trabalho_Cliente());
            pst.setString(14, client.getEstado_Civil_Cliente());
            pst.setString(15, client.getNome_Conjuje_Cliente());
            pst.setString(16, client.getRg_Cliente());
            pst.setString(17, client.getOrg_Emissor_RG_Cliente());
            pst.setString(18, client.getEstado_Emissor_RG_Cliente());
            pst.setString(19, client.getCpf_Cliente());
            pst.setFloat(20, client.getRenda_Cliente());
            pst.setInt(21, client.getId_Cliente());
            System.out.println(pst);
            int alterou = pst.executeUpdate();
            if (alterou == 1) {
                JOptionPane.showMessageDialog(null, "Cliente modificado com sucesso", "Alerta", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao modificar os dados do cliente", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void cadastraCliente(Cliente client) throws ClassNotFoundException {
        String sql = "INSERT INTO `dba.clientes`(`nome_cliente`, `apelido_cliente`, `data_nascimento_cliente`, `numero_casa_cliente`, `rua_casa_cliente`, `bairro_casa_cliente`, `cidade_casa_cliente`, `estado_Casa_Cliente`, `nome_mae_cliente`, `nome_pai_cliente`, `fone_cliente`, `profissao_cliente`, `local_trabalho_cliente`, `estado_civil_cliente`, `nome_conjuje_cliente`, `rg_cliente`, `org_emissor_rg_cliente`, `estado_emisso_rg_cliente`, `cpf_cliente`, `renda_cliente`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //System.out.println(client.getNome_Cliente() + "\n" + client.getApelido_Cliente() + "\n" + client.getFone_Cliente() + "\n" + client.getData_Nascimento_Cliente() + "\n" + client.getNome_Mae_Cliente() + "\n" + client.getNome_Pai_Cliente() + "\n" + client.getEstado_Civil_Cliente() + "\n" + client.getRua_Casa_Cliente() + "\n" + client.getNumero_Casa_Cliente() + "\n" + client.getCidade_Casa_Cliente() + "\n" + client.getEstado_Casa_Cliente() + "\n" + client.getRg_Cliente() + "\n" + client.getOrg_Emissor_RG_Cliente() + "\n" + client.getEstado_Emissor_RG_Cliente() + "\n" + client.getNome_Conjuje_Cliente() + "\n" + client.getProfissao_Cliente() + "\n" + client.getLocal_Trabalho_Cliente() + "\n" + client.getCpf_Cliente() + "\n" + client.getRenda_Cliente());
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, client.getNome_Cliente());
            pst.setString(2, client.getApelido_Cliente());
            pst.setString(3, client.getData_Nascimento_Cliente());
            pst.setString(4, client.getNumero_Casa_Cliente());
            pst.setString(5, client.getRua_Casa_Cliente());
            pst.setString(6, client.getBairro_Casa_Cliente());
            pst.setString(7, client.getCidade_Casa_Cliente());
            pst.setString(8, client.getEstado_Casa_Cliente());
            pst.setString(9, client.getNome_Mae_Cliente());
            pst.setString(10, client.getNome_Pai_Cliente());
            pst.setString(11, client.getFone_Cliente());
            pst.setString(12, client.getProfissao_Cliente());
            pst.setString(13, client.getLocal_Trabalho_Cliente());
            pst.setString(14, client.getEstado_Civil_Cliente());
            pst.setString(15, client.getNome_Conjuje_Cliente());
            pst.setString(16, client.getRg_Cliente());
            pst.setString(17, client.getOrg_Emissor_RG_Cliente());
            pst.setString(18, client.getEstado_Emissor_RG_Cliente());
            pst.setString(19, client.getCpf_Cliente());
            pst.setFloat(20, client.getRenda_Cliente());
            /*System.out.println(pst);
            System.out.println(sql);*/
            boolean salvou = pst.execute();
            //System.out.println(salvou);
            if (salvou) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cliente", "Alerta", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso", "Aviso", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
            //System.out.println(e);
        }
    }

    public ResultSet listaClientes() throws ClassNotFoundException {
        String sql = "SELECT `nome_cliente` AS Nome, `apelido_cliente` AS Apelido, `fone_cliente` as Telefone, `cpf_cliente` AS CPF FROM `dba.clientes` ORDER BY `nome_cliente` ASC";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return pst.executeQuery();
            //return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public ResultSet pesquisaAvancada(String nomeCliente) throws ClassNotFoundException {
        String sql = "SELECT `nome_cliente` AS Nome, `apelido_cliente` AS Apelido, `fone_cliente` as Telefone, `cpf_cliente` AS CPF FROM `dba.clientes` WHERE `nome_cliente` LIKE ? ORDER BY `nome_cliente` ASC";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + nomeCliente + "%");
            //System.out.println(pst);
            return pst.executeQuery();
            /*if (rs.next()) {
                do {
                    Cliente clie = new Cliente();
                    clie.setNome_Cliente(rs.getString(2));
                    clie.setApelido_Cliente(rs.getString(3));
                    clie.setFone_Cliente(rs.getString(12));
                    clie.setCpf_Cliente(rs.getString(20));
                    clientes.add(clie);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }

            return clientes;*/
        } catch (SQLException e) {
            //System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ResultSet pesquisaClienteVenda(String nomeCliente) throws ClassNotFoundException {
        String sql = "SELECT `nome_cliente` AS Nome FROM `dba.clientes` WHERE `nome_cliente` LIKE ? ORDER BY `nome_cliente` ASC";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + nomeCliente + "%");
            //System.out.println(pst);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public ResultSet listaNomesClientes() throws ClassNotFoundException {
        String sql = "SELECT `nome_cliente` AS Nome FROM `dba.clientes` ORDER BY `nome_cliente` ASC";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return pst.executeQuery();
            //return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public Cliente pesquisaClienteCodigo(int idCli) throws ClassNotFoundException{
        String sql = "SELECT * FROM `dba.clientes` WHERE `id_cliente` = ?";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idCli);
            rs = pst.executeQuery();
            if (rs.next()) {
                cliente.setId_Cliente(rs.getInt(1));
                cliente.setNome_Cliente(rs.getString(2));
                cliente.setApelido_Cliente(rs.getString(3));
                cliente.setData_Nascimento_Cliente(rs.getString(4));
                cliente.setNumero_Casa_Cliente(rs.getString(5));
                cliente.setRua_Casa_Cliente(rs.getString(6));
                cliente.setBairro_Casa_Cliente(rs.getString(7));
                cliente.setCidade_Casa_Cliente(rs.getString(8));
                cliente.setEstado_Casa_Cliente(rs.getString(9));
                cliente.setNome_Mae_Cliente(rs.getString(10));
                cliente.setNome_Pai_Cliente(rs.getString(11));
                cliente.setFone_Cliente(rs.getString(12));
                cliente.setProfissao_Cliente(rs.getString(13));
                cliente.setLocal_Trabalho_Cliente(rs.getString(14));
                cliente.setEstado_Civil_Cliente(rs.getString(15));
                cliente.setNome_Conjuje_Cliente(rs.getString(16));
                cliente.setRg_Cliente(rs.getString(17));
                cliente.setOrg_Emissor_RG_Cliente(rs.getString(18));
                cliente.setEstado_Emissor_RG_Cliente(rs.getString(19));
                cliente.setCpf_Cliente(rs.getString(20));
                cliente.setRenda_Cliente(rs.getFloat(21));
            }
            return cliente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e + "\nFalha ao acessar o banco de dados", "Erro", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}
