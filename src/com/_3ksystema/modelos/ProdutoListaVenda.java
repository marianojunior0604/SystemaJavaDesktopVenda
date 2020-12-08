/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.modelos;

/**
 *
 * @author Mariano Junior
 */
public class ProdutoListaVenda {
    
    String nomeProduto;
    double precoProduto;
    double precoTotal;
    int qtdProdutoComprado;

    public ProdutoListaVenda() {
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public void setPrecoTotal(double precoProduto, int qtdComprada){
        this.precoTotal = precoProduto * qtdComprada;
    }
    
    public double getPrecoTotal(){
        return precoTotal;
    }
    
    public int getQtdProdutoComprado() {
        return qtdProdutoComprado;
    }

    public void setQtdProdutoComprado(int qtdProdutoComprado) {
        this.qtdProdutoComprado = qtdProdutoComprado;
    }
}
