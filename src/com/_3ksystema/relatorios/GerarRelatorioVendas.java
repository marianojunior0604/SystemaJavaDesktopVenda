/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.relatorios;

import com._3ksystema.classes.FormataData;
import com._3ksystema.funcoes.FuncoesEmpresa;
import com._3ksystema.modelos.Empresa;
import com._3ksystema.modelos.RelVendaPer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Mariano Junior
 */
public class GerarRelatorioVendas {
    
    private Empresa empresa = new Empresa();
    private FuncoesEmpresa fe = new FuncoesEmpresa();
    private FormataData fd = new FormataData();
    private DecimalFormat df = new DecimalFormat("#,###.00");
    
    public void gerarRelatorio(ArrayList<RelVendaPer> rvp, String dataInicio, String dataFim) throws FileNotFoundException, DocumentException, ClassNotFoundException, SQLException, IOException{
        
        empresa = fe.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(20, 50, 30, 30);
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        documento.setMarginMirroring(true);
        String periodo = fd.formataData(dataInicio) + " - " + fd.formataData(dataFim);
        String relatorioPeriodo = periodo + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(relatorioPeriodo));
        documento.open();
        String texto = "Relatorio de vendas da " + empresa.getNomeEmpresa() + " com informações do Periodo " + dataInicio + " à " + dataFim;
        Paragraph paragrafo = new Paragraph(texto);
        paragrafo.setAlignment(1);
        documento.add(paragrafo);
        texto = " ";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);
        texto = " ";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);
        texto = "Relatório de vendas detalhado da empresa " + empresa.getNomeEmpresa() + " Inscrita sobre o CNPJ: " + empresa.getCnpjEmpresa() + " Localizada no Endereço: " + empresa.getRuaEmpresa() 
                + " Nº " + empresa.getNumEmpresa() + ", Bairro: " + empresa.getBairroEmpresa() + ", na cidade: " + empresa.getCidadeEmpresa() + " - " + empresa.getEstadoEmpresa() + " no período de "
                + dataInicio + " à " + dataFim + ". Com dados detalhados de vendas diarias e valores individuais alem de um total arrecadado no periodo.";
        paragrafo = new Paragraph(texto, fonte);
        paragrafo.setAlignment(3);
        documento.add(paragrafo);
        texto = " ";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);
        PdfPTable tblCabecalho = new PdfPTable(10);
        tblCabecalho.setWidthPercentage(100);
        PdfPCell celulaN = new PdfPCell();
        celulaN.setPaddingBottom(2);
        texto = "Nº";
        paragrafo = new Paragraph(texto);
        paragrafo.setAlignment(1);
        celulaN.addElement(paragrafo);
        PdfPCell celulaC = new PdfPCell();
        celulaC.setPaddingBottom(2);
        celulaC.setColspan(5);
        texto = "Cliente";
        paragrafo = new Paragraph(texto);
        paragrafo.setAlignment(1);
        celulaC.addElement(paragrafo);
        PdfPCell celulaD = new PdfPCell();
        celulaD.setPaddingBottom(2);
        celulaD.setColspan(2);
        texto = "Data";
        paragrafo = new Paragraph(texto);
        paragrafo.setAlignment(1);
        celulaD.addElement(paragrafo);
        PdfPCell celulaV = new PdfPCell();
        celulaV.setPaddingBottom(2);
        celulaV.setColspan(2);
        texto = "Valor";
        paragrafo = new Paragraph(texto);
        paragrafo.setAlignment(1);
        celulaV.addElement(paragrafo);
        tblCabecalho.addCell(celulaN);
        tblCabecalho.addCell(celulaC);
        tblCabecalho.addCell(celulaD);
        tblCabecalho.addCell(celulaV);
        int contador = 0;
        double valorTotal = 0.0;
        for (RelVendaPer rel : rvp) {
            PdfPCell celN = new PdfPCell();
            PdfPCell celC = new PdfPCell();
            celC.setColspan(5);
            PdfPCell celD = new PdfPCell();
            celD.setColspan(2);
            PdfPCell celV = new PdfPCell();
            celV.setColspan(2);
            contador ++;
            texto = String.valueOf(contador);
            paragrafo = new Paragraph(texto, fonte);
            paragrafo.setAlignment(1);
            celN.addElement(paragrafo);
            texto = rel.getNomeCliente();
            paragrafo = new Paragraph(texto, fonte);
            celC.addElement(paragrafo);
            texto = rel.getDataVenda();
            paragrafo = new Paragraph(texto, fonte);
            celD.addElement(paragrafo);
            texto = String.valueOf(df.format(rel.getValorVenda()));
            paragrafo = new Paragraph(texto, fonte);
            celV.addElement(paragrafo);
            valorTotal += rel.getValorVenda();
            tblCabecalho.addCell(celN);
            tblCabecalho.addCell(celC);
            tblCabecalho.addCell(celD);
            tblCabecalho.addCell(celV);
        }
        documento.add(tblCabecalho);
        texto = "Durante o periodo acima descrito a Loja em questão efetuou " + contador + " Totalizando a Quantia de R$: " + df.format(valorTotal);
        paragrafo = new Paragraph(texto, fonte);
        documento.add(paragrafo);
        documento.close();
        Desktop.getDesktop().open(new File(relatorioPeriodo));
    }
    
}
