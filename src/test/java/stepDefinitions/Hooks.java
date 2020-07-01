package stepDefinitions;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {
    DriverFactory driverFactory = new DriverFactory();


    @Before
    public void inicializarDriver() {
        driverFactory.inicializarDriver();
    }

    @After
    public void eliminarDriver() {
        driverFactory.getDriver().quit();
    }

    @AfterStep
    public void tirarScreenshot(Scenario scenario){
        if (scenario.isFailed())
        {
            byte[] screenshot = ((TakesScreenshot) driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png", scenario.getName());
        }

    }

}
