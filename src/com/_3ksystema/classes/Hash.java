/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano Junior
 */
public class Hash {
    
    public static String toHexadecimal(byte[] source){
        StringBuilder sb = new StringBuilder();
        for (byte b : source) {
            String toAppend = String.format("%2X", b);
            sb.append(toAppend);
        }
        return sb.toString();
    }
    
    public static String toMD5Hash(String source) throws NoSuchAlgorithmException{
        String result = "";
        
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5HashBytes = md5.digest(source.getBytes());
            result = toHexadecimal(md5HashBytes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return result;
    }
    
}
