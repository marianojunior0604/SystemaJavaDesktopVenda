/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import com._3ksystema.funcoes.FuncoesProduto;
import com._3ksystema.modelos.RelVendaPer;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mariano Junior
 */
public class RelatorioVendaPeriodoTableModel extends AbstractTableModel{
    private String[] colunas = {"Nome do Cliente", "Data da Venda", "Valor da Venda"};
    private ArrayList<RelVendaPer> linhas = new ArrayList();
    private FuncoesProduto fp = new FuncoesProduto();

    public RelatorioVendaPeriodoTableModel() {
    }

    public RelatorioVendaPeriodoTableModel(ArrayList<RelVendaPer> dadosVenda){
        this.linhas = dadosVenda;
    }
    
    /*public String[] getColunas() {
        return colunas;
    }*/

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public ArrayList<RelVendaPer> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<RelVendaPer> linhas) {
        this.linhas = linhas;
    }
    
    public void limpaLinhas(){
        this.linhas = null;
    }
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int indice) {
        return colunas[indice];
    }

    @Override
    public Object getValueAt(int rowIndex, int colunIndex) {
        RelVendaPer dadosVenda = this.linhas.get(rowIndex);
        switch(colunIndex){
            case 0:
                return dadosVenda.getNomeCliente();
            case 1:
                return dadosVenda.getDataVenda();
            case 2:
                return dadosVenda.getValorVenda();
        }
        return null;
    }
    
    public void addProduto(RelVendaPer rvp){
        this.linhas.add(rvp);
        this.fireTableDataChanged();
    }
    
    public void removerProduto(int linha){
        this.linhas.remove(linha);
        this.fireTableDataChanged();
    }
}
