/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import com._3ksystema.funcoes.FuncoesEntrada;
import com._3ksystema.modelos.Entradas;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mariano Junior
 */
public class EntradasTableModel extends AbstractTableModel{
    private String[] colunas = {"Data", "Valor", "Motivo"};
    private ArrayList<Entradas> linhas = new ArrayList();
    private FuncoesEntrada fe = new FuncoesEntrada();

    public EntradasTableModel() {
    }

    public EntradasTableModel(ArrayList<Entradas> entradas){
        this.linhas = entradas;
    }
    
    /*public String[] getColunas() {
        return colunas;
    }*/

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public ArrayList<Entradas> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<Entradas> linhas) {
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
        Entradas entrada = this.linhas.get(rowIndex);
        switch(colunIndex){
            case 0:
                return entrada.getDataEntrada();
            case 1:
                return entrada.getValorEntrada();
            case 2:
                return entrada.getNaturezaEntrada();
        }
        return null;
    }
    
    public void addProduto(Entradas entrada){
        this.linhas.add(entrada);
        this.fireTableDataChanged();
    }
    
    public void deletarVenda(int linha){
        this.linhas.remove(linha);
        this.fireTableDataChanged();
    }
}
