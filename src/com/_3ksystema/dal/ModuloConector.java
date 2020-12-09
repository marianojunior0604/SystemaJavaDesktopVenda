/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class ModuloConector {

    private Path endeConf = Paths.get("C:/_3ksystemas/config/Config.txt"); //Endereço dos dados de configurações do banco de dados
    private String endereco = CapturaEndeConfi();
    private String nomebanc = CapturaEndeConfi();
    private String userbanc = CapturaEndeConfi();
    private String passbanc = CapturaEndeConfi();
    private String sqlBin = CapturaEndeConfi();
    private String camSQL = sqlBin + "SqlBin.txt";
    private String camBanco = endereco + "Ender.txt";
    private String nomBanco = nomebanc + "Banco.txt";
    private String usuBanco = userbanc + "User.txt";
    private String senBanco = passbanc + "Senha.txt";
    private Path cmnBanco = Paths.get(nomBanco);
    private Path cmnUsers = Paths.get(usuBanco);
    private Path cmnSenha = Paths.get(senBanco);
    private Path cmnEnder = Paths.get(camBanco);
    private Path cmnBinSQL = Paths.get(camSQL);
    private boolean sucesso = false;
    private String result;
    private Process processo;
    private BufferedReader input;
    private StringBuffer cmdOut = new StringBuffer();
    private int numberOfoutline = 0;

    private String CapturaEndeConfi() {
        try {
            byte[] endeConfi = Files.readAllBytes(endeConf);
            return new String(endeConfi);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arguivo" + e);
            return "Erro ao encontrar o arquivo " + e;
        }
    }

    private String CapturaNomeBanco() {
        try {
            byte[] nomedba = Files.readAllBytes(cmnBanco);
            return new String(nomedba);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arguivo" + e);
            return "Erro ao encontrar o arguivo " + e;
        }
    }

    private String CapturaUserBanco() {
        try {
            byte[] nomedba = Files.readAllBytes(cmnUsers);
            return new String(nomedba);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arguivo" + e);
            return "Erro ao encontrar o arguivo " + e;
        }
    }

    private String CapturaSenhaBanco() {
        try {
            byte[] nomedba = Files.readAllBytes(cmnSenha);
            return new String(nomedba);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arguivo" + e);
            return "Erro ao encontrar o arguivo " + e;
        }
    }

    private String CapturaEndeBanco() {
        try {
            byte[] nomedba = Files.readAllBytes(cmnEnder);
            return new String(nomedba);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arguivo" + e);
            return "Erro ao encontrar o arguivo " + e;
        }
    }

    private String capturaBinSQL() {
        try {
            byte[] nomedba = Files.readAllBytes(cmnBinSQL);
            return new String(nomedba);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arguivo" + e);
            return "Erro ao encontrar o arguivo " + e;
        }
    }

    public Connection conector() throws ClassNotFoundException {

        java.sql.Connection conexao = null;

        String nomeBanco = CapturaNomeBanco();
        String nomeUsers = CapturaUserBanco();
        String senhaUser = CapturaSenhaBanco();
        String enderedba = CapturaEndeBanco();

        String driver = "com.mysql.jdbc.Driver";
        //String url = "jdbc:mysql://"+ enderedba + "/" + nomeBanco;
        String url = "jdbc:mysql://" + enderedba + "/" + nomeBanco + "?autoReconnect=true&useSSL=false";
        //System.out.println(url);
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, nomeUsers, senhaUser);
            //System.out.println(conexao);
            return conexao;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void fazBackup(String enderecoSalvar) throws IOException, InterruptedException {
        String mysqlBin = "cd " + capturaBinSQL() + "\\";
        String comando = "mysqldump.exe -u " + CapturaUserBanco() + " -p " + CapturaNomeBanco() + " > " + enderecoSalvar + "\n";
        //System.out.println("Comando do diretorio: " + mysqlBin + "\nComando para fazer backup: " + comando);
        String fullcomand = mysqlBin + "\n" + comando;
        System.out.println(fullcomand);
        try {
            processo = Runtime.getRuntime().exec(new String[] { "cmd.exe", comando });
            input = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            while((comando = input.readLine()) != null){
                if (numberOfoutline > 0) {
                    cmdOut.append("\n");
                }
                cmdOut.append(comando);
                numberOfoutline++;
            }
            result = cmdOut.toString();
            sucesso = true;
            input.close();
        } catch (IOException e) {
            result = String.format("Falha ao executar o comando %s. Erro %s", comando, e.toString());
        }
        //if (!sucesso) {
            new IOException(result);
        //}
        JOptionPane.showMessageDialog(null, "Banco de dados Salvo com sucesso", "OK", 1);
        /*
        try {
            Process processo = Runtime.getRuntime().exec(fullcomand);
            StringBuilder saida = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            while((linha = reader.readLine()) != null){
                saida.append(linha).append("\n");
            }
            int saidavalor = processo.waitFor();
            if (saidavalor == 0) {
                JOptionPane.showMessageDialog(null, "banco de dados salvo com sucesso", "comando executado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao salvar o banco de dados", "comando não executado", JOptionPane.INFORMATION_MESSAGE);
            }
            /*
            processo = Runtime.getRuntime().exec(fullcomand);
            input = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            while((comando = input.readLine()) != null){
                if (numberOfoutline > 0) {
                    cmdOut.append("\n");
                }
                cmdOut.append(comando);
                numberOfoutline++;
            }
            result = cmdOut.toString();
            sucesso = true;
            input.close();
            IOException ioException = new IOException(result);
            JOptionPane.showMessageDialog(null, "banco de dados salvo com sucesso", "comando executado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            //result = String.format("Falha ao executar o comando %s. Erro %s", comando, e.toString());
        }
        if (!sucesso) {
            
        }
        */

    }
}
