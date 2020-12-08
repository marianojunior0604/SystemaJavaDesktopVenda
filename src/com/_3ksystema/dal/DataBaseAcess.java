/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mariano Junior
 */
public class DataBaseAcess {
    
    private Path endeConf = Paths.get("C:/_3ksystemas/lojasaojorge/SqlBin.txt");
    private Path enderecoSql = Paths.get(CapturaEndeConfi());
    private Path enderecoSalvar;
    
    private String CapturaEndeConfi(){
        try {
            byte[] endeConfi = Files.readAllBytes(endeConf);
            String endereco = new String(endeConfi);
            return endereco;
        } catch (IOException e) {
            return "Erro ao encontrar o arquivo " + e;
        }
    }
    
    public void salvarBanco(Path endereco, String nome){
        
    }
    
}
