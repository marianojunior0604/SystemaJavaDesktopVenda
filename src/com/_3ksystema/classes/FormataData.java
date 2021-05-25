/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mariano
 */
public class FormataData {

    private Calendar data = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private int dia = data.get(Calendar.DAY_OF_MONTH);
    private int mes = data.get(Calendar.MONTH);
    private int ano = data.get(Calendar.YEAR);
    private int hora = data.get(Calendar.HOUR_OF_DAY);
    private int min = data.get(Calendar.MINUTE);

    public String formataData(String data) {
        String dataFormatada;

        String d = data.substring(0, 2);
        String m = data.substring(3, 5);
        String a = data.substring(6);

        dataFormatada = a + "-" + m + "-" + d;

        return dataFormatada;
    }

    public String dataBr(String dataFormatada) {
        String dat;
        String a = dataFormatada.substring(0, 4);
        String m = dataFormatada.substring(5, 7);
        String d = dataFormatada.substring(8);
        dat = d + "/" + m + "/" + a;
        return dat;
    }

    public String dataAtualBr() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = dateFormat.format(new Date());
        return dataAtual;
    }

    public String dataSQL() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataSQL = dateFormat.format(new Date());
        return dataSQL;
    }

    public String dataExtenso() {
        DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
        return formato.format(new Date());
    }
    
    public long calculaJuros(String dataVencimento, String dataAtual) throws ParseException{
        Date dataVenc = sdf.parse(dataBr(dataVencimento));
        Date dataAtua = sdf.parse(dataAtual);
        long tempoMili = Math.abs(dataVenc.getTime() - dataAtua.getTime());
        return TimeUnit.DAYS.convert(tempoMili, TimeUnit.MILLISECONDS);
    }
}
