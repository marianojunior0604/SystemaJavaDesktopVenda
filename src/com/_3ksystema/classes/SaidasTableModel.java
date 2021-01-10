/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import com._3ksystema.funcoes.FuncoesSaida;
import com._3ksystema.modelos.Saidas;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mariano Junior
 */
public class SaidasTableModel extends AbstractTableModel{
    private String[] colunas = {"Data", "Valor", "Motivo"};
    private ArrayList<Saidas> linhas = new ArrayList();
    private FuncoesSaida fs = new FuncoesSaida();

    public SaidasTableModel() {
    }

    public SaidasTableModel(ArrayList<Saidas> saidas){
        this.linhas = saidas;
    }
    
    /*public String[] getColunas() {
        return colunas;
    }*/

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public ArrayList<Saidas> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<Saidas> linhas) {
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
        Saidas saida = this.linhas.get(rowIndex);
        switch(colunIndex){
            case 0:
                return saida.getDataSaida();
            case 1:
                return saida.getValorSaida();
            case 2:
                return saida.getNaturezaSaida();
        }
        return null;
    }
    
    public void addProduto(Saidas saida){
        this.linhas.add(saida);
        this.fireTableDataChanged();
    }
    
    public void deletarVenda(int linha){
        this.linhas.remove(linha);
        this.fireTableDataChanged();
    }
}
