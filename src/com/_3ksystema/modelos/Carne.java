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
public class Carne {
    
    private int idCarne;
    private int idCompra;
    private int idCliente;
    private String dataVencimento;
    private double valorParcela;
    private int numeroParcela;
    private boolean parcelaPaga;
    private String dataPagamento;

    public Carne() {
    }

    public int getIdCarne() {
        return idCarne;
    }

    public void setIdCarne(int idCarne) {
        this.idCarne = idCarne;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public boolean isParcelaPaga() {
        return parcelaPaga;
    }

    public void setParcelaPaga(boolean parcelaPaga) {
        this.parcelaPaga = parcelaPaga;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
