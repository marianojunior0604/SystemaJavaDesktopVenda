/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

/**
 *
 * @author Junior
 */
public class FormataNumeros {
    
    public String ConverterFloat(String numero){
        String numbero = numero.replace(".", "");
        String number = numbero.replace(',', '.');
        return number;
    }
    
    public String FloatConvertido(String numero){
        String number = numero.replace('.', ',');
        return number;
    }
    
}
