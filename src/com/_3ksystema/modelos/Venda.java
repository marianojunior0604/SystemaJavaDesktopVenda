/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.modelos;

import java.util.ArrayList;

/**
 *
 * @author Mariano Junior
 */
public class Venda {
    
    private int idVenda;
    private int qtdParcelas;
    private int cliente;
    private ArrayList<ProdutosVenda> produtos = new ArrayList();
    private double valorVenda;
    private double valorEntrada;
    private String dataVenda;
    private String tipoVenda;
    private String dataPgtoParcela;

    public Venda() {
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ProdutosVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ProdutosVenda> produtos) {
        this.produtos = produtos;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public String getDataPgtoParcela() {
        return dataPgtoParcela;
    }

    public void setDataPgtoParcela(String dataPgtoParcela) {
        this.dataPgtoParcela = dataPgtoParcela;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.idVenda;
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
        final Venda other = (Venda) obj;
        if (this.idVenda != other.idVenda) {
            return false;
        }
        return true;
    }
}
