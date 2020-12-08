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
public class Saidas {
    
    private int idSaida;
    private String naturezaSaida;
    private String dataSaida;
    private double valorSaida;

    public Saidas() {
    }

    public int getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(int idSaida) {
        this.idSaida = idSaida;
    }

    public String getNaturezaSaida() {
        return naturezaSaida;
    }

    public void setNaturezaSaida(String naturezaSaida) {
        this.naturezaSaida = naturezaSaida;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValorSaida() {
        return valorSaida;
    }

    public void setValorSaida(double valorSaida) {
        this.valorSaida = valorSaida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idSaida;
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
        final Saidas other = (Saidas) obj;
        if (this.idSaida != other.idSaida) {
            return false;
        }
        return true;
    }
}
