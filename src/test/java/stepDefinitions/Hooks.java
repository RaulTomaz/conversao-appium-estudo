package stepDefinitions;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.WordEvidence;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Hooks {
    DriverFactory driverFactory = new DriverFactory();
    WordEvidence wordEvidence = new WordEvidence();
    DateFormat dataEvidencia = new SimpleDateFormat("dd-MM-yyy_HH_mm_ss");

    @Before
    public void inicializarDriver() throws Exception{
        driverFactory.inicializarDriver();
        wordEvidence.criandoCabecalho();
        wordEvidence.inserindoConteudoCabecalho();
        wordEvidence.quebrandoParagrafo();
        wordEvidence.criandoTituloDocumento();
    }

    @After
    public void eliminarDriver() throws IOException{
        wordEvidence.salvaDocumento("conversao-appium" + dataEvidencia.format(new Date()));
        wordEvidence.fechaDocumento();
        driverFactory.getDriver().quit();


    }

    @AfterStep
    public void tirarScreenshot(Scenario scenario){
//        if (scenario.isFailed())
//        {
//            byte[] screenshot = ((TakesScreenshot) driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot, "image/png", scenario.getName());
//        }
        wordEvidence.salvaImagem("screenshot" + dataEvidencia.format(new Date()));

    }

}
