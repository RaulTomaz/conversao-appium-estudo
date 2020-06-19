package stepDefinitions;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

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
}
