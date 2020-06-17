package stepDefinitions;

import core.DriverFactory;
import org.junit.After;
import org.junit.Before;

public class Hooks {
    DriverFactory driverFactory = new DriverFactory();

    @Before
    public void inicializarDriver(){
        driverFactory.inicializarDriver();
    }

    @After
    public void eliminarDriver(){
        driverFactory.getDriver().quit();
    }
}
