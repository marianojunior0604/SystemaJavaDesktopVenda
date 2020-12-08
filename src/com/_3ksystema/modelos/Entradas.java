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
public class Entradas {
    
    private int idEntrada;
    private String naturezaEntrada;
    private String dataEntrada;
    private double valorEntrada;

    public Entradas() {
    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getNaturezaEntrada() {
        return naturezaEntrada;
    }

    public void setNaturezaEntrada(String naturezaEntrada) {
        this.naturezaEntrada = naturezaEntrada;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idEntrada;
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
        final Entradas other = (Entradas) obj;
        if (this.idEntrada != other.idEntrada) {
            return false;
        }
        return true;
    }
}
