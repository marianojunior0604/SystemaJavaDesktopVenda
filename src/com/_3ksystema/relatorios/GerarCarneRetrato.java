/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.relatorios;

import com._3ksystema.classes.FormataData;
import com._3ksystema.classes.FormataNumeros;
import com._3ksystema.funcoes.FuncaoCarne;
import com._3ksystema.funcoes.FuncoesCliente;
import com._3ksystema.funcoes.FuncoesEmpresa;
import com._3ksystema.funcoes.FuncoesVenda;
import com._3ksystema.modelos.Carne;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Empresa;
import com._3ksystema.modelos.Venda;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
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
public class GerarCarneRetrato {

    private FuncaoCarne fcn = new FuncaoCarne();
    private FuncoesCliente fc = new FuncoesCliente();
    private FuncoesVenda fv = new FuncoesVenda();
    private ArrayList<Carne> carnes = new ArrayList();
    private FormataNumeros fn = new FormataNumeros();
    private FormataData fd = new FormataData();
    private Cliente cliente = new Cliente();
    private Venda venda = new Venda();
    private FuncoesEmpresa fep = new FuncoesEmpresa();
    
    //Carnê Jorge
    
    public void criaCarne(Venda venda) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnes(venda.getIdVenda());
        cliente = fc.pesquisaClienteCodigo(venda.getCliente());
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(20, 20, 50, 30);
        //Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        documento.setMarginMirroring(true);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(2);
            PdfPTable subTabela = new PdfPTable(2);
            tabela.setWidthPercentage(100);
            subTabela.setWidthPercentage(100);
            PdfPCell cel1 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()));
            PdfPCell cel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            cel1 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente()));
            cel2 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente()));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            String endereco = cliente.getRua_Casa_Cliente() + " " + cliente.getNumero_Casa_Cliente();
            cel1 = new PdfPCell(new Paragraph("Endereço:\n" + endereco));
            cel2 = new PdfPCell(new Paragraph("Endereço:\n" + endereco));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            double valorParcela = (venda.getValorVenda() - venda.getValorEntrada()) / venda.getQtdParcelas();
            DecimalFormat df = new DecimalFormat("#,###.00");
            //System.out.println("Valor da parcela no banco: " + carne.getValorParcela() + "\nValor Impresso: " + df.format(carne.getValorParcela()));
            PdfPCell scel1 = new PdfPCell(new Paragraph("Valor:\nR$ " + df.format(valorParcela)));
            PdfPCell scel2 = new PdfPCell(new Paragraph("Código:\nNº " + carne.getIdCarne()));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            scel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento()));
            scel2 = new PdfPCell(new Paragraph("Pago em:\n____/____/______"));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            cel1.addElement(subTabela);
            cel2.addElement(subTabela);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n"));
        }
        documento.close();
        Desktop.getDesktop().open(new File(nomeCarne));
    }
    
    //Carne Alcione
    /*
    public void criaCarne(Venda venda) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnes(venda.getIdVenda());
        cliente = fc.pesquisaClienteCodigo(venda.getCliente());
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(20, 50, 25, 25);
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        documento.setMarginMirroring(true);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(2);
            PdfPTable subTabela = new PdfPTable(2);
            tabela.setWidthPercentage(83);
            subTabela.setWidthPercentage(100);
            PdfPCell cel1 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            PdfPCell cel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            cel1 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            cel2 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            String endereco = cliente.getRua_Casa_Cliente() + " " + cliente.getNumero_Casa_Cliente();
            cel1 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            cel2 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            double valorParcela = (venda.getValorVenda() - venda.getValorEntrada()) / venda.getQtdParcelas();
            DecimalFormat df = new DecimalFormat("#,###.00");
            //System.out.println("Valor da parcela no banco: " + carne.getValorParcela() + "\nValor Impresso: " + df.format(carne.getValorParcela()));
            PdfPCell scel1 = new PdfPCell(new Paragraph("Valor:\nR$ " + df.format(valorParcela), fonte));
            PdfPCell scel2 = new PdfPCell(new Paragraph("Código:\nNº " + carne.getIdCarne(), fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            scel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento(), fonte));
            scel2 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            cel1.addElement(subTabela);
            cel2.addElement(subTabela);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n"));
        }
        documento.close();
        Desktop.getDesktop().open(new File(nomeCarne));
    }
    */
    //Carne Janseli
    /*
    public void criaCarne(Venda venda) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnes(venda.getIdVenda());
        cliente = fc.pesquisaClienteCodigo(venda.getCliente());
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(20, 50, 25, 25);
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        documento.setMarginMirroring(true);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(2);
            PdfPTable subTabela = new PdfPTable(2);
            tabela.setWidthPercentage(70);
            subTabela.setWidthPercentage(100);
            PdfPCell cel1 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            PdfPCell cel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            cel1.setFixedHeight(30);
            cel2.setFixedHeight(30);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            cel1 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            cel2 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            cel1.setFixedHeight(30);
            cel2.setFixedHeight(30);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            String endereco = cliente.getRua_Casa_Cliente() + " " + cliente.getNumero_Casa_Cliente();
            cel1 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            cel2 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            cel1.setFixedHeight(30);
            cel2.setFixedHeight(30);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            DecimalFormat df = new DecimalFormat("#,###.00");
            PdfPCell scel1 = new PdfPCell(new Paragraph("Valor:\nR$ " + df.format(carne.getValorParcela()), fonte));
            PdfPCell scel2 = new PdfPCell(new Paragraph("Código:\nNº " + carne.getIdCarne(), fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            scel1.setFixedHeight(25);
            scel2.setFixedHeight(25);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            scel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento(), fonte));
            scel2 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            scel1.setFixedHeight(25);
            scel2.setFixedHeight(25);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            cel1.addElement(subTabela);
            cel2.addElement(subTabela);
            cel1.setFixedHeight(55);
            cel2.setFixedHeight(55);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 6)));
        }
        documento.close();
        Desktop.getDesktop().open(new File(nomeCarne));
    }
    */
    //reimpressão Alcione
    /*
    public void reimprimirCarnes(int idCliente) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnesNPagos(idCliente);
        cliente = fc.pesquisaClienteCodigo(idCliente);
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(20, 50, 25, 25);
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        documento.setMarginMirroring(false);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(2);
            PdfPTable subTabela = new PdfPTable(2);
            tabela.setWidthPercentage(83);
            subTabela.setWidthPercentage(100);
            PdfPCell cel1 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            PdfPCell cel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            cel1 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            cel2 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            String endereco = cliente.getRua_Casa_Cliente() + " " + cliente.getNumero_Casa_Cliente();
            cel1 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            cel2 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            DecimalFormat df = new DecimalFormat("#,###.00");
            PdfPCell scel1 = new PdfPCell(new Paragraph("Valor:\nR$ " + df.format(carne.getValorParcela()), fonte));
            PdfPCell scel2 = new PdfPCell(new Paragraph("Código:\nNº " + carne.getIdCarne(), fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            scel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento(), fonte));
            scel2 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            cel1.addElement(subTabela);
            cel2.addElement(subTabela);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n"));
        }
        documento.close();
        Desktop.getDesktop().open(new File(nomeCarne));
    }
    */
    //Reimpressão Jorge
    
    public void reimprimirCarnes(int idCliente) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnesNPagos(idCliente);
        cliente = fc.pesquisaClienteCodigo(idCliente);
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(20, 20, 50, 30);
        //Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        documento.setMarginMirroring(false);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(2);
            PdfPTable subTabela = new PdfPTable(2);
            tabela.setWidthPercentage(100);
            subTabela.setWidthPercentage(100);
            PdfPCell cel1 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()));
            PdfPCell cel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            cel1 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente()));
            cel2 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente()));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            String endereco = cliente.getRua_Casa_Cliente() + " " + cliente.getNumero_Casa_Cliente();
            cel1 = new PdfPCell(new Paragraph("Endereço:\n" + endereco));
            cel2 = new PdfPCell(new Paragraph("Endereço:\n" + endereco));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            DecimalFormat df = new DecimalFormat("#,###.00");
            PdfPCell scel1 = new PdfPCell(new Paragraph("Valor:\nR$ " + df.format(carne.getValorParcela())));
            PdfPCell scel2 = new PdfPCell(new Paragraph("Código:\nNº " + carne.getIdCarne()));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            scel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento()));
            scel2 = new PdfPCell(new Paragraph("Pago em:\n____/____/______"));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            cel1.addElement(subTabela);
            cel2.addElement(subTabela);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n"));
        }
        documento.close();
        Desktop.getDesktop().open(new File(nomeCarne));
    }
    
    //reimpressão Janseli
    /*
    public void reimprimirCarnes(int idCliente) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnesNPagos(idCliente);
        cliente = fc.pesquisaClienteCodigo(idCliente);
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(20, 50, 25, 25);
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        documento.setMarginMirroring(false);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(2);
            PdfPTable subTabela = new PdfPTable(2);
            tabela.setWidthPercentage(70);
            subTabela.setWidthPercentage(100);
            PdfPCell cel1 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            PdfPCell cel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa(), fonte));
            cel1.setFixedHeight(30);
            cel2.setFixedHeight(30);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            cel1 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            cel2 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente(), fonte));
            cel1.setFixedHeight(30);
            cel2.setFixedHeight(30);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            String endereco = cliente.getRua_Casa_Cliente() + " " + cliente.getNumero_Casa_Cliente();
            cel1 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            cel2 = new PdfPCell(new Paragraph("Endereço:\n" + endereco, fonte));
            cel1.setFixedHeight(30);
            cel2.setFixedHeight(30);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            DecimalFormat df = new DecimalFormat("#,###.00");
            PdfPCell scel1 = new PdfPCell(new Paragraph("Valor:\nR$ " + df.format(carne.getValorParcela()), fonte));
            PdfPCell scel2 = new PdfPCell(new Paragraph("Código:\nNº " + carne.getIdCarne(), fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            scel1.setFixedHeight(25);
            scel2.setFixedHeight(25);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            scel1 = new PdfPCell(new Paragraph("Vencimento:\n" + fd.dataBr(carne.getDataVencimento()), fonte));
            scel2 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", fonte));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            scel1.setFixedHeight(25);
            scel2.setFixedHeight(25);
            subTabela.addCell(scel1);
            subTabela.addCell(scel2);
            cel1.addElement(subTabela);
            cel2.addElement(subTabela);
            cel1.setFixedHeight(55);
            cel2.setFixedHeight(55);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 6)));
        }
        documento.close();
        Desktop.getDesktop().open(new File(nomeCarne));
    }
    */
}

