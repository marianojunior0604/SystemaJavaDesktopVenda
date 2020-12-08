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
public class GerarCarnePaisagem {

    FuncaoCarne fcn = new FuncaoCarne();
    FuncoesCliente fc = new FuncoesCliente();
    FuncoesVenda fv = new FuncoesVenda();
    ArrayList<Carne> carnes = new ArrayList();
    FormataNumeros fn = new FormataNumeros();
    FormataData fd = new FormataData();
    Cliente cliente = new Cliente();
    Venda venda = new Venda();
    FuncoesEmpresa fep = new FuncoesEmpresa();

    public void criaCarne(Venda venda) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnes(venda.getIdVenda());
        cliente = fc.pesquisaClienteCodigo(venda.getCliente());
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4.rotate());
        documento.setMargins(30, 20, 40, 50);
        documento.setMarginMirroring(true);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        documento.open();
        int contador = 1;
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(2);
            PdfPTable subTabela = new PdfPTable(2);
            PdfPTable susubTabela = new PdfPTable(2);
            tabela.setWidthPercentage(100);
            subTabela.setWidthPercentage(100);
            PdfPCell cel1 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()));
            PdfPCell cel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()));
            subTabela.addCell(cel1);
            subTabela.addCell(cel2);
            cel1 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente()));
            cel2 = new PdfPCell(new Paragraph("Nome:\n" + cliente.getNome_Cliente()));
            subTabela.addCell(cel1);
            subTabela.addCell(cel2);
            String endereco = cliente.getRua_Casa_Cliente() + " " + cliente.getNumero_Casa_Cliente();
            cel1 = new PdfPCell(new Paragraph("Endereço:\n" + endereco));
            cel2 = new PdfPCell(new Paragraph("Endereço:\n" + endereco));
            subTabela.addCell(cel1);
            subTabela.addCell(cel2);
            double valorParcela = (venda.getValorVenda() - venda.getValorEntrada()) / venda.getQtdParcelas();
            DecimalFormat df = new DecimalFormat("#,###.00");
            //System.out.println("Valor da parcela no banco: " + carne.getValorParcela() + "\nValor Impresso: " + df.format(carne.getValorParcela()));
            PdfPCell scel1 = new PdfPCell(new Paragraph("Valor:\nR$ " + df.format(valorParcela)));
            PdfPCell scel2 = new PdfPCell(new Paragraph("Código:\nNº " + carne.getIdCarne()));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            susubTabela.addCell(scel1);
            susubTabela.addCell(scel2);
            scel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento()));
            scel2 = new PdfPCell(new Paragraph("Pago em:\n____/____/______"));
            scel1.setBorder(Rectangle.NO_BORDER);
            scel2.setBorder(Rectangle.NO_BORDER);
            susubTabela.addCell(scel1);
            susubTabela.addCell(scel2);
            cel1.addElement(subTabela);
            cel2.addElement(subTabela);
            subTabela.addCell(cel1);
            subTabela.addCell(cel2);
            PdfPCell el1;
            PdfPCell el2;
            //if (contador % 2 == 1) {
            el1 = new PdfPCell(new Paragraph());
            tabela.addCell(el1);
            // }else{
            el2 = new PdfPCell(new Paragraph());
            tabela.addCell(el2);
            //}
            System.out.println(contador);
            contador++;
            documento.add(tabela);
            documento.add(new Paragraph("\n"));
        }
        documento.close();
        Desktop.getDesktop().open(new File(nomeCarne));
    }

    public void reimprimirCarnes(int idCliente) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnesNPagos(idCliente);
        cliente = fc.pesquisaClienteCodigo(idCliente);
        Empresa empresa = fep.pesquisaEmpresa();
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(30, 20, 40, 50);
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

}
