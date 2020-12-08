/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import com._3ksystema.funcoes.FuncoesProduto;
import com._3ksystema.modelos.ProdutoListaVenda;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mariano Junior
 */
public class ProdutosVendaTableModel extends AbstractTableModel{

    private String[] colunas = {"Produto", "Preço Unitário", "Quantidade", "Preço Total"};
    private ArrayList<ProdutoListaVenda> linhas = new ArrayList();
    private FuncoesProduto fp = new FuncoesProduto();

    public ProdutosVendaTableModel() {
    }

    public ProdutosVendaTableModel(ArrayList<ProdutoListaVenda> produtos){
        this.linhas = produtos;
    }
    
    /*public String[] getColunas() {
        return colunas;
    }*/

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public ArrayList<ProdutoListaVenda> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<ProdutoListaVenda> linhas) {
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
        ProdutoListaVenda produto = this.linhas.get(rowIndex);
        switch(colunIndex){
            case 0:
                return produto.getNomeProduto();
            case 1:
                return produto.getPrecoProduto();
            case 2:
                return produto.getQtdProdutoComprado();
            case 3:
                return produto.getPrecoTotal();
        }
        return null;
    }
    
    public void addProduto(ProdutoListaVenda pv){
        this.linhas.add(pv);
        this.fireTableDataChanged();
    }
    
    public void removerProduto(int linha){
        this.linhas.remove(linha);
        this.fireTableDataChanged();
    }
}
