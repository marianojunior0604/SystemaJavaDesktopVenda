/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.funcoes;

import com._3ksystema.dal.ModuloConector;
import com._3ksystema.modelos.ContasReceber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Mariano Junior
 */
public class FuncoesContaReceber {
    
    private ModuloConector mc = new ModuloConector();
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ContasReceber contaReceber = new ContasReceber();
    private ArrayList<ContasReceber> contasReceber = new ArrayList();
    
    public void cadastraContaReceber(ContasReceber contRec){
        String sql = "";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
        } catch (Exception e) {
        }
    }
    
    public ContasReceber pesquisaContaReceber(String desk){
        String sql = "";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return contaReceber;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ArrayList<ContasReceber> listaContasReceber(){
        String sql = "";
        try {
            conexao = mc.conector();
            pst = conexao.prepareStatement(sql);
            return contasReceber;
        } catch (Exception e) {
            return null;
        }
    }
}
