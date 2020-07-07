package utils;

import core.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.*;
import java.math.BigInteger;

public class WordEvidence extends DriverFactory {

    XWPFDocument wordEvidence = new XWPFDocument();
    XWPFHeader cabecalho = null;
    XWPFParagraph paragrafo = null;
    XWPFRun run = null;
    XWPFTable tableOne = null;

    public void criandoCabecalho(){
        cabecalho = wordEvidence.createHeader(HeaderFooterType.DEFAULT);
        paragrafo = cabecalho.createParagraph();
        run = paragrafo.createRun();
    }

    public void inserindoConteudoCabecalho() throws Exception{
        paragrafo.setAlignment(ParagraphAlignment.RIGHT);
        FileInputStream teste = new FileInputStream("src/test/resources/evidencias/support/logo.png");
        run.addPicture(teste, XWPFDocument.PICTURE_TYPE_PNG, "teste", Units.toEMU(80), Units.toEMU(50));
    }

    public void quebrandoParagrafo(){
        run.addBreak();
    }

    public void criandoTituloDocumento(){
        paragrafo = wordEvidence.createParagraph();
        run = paragrafo.createRun();
        paragrafo.setAlignment(ParagraphAlignment.CENTER);
        run.setText("Grupo de estudos Appium com Java");
        run.setBold(true);
    }

    public void criaTabela(){
        tableOne = wordEvidence.createTable();
        CTTblWidth larguraTabela = tableOne.getCTTbl().addNewTblPr().addNewTblW();
        larguraTabela.setType(STTblWidth.DXA);
        larguraTabela.setW(BigInteger.valueOf(9000));
//        XWPFTableRow tableOneRowTwo = tableOne.createRow();
//        tableOneRowTwo.getCell(0).setText("Data1");
//        tableOneRowTwo.getCell(1).setText("Data2");
    }

    public void insereConteudoTabela(String chaveTexto, String valorTexto){
        XWPFTableRow tableOneRowOne = tableOne.getRow(0);
        tableOneRowOne.getCell(0).setText(chaveTexto);
        tableOneRowOne.addNewTableCell().setText(valorTexto);
    }

    public void salvaDocumento(String nomeDocumento) throws IOException {
        FileOutputStream os = new FileOutputStream(new File("src/test/resources/evidencias/"+ nomeDocumento + ".docx"));
        wordEvidence.write(os);
    }

    public void salvaImagem(String nomeImagem){
        try
        {
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("src/test/resources/evidencias/screenshots/"+ nomeImagem +".png"));
            FileInputStream is = new FileInputStream("src/test/resources/evidencias/screenshots/"+ nomeImagem + ".png");
//            XWPFParagraph paragraph = wordEvidence.createParagraph();
//            XWPFRun run = paragraph.createRun();
//            run.addBreak();
            run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, nomeImagem, Units.toEMU(200), Units.toEMU(350)); // 200x200 pixels
            is.close();
        } catch (IOException | InvalidFormatException exceptionBlocks)
        {
            exceptionBlocks.getCause();
        }


    }

    public void fechaDocumento() throws IOException {
        if (wordEvidence != null)
        {
            wordEvidence.close();
        }
        else
        {
            throw new IOException("Nenhum documento word foi iniciado");
        }

    }

}
