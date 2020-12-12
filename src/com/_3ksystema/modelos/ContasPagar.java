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
public class ContasPagar {
    
    private int idContaPagar;
    private String dataContaPagar;
    private double valorContaPagar;
    private String descContaPagar;
    private boolean contaRecorrente;

    public ContasPagar() {
    }

    public int getIdContaPagar() {
        return idContaPagar;
    }

    public void setIdContaPagar(int idContaPagar) {
        this.idContaPagar = idContaPagar;
    }

    public String getDataContaPagar() {
        return dataContaPagar;
    }

    public void setDataContaPagar(String dataContaPagar) {
        this.dataContaPagar = dataContaPagar;
    }

    public double getValorContaPagar() {
        return valorContaPagar;
    }

    public void setValorContaPagar(double valorContaPagar) {
        this.valorContaPagar = valorContaPagar;
    }

    public String getDescContaPagar() {
        return descContaPagar;
    }

    public void setDescContaPagar(String descContaPagar) {
        this.descContaPagar = descContaPagar;
    }

    public boolean isContaRecorrente() {
        return contaRecorrente;
    }

    public void setContaRecorrente(boolean contaRecorrente) {
        this.contaRecorrente = contaRecorrente;
    }
}
