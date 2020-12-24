/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.relatorios;

import com._3ksystema.classes.FormataData;
import com._3ksystema.funcoes.FuncoesCliente;
import com._3ksystema.funcoes.FuncoesEmpresa;
import com._3ksystema.funcoes.FuncoesProduto;
import com._3ksystema.funcoes.FuncoesProdutoVenda;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Empresa;
import com._3ksystema.modelos.Produto;
import com._3ksystema.modelos.ProdutosVenda;
import com._3ksystema.modelos.Venda;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
public class GerarNotaVenda {
    
    private Empresa empresa = new Empresa();
    private FuncoesEmpresa fe = new FuncoesEmpresa();
    private Cliente cliente = new Cliente();
    private FuncoesCliente fc = new FuncoesCliente();
    private Produto produto = new Produto();
    private FuncoesProduto fp = new FuncoesProduto();
    private FormataData fd = new FormataData();
    private FuncoesProdutoVenda fpv = new FuncoesProdutoVenda();
    private DecimalFormat df = new DecimalFormat("#,###.00");
    
    public void gerarNotavenda(Venda venda, ArrayList<ProdutosVenda> produtosVenda) throws ClassNotFoundException, SQLException, FileNotFoundException, DocumentException, IOException{
        empresa = fe.pesquisaEmpresa();
        cliente = fc.pesquisaClienteCodigo(venda.getCliente());
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(30, 30, 30, 30);
        String nomeNota = "Nota de Venda - " + cliente.getNome_Cliente();
        PdfWriter.getInstance(documento, new FileOutputStream(nomeNota));
        documento.open();
        PdfPTable tabelaLogo = new PdfPTable(4);
        //BorderRadius bordas = new BorderRadius();
        PdfPCell celulaLogo = new PdfPCell();
        String texto = "Logo";
        Paragraph paragrafo = new Paragraph(texto);
        celulaLogo.addElement(paragrafo);
        PdfPCell celulasEmpresa = new PdfPCell();
        celulasEmpresa.setColspan(3);
        texto = empresa.getNomeEmpresa() + "\n" + 
                empresa.getCnpjEmpresa() + "\n" +
                empresa.getRuaEmpresa() + ", Nº " + empresa.getNumEmpresa() + 
                "\nBairro: " + empresa.getBairroEmpresa() + " - " + empresa.getCidadeEmpresa() + " - " + empresa.getEstadoEmpresa() +
                "\nFone: " + empresa.getFoneEmpresa();
        paragrafo = new Paragraph(texto);
        celulasEmpresa.addElement(paragrafo);
        tabelaLogo.addCell(celulaLogo);
        tabelaLogo.addCell(celulasEmpresa);
        documento.add(tabelaLogo);
        texto = "\n";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);
        PdfPTable tabelaCliente = new PdfPTable(2);
        PdfPCell celulaData = new PdfPCell();
        celulaData.setBorderWidthRight(0);
        texto = empresa.getCidadeEmpresa() + ": " + fd.dataAtualBr();
        paragrafo = new Paragraph(texto);
        celulaData.addElement(paragrafo);
        celulaData.setRowspan(2);
        PdfPCell celulaNVenda = new PdfPCell();
        texto = String.valueOf(venda.getIdVenda());
        paragrafo = new Paragraph(texto);
        celulaNVenda.addElement(paragrafo);
        PdfPCell celulaFoneCliente = new PdfPCell();
        texto = "Fone: " + cliente.getFone_Cliente();
        paragrafo = new Paragraph(texto);
        celulaFoneCliente.addElement(paragrafo);
        tabelaCliente.addCell(celulaData);
        tabelaCliente.addCell(celulaNVenda);
        tabelaCliente.addCell(celulaFoneCliente);
        PdfPCell celulaCliente = new PdfPCell();
        texto = "Nome: " + cliente.getNome_Cliente();
        paragrafo = new Paragraph(texto);
        celulaCliente.addElement(paragrafo);
        celulaCliente.setColspan(2);
        tabelaCliente.addCell(celulaCliente);
        PdfPCell celulaEndereco = new PdfPCell();
        texto = "Endereço: " + cliente.getRua_Casa_Cliente() + ", Nº: " + cliente.getNumero_Casa_Cliente()
                + "\nBairro: " + cliente.getBairro_Casa_Cliente() + "       Cidade / Estado: " + cliente.getCidade_Casa_Cliente() + "/" + cliente.getEstado_Casa_Cliente();
        paragrafo = new Paragraph(texto);
        celulaEndereco.addElement(paragrafo);
        celulaEndereco.setColspan(2);
        tabelaCliente.addCell(celulaEndereco);
        documento.add(tabelaCliente);
        texto = "\n";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);
        PdfPTable tabelaProdutos = new PdfPTable(12);
        PdfPCell celulaN = new PdfPCell();
        texto = "Nº";
        paragrafo = new Paragraph(texto);
        celulaN.addElement(paragrafo);
        PdfPCell celulaNP = new PdfPCell();
        texto = "Produto";
        paragrafo = new Paragraph(texto);
        celulaNP.addElement(paragrafo);
        celulaNP.setColspan(6);
        PdfPCell celulaPU = new PdfPCell();
        texto = "Valor U.";
        paragrafo = new Paragraph(texto);
        celulaPU.addElement(paragrafo);
        celulaPU.setColspan(2);
        PdfPCell celulaQt = new PdfPCell();
        texto = "Qtd.";
        paragrafo = new Paragraph(texto);
        celulaQt.addElement(paragrafo);
        PdfPCell celulaPT = new PdfPCell();
        texto = "Valor U.";
        paragrafo = new Paragraph(texto);
        celulaPT.addElement(paragrafo);
        celulaPT.setColspan(2);
        tabelaProdutos.addCell(celulaN);
        tabelaProdutos.addCell(celulaNP);
        tabelaProdutos.addCell(celulaPU);
        tabelaProdutos.addCell(celulaQt);
        tabelaProdutos.addCell(celulaPT);
        int numOrd = 0;
        //double valorVenda = 0.0;
        for (ProdutosVenda produtosVenda1 : produtosVenda) {
            numOrd++;
            PdfPCell nOdem = new PdfPCell();
            nOdem.setPadding(0);
            nOdem.setPaddingLeft(2);
            nOdem.setPaddingBottom(2);
            PdfPCell desk = new PdfPCell();
            desk.setPadding(0);
            desk.setPaddingLeft(2);
            desk.setPaddingBottom(2);
            desk.setColspan(6);
            PdfPCell valor = new PdfPCell();
            valor.setPadding(0);
            valor.setPaddingLeft(2);
            valor.setPaddingBottom(2);
            valor.setColspan(2);
            PdfPCell qtd = new PdfPCell();
            qtd.setPadding(0);
            qtd.setPaddingLeft(2);
            PdfPCell vTotal = new PdfPCell();
            vTotal.setPadding(0);
            vTotal.setPaddingLeft(2);
            vTotal.setPaddingBottom(2);
            vTotal.setColspan(2);
            paragrafo = new Paragraph(String.valueOf(numOrd));
            nOdem.addElement(paragrafo);
            produto = fpv.pesquisaProdutoVenda(produtosVenda1.getIdproduto());
            texto = produto.getNomeProduto() + ", " + produto.getMarcaProtudo() + ", " + produto.getModeloProtudo();
            paragrafo = new Paragraph(texto);
            desk.addElement(paragrafo);
            texto = String.valueOf(df.format(produto.getValorVendaProduto()));
            paragrafo = new Paragraph(texto);
            valor.addElement(paragrafo);
            texto = String.valueOf(produtosVenda1.getQtdProdutoVenda());
            paragrafo = new Paragraph(texto);
            qtd.addElement(paragrafo);
            texto = String.valueOf(df.format(produto.getValorVendaProduto() * produtosVenda1.getQtdProdutoVenda()));
            paragrafo = new Paragraph(texto);
            vTotal.addElement(paragrafo);
            tabelaProdutos.addCell(nOdem);
            tabelaProdutos.addCell(desk);
            tabelaProdutos.addCell(valor);
            tabelaProdutos.addCell(qtd);
            tabelaProdutos.addCell(vTotal);
            //valorVenda += (produto.getValorVendaProduto() * produtosVenda1.getQtdProdutoVenda());
        }
        PdfPCell celTotal = new PdfPCell();
        texto = "Total: ";
        paragrafo = new Paragraph(texto);
        celTotal.addElement(paragrafo);
        celTotal.setColspan(9);
        PdfPCell celVTotal = new PdfPCell();
        texto = String.valueOf(df.format(venda.getValorVenda()));
        paragrafo = new Paragraph(texto);
        celVTotal.addElement(paragrafo);
        celVTotal.setColspan(3);
        tabelaProdutos.addCell(celTotal);
        tabelaProdutos.addCell(celVTotal);
        PdfPCell celEntrada = new PdfPCell();
        texto = "Entrada:";
        paragrafo = new Paragraph(texto);
        celEntrada.addElement(paragrafo);
        celEntrada.setColspan(9);
        PdfPCell celVEntrada = new PdfPCell();
        texto = String.valueOf(df.format(venda.getValorEntrada()));
        paragrafo = new Paragraph(texto);
        celVEntrada.addElement(paragrafo);
        tabelaProdutos.addCell(celEntrada);
        tabelaProdutos.addCell(celVEntrada);
        documento.add(tabelaProdutos);
        documento.close();
        Desktop.getDesktop().open(new File(nomeNota));
    }
    
}
