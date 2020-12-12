/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.ContasPagar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Mariano Junior
 */
public class FuncoesContaPagar {
    
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ModuloConector mc = new ModuloConector();
    private ContasPagar contaPagar = new ContasPagar();
    private ArrayList<ContasPagar> contasPagar = new ArrayList();
    
    private void cadastraContaPagar(ContasPagar contPag){
        String sql = "";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            
        } catch (Exception e) {
        }
    }
    
    private ContasPagar pesquisaContaPagar (String desc){
        String sql = "";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return contaPagar;
        } catch (Exception e) {
            return null;
        }
    }
    
    private ArrayList<ContasPagar> listaContasPagar(){
        String sql = "";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return contasPagar;
        } catch (Exception e) {
            return null;
        }
    }
}
