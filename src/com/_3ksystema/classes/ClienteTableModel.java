/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import com._3ksystema.funcoes.FuncoesCliente;
import com._3ksystema.modelos.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mariano Junior
 */
public class ClienteTableModel extends AbstractTableModel{

    //private ArrayList<Cliente> clientes;
    private String[] colunas = {"Nome"};
    private ArrayList<Cliente> linhas = new ArrayList<>();
    private FuncoesCliente fc = new FuncoesCliente();
    
    
    public ClienteTableModel() {
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public ArrayList<Cliente> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<Cliente> linhas) {
        this.linhas = linhas;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = this.linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getNome_Cliente();
        }
        return null;
    }
    
    public void addCliente(Cliente c){
        this.linhas.add(c);
        this.fireTableDataChanged();
    }
    
    public void removeCliente(int linha){
        this.linhas.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}
