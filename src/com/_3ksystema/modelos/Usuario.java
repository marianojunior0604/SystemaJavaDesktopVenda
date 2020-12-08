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
public class Usuario {
    
    private int id_Usuario;
    private String nome_Usuario;
    private String perfil_Usuario;
    private String login_Usuario;
    private String senha_Usuario;

    public Usuario() {
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNome_Usuario() {
        return nome_Usuario;
    }

    public void setNome_Usuario(String nome_Usuario) {
        this.nome_Usuario = nome_Usuario;
    }

    public String getPerfil_Usuario() {
        return perfil_Usuario;
    }

    public void setPerfil_Usuario(String perfil_Usuario) {
        this.perfil_Usuario = perfil_Usuario;
    }

    public String getLogin_Usuario() {
        return login_Usuario;
    }

    public void setLogin_Usuario(String login_Usuario) {
        this.login_Usuario = login_Usuario;
    }

    public String getSenha_Usuario() {
        return senha_Usuario;
    }

    public void setSenha_Usuario(String senha_Usuario) {
        this.senha_Usuario = senha_Usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_Usuario;
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
        final Usuario other = (Usuario) obj;
        if (this.id_Usuario != other.id_Usuario) {
            return false;
        }
        return true;
    }
}
