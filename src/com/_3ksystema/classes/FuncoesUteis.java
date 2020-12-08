/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Mariano Junior
 */
public class FuncoesUteis {

    public static Date somarData(int dias, Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, dias);

        return (Date) calendar.getTime();
    }

}
