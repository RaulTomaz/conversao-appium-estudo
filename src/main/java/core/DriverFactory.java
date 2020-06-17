package core;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static AndroidDriver<MobileElement> driver;

    public void inicializarDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus");
        capabilities.setCapability("appPackage", "com.ba.universalconverter");
        capabilities.setCapability("appActivity", "com.ba.universalconverter.MainConverterActivity");
        try{
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }catch (MalformedURLException malformedUrl){
            malformedUrl.printStackTrace();
        }

    }

    public AndroidDriver<MobileElement> getDriver(){
        return driver;
    }


}
