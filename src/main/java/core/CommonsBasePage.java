package core;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class CommonsBasePage extends DriverFactory{

    public void clicarElemento(By by){
        getDriver().findElement(by).click();
    }

    public void scrollAteElemento(double porcentagemInicialY, double porcentagemFinalY){
        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * porcentagemInicialY);
        int endY = (int) (size.height * porcentagemFinalY);
        PointOption<?> point = new PointOption<>();
        new TouchAction<>(getDriver()).press(point.withCoordinates(x, startY)).moveTo(point.withCoordinates(x, endY)).release().perform();
    }

}
