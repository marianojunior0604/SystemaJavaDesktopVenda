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
public class Cliente {
    
    private int id_Cliente;
    private String nome_Cliente;
    private String apelido_Cliente;
    private String data_Nascimento_Cliente;
    private String numero_Casa_Cliente;
    private String rua_Casa_Cliente;
    private String bairro_Casa_Cliente;
    private String cidade_Casa_Cliente;
    private String estado_Casa_Cliente;
    private String nome_Mae_Cliente;
    private String nome_Pai_Cliente;
    private String fone_Cliente;
    private String profissao_Cliente;
    private String local_Trabalho_Cliente;
    private String estado_Civil_Cliente;
    private String nome_Conjuje_Cliente;
    private String rg_Cliente;
    private String org_Emissor_RG_Cliente;
    private String estado_Emissor_RG_Cliente;
    private String cpf_Cliente;
    private float renda_Cliente;

    public Cliente() {
    }

    public Cliente(int id_Cliente, String nome_Cliente, String apelido_Cliente, String data_Nascimento_Cliente, String numero_Casa_Cliente, String rua_Casa_Cliente, String bairro_Casa_Cliente, String cidade_Casa_Cliente, String estado_Casa_Cliente, String nome_Mae_Cliente, String nome_Pai_Cliente, String fone_Cliente, String profissao_Cliente, String local_Trabalho_Cliente, String estado_Civil_Cliente, String nome_Conjuje_Cliente, String rg_Cliente, String org_Emissor_RG_Cliente, String estado_Emissor_RG_Cliente, String cpf_Cliente, float renda_Cliente) {
        this.id_Cliente = id_Cliente;
        this.nome_Cliente = nome_Cliente;
        this.apelido_Cliente = apelido_Cliente;
        this.data_Nascimento_Cliente = data_Nascimento_Cliente;
        this.numero_Casa_Cliente = numero_Casa_Cliente;
        this.rua_Casa_Cliente = rua_Casa_Cliente;
        this.bairro_Casa_Cliente = bairro_Casa_Cliente;
        this.cidade_Casa_Cliente = cidade_Casa_Cliente;
        this.estado_Casa_Cliente = estado_Casa_Cliente;
        this.nome_Mae_Cliente = nome_Mae_Cliente;
        this.nome_Pai_Cliente = nome_Pai_Cliente;
        this.fone_Cliente = fone_Cliente;
        this.profissao_Cliente = profissao_Cliente;
        this.local_Trabalho_Cliente = local_Trabalho_Cliente;
        this.estado_Civil_Cliente = estado_Civil_Cliente;
        this.nome_Conjuje_Cliente = nome_Conjuje_Cliente;
        this.rg_Cliente = rg_Cliente;
        this.org_Emissor_RG_Cliente = org_Emissor_RG_Cliente;
        this.estado_Emissor_RG_Cliente = estado_Emissor_RG_Cliente;
        this.cpf_Cliente = cpf_Cliente;
        this.renda_Cliente = renda_Cliente;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getNome_Cliente() {
        return nome_Cliente;
    }

    public void setNome_Cliente(String nome_Cliente) {
        this.nome_Cliente = nome_Cliente;
    }

    public String getApelido_Cliente() {
        return apelido_Cliente;
    }

    public void setApelido_Cliente(String apelido_Cliente) {
        this.apelido_Cliente = apelido_Cliente;
    }

    public String getData_Nascimento_Cliente() {
        return data_Nascimento_Cliente;
    }

    public void setData_Nascimento_Cliente(String data_Nascimento_Cliente) {
        this.data_Nascimento_Cliente = data_Nascimento_Cliente;
    }

    public String getNumero_Casa_Cliente() {
        return numero_Casa_Cliente;
    }

    public void setNumero_Casa_Cliente(String numero_Casa_Cliente) {
        this.numero_Casa_Cliente = numero_Casa_Cliente;
    }

    public String getRua_Casa_Cliente() {
        return rua_Casa_Cliente;
    }

    public void setRua_Casa_Cliente(String rua_Casa_Cliente) {
        this.rua_Casa_Cliente = rua_Casa_Cliente;
    }

    public String getBairro_Casa_Cliente() {
        return bairro_Casa_Cliente;
    }

    public void setBairro_Casa_Cliente(String bairro_Casa_Cliente) {
        this.bairro_Casa_Cliente = bairro_Casa_Cliente;
    }

    public String getCidade_Casa_Cliente() {
        return cidade_Casa_Cliente;
    }

    public void setCidade_Casa_Cliente(String cidade_Casa_Cliente) {
        this.cidade_Casa_Cliente = cidade_Casa_Cliente;
    }

    public String getEstado_Casa_Cliente() {
        return estado_Casa_Cliente;
    }

    public void setEstado_Casa_Cliente(String estado_Casa_Cliente) {
        this.estado_Casa_Cliente = estado_Casa_Cliente;
    }

    public String getNome_Mae_Cliente() {
        return nome_Mae_Cliente;
    }

    public void setNome_Mae_Cliente(String nome_Mae_Cliente) {
        this.nome_Mae_Cliente = nome_Mae_Cliente;
    }

    public String getNome_Pai_Cliente() {
        return nome_Pai_Cliente;
    }

    public void setNome_Pai_Cliente(String nome_Pai_Cliente) {
        this.nome_Pai_Cliente = nome_Pai_Cliente;
    }

    public String getFone_Cliente() {
        return fone_Cliente;
    }

    public void setFone_Cliente(String fone_Cliente) {
        this.fone_Cliente = fone_Cliente;
    }

    public String getProfissao_Cliente() {
        return profissao_Cliente;
    }

    public void setProfissao_Cliente(String profissao_Cliente) {
        this.profissao_Cliente = profissao_Cliente;
    }

    public String getLocal_Trabalho_Cliente() {
        return local_Trabalho_Cliente;
    }

    public void setLocal_Trabalho_Cliente(String local_Trabalho_Cliente) {
        this.local_Trabalho_Cliente = local_Trabalho_Cliente;
    }

    public String getEstado_Civil_Cliente() {
        return estado_Civil_Cliente;
    }

    public void setEstado_Civil_Cliente(String estado_Civil_Cliente) {
        this.estado_Civil_Cliente = estado_Civil_Cliente;
    }

    public String getNome_Conjuje_Cliente() {
        return nome_Conjuje_Cliente;
    }

    public void setNome_Conjuje_Cliente(String nome_Conjuje_Cliente) {
        this.nome_Conjuje_Cliente = nome_Conjuje_Cliente;
    }

    public String getRg_Cliente() {
        return rg_Cliente;
    }

    public void setRg_Cliente(String rg_Cliente) {
        this.rg_Cliente = rg_Cliente;
    }

    public String getOrg_Emissor_RG_Cliente() {
        return org_Emissor_RG_Cliente;
    }

    public void setOrg_Emissor_RG_Cliente(String org_Emissor_RG_Cliente) {
        this.org_Emissor_RG_Cliente = org_Emissor_RG_Cliente;
    }

    public String getEstado_Emissor_RG_Cliente() {
        return estado_Emissor_RG_Cliente;
    }

    public void setEstado_Emissor_RG_Cliente(String estado_Emissor_RG_Cliente) {
        this.estado_Emissor_RG_Cliente = estado_Emissor_RG_Cliente;
    }

    public String getCpf_Cliente() {
        return cpf_Cliente;
    }

    public void setCpf_Cliente(String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }

    public float getRenda_Cliente() {
        return renda_Cliente;
    }

    public void setRenda_Cliente(float renda_Cliente) {
        this.renda_Cliente = renda_Cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_Cliente;
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
        final Cliente other = (Cliente) obj;
        if (this.id_Cliente != other.id_Cliente) {
            return false;
        }
        return true;
    }
}
