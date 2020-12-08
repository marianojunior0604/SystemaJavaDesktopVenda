/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import com._3ksystema.funcoes.FuncoesProduto;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mariano Junior
 */
public class VendasDeleteTableModel extends AbstractTableModel{
    private String[] colunas = {"Cliente"};
    private ArrayList<String> linhas = new ArrayList();
    private FuncoesProduto fp = new FuncoesProduto();

    public VendasDeleteTableModel() {
    }

    public VendasDeleteTableModel(ArrayList<String> produtos){
        this.linhas = produtos;
    }
    
    /*public String[] getColunas() {
        return colunas;
    }*/

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public ArrayList<String> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<String> linhas) {
        this.linhas = linhas;
        this.fireTableDataChanged();
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
        String cliente = this.linhas.get(rowIndex);
        switch(colunIndex){
            case 0:
                return cliente.toString();
        }
        return null;
    }
    
    public void addProduto(String pv){
        this.linhas.add(pv);
        this.fireTableDataChanged();
    }
    
    public void deletarVenda(int linha){
        this.linhas.remove(linha);
        this.fireTableDataChanged();
    }
}
