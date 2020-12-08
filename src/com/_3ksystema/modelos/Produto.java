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
public class Produto {
    
    private int idProduto;
    private String nomeProduto;
    private String marcaProtudo;
    private String modeloProtudo;
    private String caracteristicaProduto;
    private int qtdEstoqueProduto;
    private double valorCompraProduto;
    private double valorVendaProduto;
    private double percentualLucro;

    public Produto() {
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getMarcaProtudo() {
        return marcaProtudo;
    }

    public void setMarcaProtudo(String marcaProtudo) {
        this.marcaProtudo = marcaProtudo;
    }

    public String getModeloProtudo() {
        return modeloProtudo;
    }

    public void setModeloProtudo(String modeloProtudo) {
        this.modeloProtudo = modeloProtudo;
    }

    public String getCaracteristicaProduto() {
        return caracteristicaProduto;
    }

    public void setCaracteristicaProduto(String caracteristicaProduto) {
        this.caracteristicaProduto = caracteristicaProduto;
    }

    public int getQtdEstoqueProduto() {
        return qtdEstoqueProduto;
    }

    public void setQtdEstoqueProduto(int qtdEstoqueProduto) {
        this.qtdEstoqueProduto = qtdEstoqueProduto;
    }

    public double getValorCompraProduto() {
        return valorCompraProduto;
    }

    public void setValorCompraProduto(double valorCompraProduto) {
        this.valorCompraProduto = valorCompraProduto;
    }

    public double getValorVendaProduto() {
        return valorVendaProduto;
    }

    public void setValorVendaProduto(double valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
    }

    public double getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(double percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idProduto;
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
        final Produto other = (Produto) obj;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }
    
    
    
}
