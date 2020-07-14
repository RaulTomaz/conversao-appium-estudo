package core;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CommonsBasePage extends DriverFactory{
    WebDriverWait wait = new WebDriverWait(getDriver(), TimeUnit.SECONDS.toSeconds(8));

    public void clicarElemento(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public void scrollAteElemento(double porcentagemInicialY, double porcentagemFinalY){
        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * porcentagemInicialY);
        int endY = (int) (size.height * porcentagemFinalY);
        PointOption<?> point = new PointOption<>();
        new TouchAction<>(getDriver()).press(point.withCoordinates(x, startY)).moveTo(point.withCoordinates(x, endY)).release().perform();
    }

    public void validarElementoApareceuNaTela(By by){
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed());
//        assertTrue(getDriver().findElement(MobileBy.AccessibilityId("Gaveta de navegação abrir")).isDisplayed());
    }

    public void validartextoIgual(String textoAtual, String textoEsperado){
        Assert.assertEquals(textoEsperado, textoAtual);
    }

}
