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
public class Caixa {
    
    private int idCodigoCaixa;
    private String nomeCaixa;
    private String dataCaixa;
    private double valorAbertura;
    private double valorFechamento;

    public Caixa() {
    }

    public int getIdCodigoCaixa() {
        return idCodigoCaixa;
    }

    public void setIdCodigoCaixa(int idCodigoCaixa) {
        this.idCodigoCaixa = idCodigoCaixa;
    }

    public String getNomeCaixa() {
        return nomeCaixa;
    }

    public void setNomeCaixa(String nomeCaixa) {
        this.nomeCaixa = nomeCaixa;
    }

    public String getDataCaixa() {
        return dataCaixa;
    }

    public void setDataCaixa(String dataCaixa) {
        this.dataCaixa = dataCaixa;
    }

    public double getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(double valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public double getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(double valorFechamento) {
        this.valorFechamento = valorFechamento;
    }
}
