package utils;

import core.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EvidenciaWord extends DriverFactory {

    XWPFDocument documentoWord = new XWPFDocument();
    XWPFRun run = null;
    DateFormat dataEvidencia = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");

    public void abreDocumento(){
        XWPFParagraph paragrafoWord = documentoWord.createParagraph();
        run = paragrafoWord.createRun();
    }

    public void salvarScreenshotWord(String nomeScreenshot) throws IOException, InvalidFormatException {
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

}
