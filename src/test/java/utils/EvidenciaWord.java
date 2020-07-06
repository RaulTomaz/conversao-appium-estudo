package utils;

import core.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class EvidenciaWord extends DriverFactory {

    public void tirarScreenshot(String nomeEvidencia){
        try {
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("src/test/resources/evidencias/screenshots/" + nomeEvidencia + ".png"));
        }catch (IOException e){
            e.getCause();
        }

    }

}
