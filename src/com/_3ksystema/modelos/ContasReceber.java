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
public class ContasReceber {
    
    private int idContaReceber;
    private String dataContaReceber;
    private double valorContaReceber;
    private String descContaReceber;
    private boolean contaRecorrente;

    public ContasReceber() {
    }

    public int getIdContaReceber() {
        return idContaReceber;
    }

    public void setIdContaReceber(int idContaReceber) {
        this.idContaReceber = idContaReceber;
    }

    public String getDataContaReceber() {
        return dataContaReceber;
    }

    public void setDataContaReceber(String dataContaReceber) {
        this.dataContaReceber = dataContaReceber;
    }

    public double getValorContaReceber() {
        return valorContaReceber;
    }

    public void setValorContaReceber(double valorContaReceber) {
        this.valorContaReceber = valorContaReceber;
    }

    public String getDescContaReceber() {
        return descContaReceber;
    }

    public void setDescContaReceber(String descContaReceber) {
        this.descContaReceber = descContaReceber;
    }

    public boolean isContaRecorrente() {
        return contaRecorrente;
    }

    public void setContaRecorrente(boolean contaRecorrente) {
        this.contaRecorrente = contaRecorrente;
    }
}
