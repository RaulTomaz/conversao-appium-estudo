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

import java.io.*;

public class WordEvidence extends DriverFactory {

    XWPFDocument wordEvidence = new XWPFDocument();


    public void criandoParagrafo(){
        XWPFParagraph paragraph = wordEvidence.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("teste");
    }

    public void salvaDocumento(String nomeDocumento) throws FileNotFoundException, IOException {
        FileOutputStream os = new FileOutputStream(new File("src/test/resources/evidencias/"+ nomeDocumento + ".docx"));
        wordEvidence.write(os);
    }

    public void salvaImagem(String nomeImagem){
        try
        {
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("src/test/resources/evidencias/screenshots/"+ nomeImagem +".png"));
            FileInputStream is = new FileInputStream("src/test/resources/evidencias/screenshots/"+ nomeImagem + ".png");
            XWPFParagraph paragraph = wordEvidence.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.addBreak();
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
