package utils;

import core.DriverFactory;
import io.cucumber.java.Scenario;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class EvidenciaWord extends DriverFactory {

    XWPFDocument documentoWord = new XWPFDocument();
    XWPFRun run = null;
    XWPFTable tabela = null;
    DateFormat dataEvidencia = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
    XWPFParagraph paragrafoWord = null;

    public void abreDocumento(){
        paragrafoWord = documentoWord.createParagraph();
        run = paragrafoWord.createRun();
    }
//
//    public void criarCabecalho(){
//        XWPFHeader cabecalho = documentoWord.createHeader(HeaderFooterType.DEFAULT);
//        paragrafoWord = cabecalho.createParagraph();
//        run = paragrafoWord.createRun();
//    }

//    public void inserirConteudoCabecalho() throws Exception {
//        paragrafoWord.setAlignment(ParagraphAlignment.LEFT);
//        FileInputStream logoEveris = new FileInputStream("src/test/resources/support/logo.png");
//        run.addPicture(logoEveris, XWPFDocument.PICTURE_TYPE_PNG, "logoEveris", Units.toEMU(130), Units.toEMU(80));
//    }



    public void criarTabela(){
        tabela = documentoWord.createTable();
        tabela.setWidth(9000);

//        CTTblWidth larguraTabela = tabela.getCTTbl().addNewTblPr().addNewTblW();
//        larguraTabela.setType(STTblWidth.DXA);
//        larguraTabela.setW(BigInteger.valueOf(9000));
    }

    public void insereConteudoTabela(Scenario scenario, String nomeQA){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy    HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        XWPFTableRow primeiraLinha = tabela.getRow(0);
        primeiraLinha.getCell(0).setText("Nome do cenário");
        primeiraLinha.addNewTableCell().setText(scenario.getName());

        XWPFTableRow segundaLinha = tabela.createRow();
        segundaLinha.getCell(0).setText("Status do teste");
        segundaLinha.getCell(1).setText(scenario.getStatus().toString());

        //QA
        XWPFTableRow terceiraLinha = tabela.createRow();
        terceiraLinha.getCell(0).setText("Nome do QA");
        terceiraLinha.getCell(1).setText(nomeQA);

        //Hora da execução
        XWPFTableRow quartaLinha = tabela.createRow();
        quartaLinha.getCell(0).setText("Hora da execução");
        quartaLinha.getCell(1).setText(dtf.format(now));

        //Plataforma
        XWPFTableRow quintaLinha = tabela.createRow();
        quintaLinha.getCell(0).setText("Plataforma de execução");
        quintaLinha.getCell(1).setText(getDriver().getPlatformName());
    }

    public void salvarScreenshotWord(String nomeScreenshot) throws IOException, InvalidFormatException {
        criarParagrafo();
        tirarScreenshot("screenshot" + dataEvidencia.format(new Date()));
        FileInputStream caminhoImagens = new FileInputStream("src/test/resources/evidencias/screenshots/" + nomeScreenshot + ".png");
        run.addPicture(caminhoImagens, XWPFDocument.PICTURE_TYPE_PNG, null, Units.toEMU(200), Units.toEMU(450));
        run.addBreak();
    }

    public void fecharDocumento(String nomeDocumento) throws IOException{
        FileOutputStream caminhoDocumento = new FileOutputStream("src/test/resources/evidencias/"+ nomeDocumento + ".docx");
        documentoWord.write(caminhoDocumento);
        documentoWord.close();
    }

    public void tirarScreenshot(String nomeEvidencia){
        try {
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("src/test/resources/evidencias/screenshots/" + nomeEvidencia + ".png"));
        }catch (IOException e){
            e.getCause();
        }
    }

    public void criarParagrafo(){
        paragrafoWord = documentoWord.createParagraph();
        run = paragrafoWord.createRun();
        run.addBreak();
    }

    public void quebrarParagrafo(){
        run.addBreak();
    }

}
