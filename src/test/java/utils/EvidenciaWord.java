package utils;

import core.CommonsBasePage;
import core.DriverFactory;
import io.cucumber.java.Scenario;
import io.cucumber.java.lv.Un;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EvidenciaWord extends DriverFactory {

    XWPFDocument documentoWord = new XWPFDocument();
    XWPFRun run = null;
    DateFormat dataEvidencia = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
    XWPFTable tabela = null;
    XWPFParagraph paragrafoWord = null;

    public void abreDocumento(){
        XWPFParagraph paragrafoWord = documentoWord.createParagraph();
        run = paragrafoWord.createRun();
    }

    public void criarCabecalho(){
        XWPFHeader cabecalho = documentoWord.createHeader(HeaderFooterType.DEFAULT);
        paragrafoWord = cabecalho.createParagraph();
        run = paragrafoWord.createRun();
    }

    public void inserirConteudoCabecalho() throws IOException, InvalidFormatException {
        paragrafoWord.setAlignment(ParagraphAlignment.LEFT);
        FileInputStream LogoEveris = new FileInputStream("src/test/resources/support/logo.png");
        run.addPicture(LogoEveris, XWPFDocument.PICTURE_TYPE_PNG, "logoEveris", Units.toEMU(100), Units.toEMU(80));
    }


    public void criarTabela(){
        tabela = documentoWord.createTable();
        tabela.setWidth(9000);
    }

    public void insereConteudoTabela(Scenario scenario, String nomeQa){
        XWPFTableRow primeiraLinha = tabela.getRow(0);
        primeiraLinha.getCell(0).setText("Nome do cenário");
        primeiraLinha.addNewTableCell().setText(scenario.getName());

        XWPFTableRow segundaLinha = tabela.createRow();
        segundaLinha.getCell(0).setText("Nome do QA");
        segundaLinha.getCell(1).setText(nomeQa);

        XWPFTableRow quartaLinha = tabela.createRow();
        quartaLinha.getCell(0).setText("Plataforma de execução");
        quartaLinha.getCell(1).setText(getDriver().getPlatformName());
    }

    public void testeStatus(Scenario scenario){
        criarTabela();
        XWPFTableRow tabelaStatus = tabela.getRow(0);
        tabelaStatus.getCell(0).setText("resultado do teste");
        tabelaStatus.addNewTableCell().setText(scenario.getStatus().toString());
    }

    public void criarParagrafo(){
        paragrafoWord = documentoWord.createParagraph();
        run = paragrafoWord.createRun();
        run.addBreak();
    }

    public void salvarScreenshotWord(String nomeScreenshot) throws IOException, InvalidFormatException {
        criarParagrafo();
        tirarScreenshot("screenshot" + dataEvidencia.format(new Date()));
        FileInputStream caminhoImagens = new FileInputStream("src/test/resources/evidencias/screenshots/" + nomeScreenshot + ".png");
        run.addPicture(caminhoImagens, XWPFDocument.PICTURE_TYPE_PNG, null, Units.toEMU(200), Units.toEMU(450));
        run.addBreak();
    }

    public void inserirComentarioScreenshot(Scenario scenario){
        tabela = null;
        criarTabela();
        XWPFTableRow primeiraLinha = tabela.getRow(0);
        primeiraLinha.getCell(0).setText("Observações");
        if (scenario.isFailed())
        {
            primeiraLinha.addNewTableCell().setColor("FF0000");
            primeiraLinha.getCell(1).setText("O passo falhou");
        }
        else
        {
            primeiraLinha.addNewTableCell().setText("O passo foi feito com sucesso");
        }


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

}
