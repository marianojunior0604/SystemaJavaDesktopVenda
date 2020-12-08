/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.modelos;

/**
 * @author Mariano Junior
 */
public class ProdutosVenda {
    
    private int idListaProdutosVenda;
    private int idproduto;
    private int idVenda;
    private int qtdProdutoVenda;

    public ProdutosVenda() {
    }

    public int getIdListaProdutosVenda() {
        return idListaProdutosVenda;
    }

    public void setIdListaProdutosVenda(int idListaProdutosVenda) {
        this.idListaProdutosVenda = idListaProdutosVenda;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getQtdProdutoVenda() {
        return qtdProdutoVenda;
    }

    public void setQtdProdutoVenda(int qtdProdutoVenda) {
        this.qtdProdutoVenda = qtdProdutoVenda;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idListaProdutosVenda;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutosVenda other = (ProdutosVenda) obj;
        if (this.idListaProdutosVenda != other.idListaProdutosVenda) {
            return false;
        }
        return true;
    }
}
