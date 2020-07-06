package stepDefinitions;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.EvidenciaWord;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Hooks {
    DriverFactory driverFactory = new DriverFactory();
    DateFormat dataEvidencia = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
    EvidenciaWord evidenciaWord = new EvidenciaWord();


    @Before
    public void inicializarDriver() {
        driverFactory.inicializarDriver();
    }

    @After
    public void eliminarDriver() {
        driverFactory.getDriver().quit();
    }

    @AfterStep
    public void tirarScreenshot(Scenario scenario) {
//        if (scenario.isFailed())
//        {
//            byte[] screenshot = ((TakesScreenshot) driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot, "image/png", scenario.getName());
//        }
        evidenciaWord.tirarScreenshot("screenshot" + dataEvidencia.format(new Date()));
    }

}
