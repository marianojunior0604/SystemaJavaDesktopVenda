/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._3ksystema.relatorios;

import com._3ksystema.classes.FormataData;
import com._3ksystema.funcoes.FuncoesProdutoVenda;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Empresa;
import com._3ksystema.modelos.Produto;
import com._3ksystema.modelos.ProdutosVenda;
import com._3ksystema.modelos.Venda;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Mariano Junior
 */
public class GerarOrcamento {

    private FormataData fd = new FormataData();
    private Produto produto = new Produto();
    private FuncoesProdutoVenda fpv = new FuncoesProdutoVenda();
    DecimalFormat df = new DecimalFormat("#,###.00");

    public void gerarOrcamento(Cliente cliente, Empresa empresa, Venda venda, ArrayList<ProdutosVenda> produtosVenda) throws FileNotFoundException, DocumentException, IOException {
        Document documento = new Document();
        Font fonte = new Font(Font.FontFamily.HELVETICA, 8);
        documento.setPageSize(PageSize.A4);
        documento.setMargins(30, 30, 20, 20);
        String nomeDocumento = "Orçamento " + cliente.getNome_Cliente();
        PdfWriter.getInstance(documento, new FileOutputStream(nomeDocumento));
        documento.open();
        String texto = "Orçamento para proposta de compra";
        Paragraph paragrafo = new Paragraph(texto);
        paragrafo.setAlignment(1);
        documento.add(paragrafo);
        texto = "\n";
        paragrafo = new Paragraph(texto);
        paragrafo.setAlignment(0);
        documento.add(paragrafo);
        PdfPTable tabelaLogo = new PdfPTable(5);
        tabelaLogo.setWidthPercentage(100);
        PdfPCell celula1 = new PdfPCell();
        PdfPCell dadosEmpresa = new PdfPCell();
        PdfPCell dadosData = new PdfPCell();
        texto = empresa.getNomeEmpresa() + "\n" + empresa.getRuaEmpresa() + " " + empresa.getNumEmpresa()
                + "\n" + empresa.getCidadeEmpresa() + " - " + empresa.getEstadoEmpresa()
                + "\nCNPJ: " + empresa.getCnpjEmpresa() + "\nE-mail: " + empresa.getEmailEmpresa()
                + "\nFone: " + empresa.getFoneEmpresa();
        paragrafo = new Paragraph(texto, new Font(Font.FontFamily.HELVETICA, 8));
        paragrafo.setAlignment(1);
        dadosEmpresa.addElement(paragrafo);
        dadosEmpresa.setColspan(3);
        texto = "";
        paragrafo = new Paragraph(texto);
        celula1.addElement(paragrafo);
        dadosData.addElement(paragrafo);
        tabelaLogo.addCell(celula1);
        tabelaLogo.addCell(dadosEmpresa);
        tabelaLogo.addCell(dadosData);
        documento.add(tabelaLogo);
        documento.add(paragrafo);
        texto = "Dados do Cliente\n";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);
        texto = " ";
        paragrafo = new Paragraph(texto, new Font(Font.FontFamily.HELVETICA, 2));
        documento.add(paragrafo);
        PdfPTable tabelaCliente = new PdfPTable(6);
        tabelaCliente.setWidthPercentage(100);
        PdfPCell celulaCliente = new PdfPCell();
        celulaCliente.setPadding(0);
        celulaCliente.setPaddingLeft(2);
        texto = "Nome:\n" + cliente.getNome_Cliente();
        paragrafo = new Paragraph(texto, fonte);
        celulaCliente.setColspan(4);
        celulaCliente.addElement(paragrafo);
        PdfPCell celulaFone = new PdfPCell();
        celulaFone.setPadding(0);
        celulaFone.setPaddingLeft(2);
        celulaFone.setPaddingBottom(2);
        celulaFone.setColspan(2);
        texto = "Telefone:\n" + cliente.getFone_Cliente();
        paragrafo = new Paragraph(texto, fonte);
        celulaFone.addElement(paragrafo);
        tabelaCliente.addCell(celulaCliente);
        tabelaCliente.addCell(celulaFone);
        PdfPCell celulaEndereco = new PdfPCell();
        celulaEndereco.setPadding(0);
        celulaEndereco.setPaddingLeft(2);
        celulaEndereco.setPaddingBottom(2);
        celulaEndereco.setColspan(4);
        texto = "Endereço:\n" + cliente.getRua_Casa_Cliente();
        paragrafo = new Paragraph(texto, fonte);
        celulaEndereco.addElement(paragrafo);
        PdfPCell celulaNCasa = new PdfPCell();
        celulaNCasa.setPadding(0);
        celulaNCasa.setPaddingLeft(2);
        celulaNCasa.setPaddingBottom(2);
        celulaNCasa.setColspan(2);
        texto = "Numero:\n" + cliente.getNumero_Casa_Cliente();
        paragrafo = new Paragraph(texto, fonte);
        celulaNCasa.addElement(paragrafo);
        tabelaCliente.addCell(celulaEndereco);
        tabelaCliente.addCell(celulaNCasa);
        PdfPCell celulaBairro = new PdfPCell();
        celulaBairro.setPadding(0);
        celulaBairro.setPaddingLeft(2);
        celulaBairro.setPaddingBottom(2);
        celulaBairro.setColspan(2);
        texto = "Bairro:\n" + cliente.getBairro_Casa_Cliente();
        paragrafo = new Paragraph(texto, fonte);
        celulaBairro.addElement(paragrafo);
        PdfPCell celulaCidade = new PdfPCell();
        celulaCidade.setPadding(0);
        celulaCidade.setPaddingLeft(2);
        celulaCidade.setPaddingBottom(2);
        celulaCidade.setColspan(2);
        texto = "Cidade:\n" + cliente.getCidade_Casa_Cliente();
        paragrafo = new Paragraph(texto, fonte);
        celulaCidade.addElement(paragrafo);
        PdfPCell celulaEstado = new PdfPCell();
        celulaEstado.setPadding(0);
        celulaEstado.setPaddingLeft(2);
        celulaEstado.setPaddingBottom(2);
        celulaEstado.setColspan(2);
        texto = "Estado:\n" + cliente.getEstado_Casa_Cliente();
        paragrafo = new Paragraph(texto, fonte);
        celulaEstado.addElement(paragrafo);
        tabelaCliente.addCell(celulaBairro);
        tabelaCliente.addCell(celulaCidade);
        tabelaCliente.addCell(celulaEstado);
        documento.add(tabelaCliente);
        /*texto = " ";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);*/
        texto = "Produtos:";
        paragrafo = new Paragraph(texto);
        documento.add(paragrafo);
        texto = " ";
        paragrafo = new Paragraph(texto, new Font(Font.FontFamily.HELVETICA, 2));
        documento.add(paragrafo);
        PdfPTable tabelaProdutos = new PdfPTable(10);
        tabelaProdutos.setWidthPercentage(100);
        PdfPCell celulaNOrd = new PdfPCell();
        celulaNOrd.setPadding(0);
        celulaNOrd.setPaddingLeft(2);
        celulaNOrd.setPaddingBottom(2);
        texto = "Nº";
        paragrafo = new Paragraph(texto, fonte);
        paragrafo.setAlignment(1);
        celulaNOrd.addElement(paragrafo);
        PdfPCell celulaDesk = new PdfPCell();
        celulaDesk.setPadding(0);
        celulaDesk.setPaddingLeft(2);
        celulaDesk.setPaddingBottom(2);
        celulaDesk.setColspan(4);
        texto = "Descrição";
        paragrafo = new Paragraph(texto, fonte);
        paragrafo.setAlignment(1);
        celulaDesk.addElement(paragrafo);
        PdfPCell celulaValor = new PdfPCell();
        celulaValor.setPadding(0);
        celulaValor.setPaddingLeft(2);
        celulaValor.setPaddingBottom(2);
        celulaValor.setColspan(2);
        texto = "V. Unit.";
        paragrafo = new Paragraph(texto, fonte);
        paragrafo.setAlignment(1);
        celulaValor.addElement(paragrafo);
        PdfPCell celulaQtd = new PdfPCell();
        celulaQtd.setPadding(0);
        celulaQtd.setPaddingLeft(2);
        celulaQtd.setPaddingBottom(2);
        texto = "Qtd";
        paragrafo = new Paragraph(texto, fonte);
        paragrafo.setAlignment(1);
        celulaQtd.addElement(paragrafo);
        PdfPCell celulaVTotal = new PdfPCell();
        celulaVTotal.setPadding(0);
        celulaVTotal.setPaddingLeft(2);
        celulaVTotal.setPaddingBottom(2);
        celulaVTotal.setColspan(2);
        texto = "V. Total";
        paragrafo = new Paragraph(texto, fonte);
        paragrafo.setAlignment(1);
        celulaVTotal.addElement(paragrafo);
        tabelaProdutos.addCell(celulaNOrd);
        tabelaProdutos.addCell(celulaDesk);
        tabelaProdutos.addCell(celulaValor);
        tabelaProdutos.addCell(celulaQtd);
        tabelaProdutos.addCell(celulaVTotal);
        int numOrd = 0;
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
            desk.setColspan(4);
            PdfPCell valor = new PdfPCell();
            valor.setPadding(0);
            valor.setPaddingLeft(2);
            valor.setPaddingBottom(2);
            valor.setColspan(2);
            PdfPCell qtd = new PdfPCell();
            qtd.setPadding(0);
            qtd.setPaddingLeft(2);
            qtd.setPaddingBottom(2);
            PdfPCell vTotal = new PdfPCell();
            vTotal.setPadding(0);
            vTotal.setPaddingLeft(2);
            vTotal.setPaddingBottom(2);
            vTotal.setColspan(2);
            paragrafo = new Paragraph(String.valueOf(numOrd), fonte);
            nOdem.addElement(paragrafo);
            produto = fpv.pesquisaProdutoVenda(produtosVenda1.getIdproduto());
            texto = produto.getNomeProduto() + ", " + produto.getMarcaProtudo() + ", " + produto.getModeloProtudo();
            paragrafo = new Paragraph(texto, fonte);
            desk.addElement(paragrafo);
            texto = String.valueOf(df.format(produto.getValorVendaProduto()));
            paragrafo = new Paragraph(texto, fonte);
            valor.addElement(paragrafo);
            texto = String.valueOf(produtosVenda1.getQtdProdutoVenda());
            paragrafo = new Paragraph(texto, fonte);
            qtd.addElement(paragrafo);
            texto = String.valueOf(df.format(produto.getValorVendaProduto() * produtosVenda1.getQtdProdutoVenda()));
            paragrafo = new Paragraph(texto, fonte);
            vTotal.addElement(paragrafo);
            tabelaProdutos.addCell(nOdem);
            tabelaProdutos.addCell(desk);
            tabelaProdutos.addCell(valor);
            tabelaProdutos.addCell(qtd);
            tabelaProdutos.addCell(vTotal);
        }
        
        documento.add(tabelaProdutos);
        documento.close();
        Desktop.getDesktop().open(new File(nomeDocumento));
    }

}
