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
import com._3ksystema.funcoes.FuncoesProduto;
import com._3ksystema.funcoes.FuncoesVenda;
import com._3ksystema.modelos.Carne;
import com._3ksystema.modelos.Cliente;
import com._3ksystema.modelos.Empresa;
import com._3ksystema.modelos.Venda;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
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
    private FuncoesProduto fp = new FuncoesProduto();

    //Carnê Jorge
/*    
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
            cel1.setFixedHeight(200);
            cel1.setFixedHeight(200);
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
     */
    //Carne Leonardo
    public void criaCarne(Venda venda) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnes(venda.getIdVenda());
        cliente = fc.pesquisaClienteCodigo(venda.getCliente());
        Empresa empresa = fep.pesquisaEmpresa();
        DecimalFormat df = new DecimalFormat("#,###.00");
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(30, 30, 20, 20);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
        Image imagem = Image.getInstance("D:\\_3kSystemasSolucoes\\imagens\\logomini.png");
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(18);
            PdfPTable subTabela = new PdfPTable(17);
            PdfPTable corpoTabela = new PdfPTable(17);
            tabela.setWidthPercentage(100);
            tabela.setPaddingTop(0);
            subTabela.setPaddingTop(0);
            subTabela.setWidthPercentage(100);
            corpoTabela.setWidthPercentage(100);
            corpoTabela.setPaddingTop(0);
            PdfPCell cel1 = new PdfPCell();
            PdfPCell sCel1 = new PdfPCell(imagem);
            PdfPCell sCel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()
                    + "\n" + empresa.getRuaEmpresa() + "\nBairro: " + empresa.getBairroEmpresa() + " Cidade: " + empresa.getCidadeEmpresa() + " - " + empresa.getEstadoEmpresa()
                    + "\nCNPJ: " + empresa.getCnpjEmpresa()
                    + "\nEmail: " + empresa.getEmailEmpresa(), font1));
            PdfPCell sCel3 = new PdfPCell(new Paragraph("Codigo:\n" + carne.getIdCarne()));
            sCel1.setPadding(0);
            sCel1.setColspan(4);
            sCel2.setPadding(0);
            sCel2.setColspan(10);
            sCel3.setColspan(3);
            sCel3.setPadding(0);
            subTabela.addCell(sCel1);
            subTabela.addCell(sCel2);
            subTabela.addCell(sCel3);
            PdfPCell cel2 = new PdfPCell();
            cel1.setPadding(0);
            cel1.setRowspan(2);
            cel2.setPadding(0);
            cel2.setColspan(17);
            cel2.addElement(subTabela);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            sCel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento(), font1));
            sCel2 = new PdfPCell(new Paragraph("LOCAL DE PAGAMENTO\n"
                    + "PAGAVEL SOMENTE EM NOSSA LOJA", font1));
            sCel3 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento(), font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(2);
            sCel2.setColspan(10);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("(=) Valor da Parcela:\nR$: " + df.format(carne.getValorParcela()), font1));
            sCel2 = new PdfPCell(new Paragraph("Nome do Cliente:\nFrancisco Mariano de Albuquerque Junior", font1));
            sCel3 = new PdfPCell(new Paragraph("CPF:\n" + cliente.getCpf_Cliente(), font1));
            PdfPCell sCel4 = new PdfPCell(new Paragraph("(=) Valor da Parcela:\nR$: " + df.format(carne.getValorParcela()), font2));
            sCel2.setBorder(0);
            sCel3.setBorder(0);
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(2);
            sCel2.setColspan(7);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            sCel4.setColspan(3);
            sCel4.setPadding(2);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            corpoTabela.addCell(sCel4);
            sCel1 = new PdfPCell(new Paragraph("Nº Parcela / Total Parcelas\n" + carne.getNumeroParcela() + " / " + carnes.size(), font1));
            sCel2 = new PdfPCell(new Paragraph("Endereço:\n" + cliente.getRua_Casa_Cliente() + " Nº " + cliente.getNumero_Casa_Cliente()
                    + "\nBairro: " + cliente.getBairro_Casa_Cliente() + " - Cidade/Estado: " + cliente.getCidade_Casa_Cliente() + "/" + cliente.getEstado_Casa_Cliente(), font1));
            sCel3 = new PdfPCell(new Paragraph("Nº Parcela / Total Parcelas\n" + carne.getNumeroParcela() + " / " + carnes.size(), font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(0);
            sCel2.setColspan(10);
            sCel2.setRowspan(2);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("Valor da Compra:\nR$: " + df.format(venda.getValorVenda()), font1));
            sCel3 = new PdfPCell(new Paragraph("Valor da Compra:\nR$: " + df.format(venda.getValorVenda()), font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("Valor Pago:\nR$: ___________", font1));
            sCel2 = new PdfPCell(new Paragraph("Detalhes da Compra:\n", font1));
            sCel3 = new PdfPCell(new Paragraph("Valor Pago:\nR$: ___________", font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(0);
            sCel2.setColspan(10);
            sCel2.setRowspan(2);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", font1));
            sCel3 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel3);
            cel1 = new PdfPCell();
            cel2 = new PdfPCell();
            cel1.setPadding(0);
            cel2.setPadding(0);
            cel2.setColspan(17);
            cel2.addElement(corpoTabela);
            //tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 15)));
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 15)));
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
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
  /*
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
*/
    //Reimpressão carnê Leonardo
    public void reimprimirCarnes(int idCliente) throws ClassNotFoundException, FileNotFoundException, DocumentException, IOException, SQLException {
        carnes = fcn.listaCarnesNPagos(idCliente);
        cliente = fc.pesquisaClienteCodigo(idCliente);
        Empresa empresa = fep.pesquisaEmpresa();
        DecimalFormat df = new DecimalFormat("#,###.00");
        Document documento = new Document();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(30, 30, 20, 20);
        String nomeCarne = "carnê " + cliente.getApelido_Cliente() + ".pdf";
        PdfWriter.getInstance(documento, new FileOutputStream(nomeCarne));
        Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
        Image imagem = Image.getInstance("D:\\_3kSystemasSolucoes\\imagens\\logomini.png");
        documento.open();
        for (Carne carne : carnes) {
            PdfPTable tabela = new PdfPTable(18);
            PdfPTable subTabela = new PdfPTable(17);
            PdfPTable corpoTabela = new PdfPTable(17);
            tabela.setWidthPercentage(100);
            tabela.setPaddingTop(0);
            subTabela.setPaddingTop(0);
            subTabela.setWidthPercentage(100);
            corpoTabela.setWidthPercentage(100);
            corpoTabela.setPaddingTop(0);
            PdfPCell cel1 = new PdfPCell();
            PdfPCell sCel1 = new PdfPCell(imagem);
            PdfPCell sCel2 = new PdfPCell(new Paragraph(empresa.getNomeEmpresa()
                    + "\n" + empresa.getRuaEmpresa() + "\nBairro: " + empresa.getBairroEmpresa() + " Cidade: " + empresa.getCidadeEmpresa() + " - " + empresa.getEstadoEmpresa()
                    + "\nCNPJ: " + empresa.getCnpjEmpresa()
                    + "\nEmail: " + empresa.getEmailEmpresa(), font1));
            PdfPCell sCel3 = new PdfPCell(new Paragraph("Codigo:\n" + carne.getIdCarne()));
            sCel1.setPadding(0);
            sCel1.setColspan(4);
            sCel2.setPadding(0);
            sCel2.setColspan(10);
            sCel3.setColspan(3);
            sCel3.setPadding(0);
            subTabela.addCell(sCel1);
            subTabela.addCell(sCel2);
            subTabela.addCell(sCel3);
            PdfPCell cel2 = new PdfPCell();
            cel1.setPadding(0);
            cel1.setRowspan(2);
            cel2.setPadding(0);
            cel2.setColspan(17);
            cel2.addElement(subTabela);
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            sCel1 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento(), font1));
            sCel2 = new PdfPCell(new Paragraph("LOCAL DE PAGAMENTO\n"
                    + "PAGAVEL SOMENTE EM NOSSA LOJA", font1));
            sCel3 = new PdfPCell(new Paragraph("Vencimento:\n" + carne.getDataVencimento(), font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(2);
            sCel2.setColspan(10);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("(=) Valor da Parcela:\nR$: " + df.format(carne.getValorParcela()), font1));
            sCel2 = new PdfPCell(new Paragraph("Nome do Cliente:\nFrancisco Mariano de Albuquerque Junior", font1));
            sCel3 = new PdfPCell(new Paragraph("CPF:\n" + cliente.getCpf_Cliente(), font1));
            PdfPCell sCel4 = new PdfPCell(new Paragraph("(=) Valor da Parcela:\nR$: " + df.format(carne.getValorParcela()), font2));
            sCel2.setBorder(0);
            sCel3.setBorder(0);
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(2);
            sCel2.setColspan(7);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            sCel4.setColspan(3);
            sCel4.setPadding(2);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            corpoTabela.addCell(sCel4);
            sCel1 = new PdfPCell(new Paragraph("Nº Parcela / Total Parcelas\n" + carne.getNumeroParcela() + " / " + carnes.size(), font1));
            sCel2 = new PdfPCell(new Paragraph("Endereço:\n" + cliente.getRua_Casa_Cliente() + " Nº " + cliente.getNumero_Casa_Cliente()
                    + "\nBairro: " + cliente.getBairro_Casa_Cliente() + " - Cidade/Estado: " + cliente.getCidade_Casa_Cliente() + "/" + cliente.getEstado_Casa_Cliente(), font1));
            sCel3 = new PdfPCell(new Paragraph("Nº Parcela / Total Parcelas\n" + carne.getNumeroParcela() + " / " + carnes.size(), font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(0);
            sCel2.setColspan(10);
            sCel2.setRowspan(2);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("Valor da Compra:\nR$: " + df.format(venda.getValorVenda()), font1));
            sCel3 = new PdfPCell(new Paragraph("Valor da Compra:\nR$: " + df.format(venda.getValorVenda()), font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("Valor Pago:\nR$: ___________", font1));
            sCel2 = new PdfPCell(new Paragraph("Detalhes da Compra:\n", font1));
            sCel3 = new PdfPCell(new Paragraph("Valor Pago:\nR$: ___________", font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel2.setPadding(0);
            sCel2.setColspan(10);
            sCel2.setRowspan(2);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel2);
            corpoTabela.addCell(sCel3);
            sCel1 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", font1));
            sCel3 = new PdfPCell(new Paragraph("Pago em:\n____/____/______", font2));
            sCel1.setPadding(2);
            sCel1.setColspan(4);
            sCel3.setPadding(2);
            sCel3.setColspan(3);
            corpoTabela.addCell(sCel1);
            corpoTabela.addCell(sCel3);
            cel1 = new PdfPCell();
            cel2 = new PdfPCell();
            cel1.setPadding(0);
            cel2.setPadding(0);
            cel2.setColspan(17);
            cel2.addElement(corpoTabela);
            //tabela.addCell(cel1);
            tabela.addCell(cel2);
            documento.add(tabela);
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 15)));
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 15)));
            documento.add(new Paragraph("\n", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
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
