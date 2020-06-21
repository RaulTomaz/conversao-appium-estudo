package stepDefinitions;

import core.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.Dimension;

public class ConversaoSucessoSteps extends DriverFactory {

    @Dado("que o aplicativo esta na tela de conversao")
    public void que_o_aplicativo_esta_na_tela_de_conversao() {
        Assert.assertTrue(getDriver().findElement(MobileBy.AccessibilityId("Gaveta de navegação abrir")).isDisplayed());
    }

    @Quando("seleciono a opcao de centimetros")
    public void seleciono_a_opcao_de_centimetros() throws InterruptedException {
        getDriver().findElement(MobileBy.id("from_units_spinner")).click();

        Thread.sleep(3500);
        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.10);
        int endY = (int) (size.height * 0.90);
        // explicar pq está com warning
        PointOption point = new PointOption();
        new TouchAction(getDriver()).press(point.withCoordinates(x, startY)).moveTo(point.withCoordinates(x, endY)).release().perform();

        getDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='Centímetro']")).click();
    }

    @Quando("seleciono a opcao de metros")
    public void seleciono_a_opcao_de_metros() throws InterruptedException {
        getDriver().findElement(MobileBy.id("to_units_spinner")).click();

        Thread.sleep(3500);
        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.10);
        int endY = (int) (size.height * 0.90);
        PointOption point = new PointOption();
        new TouchAction(getDriver()).press(point.withCoordinates(x, startY)).moveTo(point.withCoordinates(x, endY)).release().perform();

        getDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='Metro']")).click();
    }

    @Quando("digito mil pelo teclado de numeros")
    public void digito_mil_pelo_teclado_de_numeros() {
        getDriver().findElement(MobileBy.xpath("//android.widget.Button[@text='1']")).click();
        getDriver().findElement(MobileBy.xpath("//android.widget.Button[@text='0']")).click();
        getDriver().findElement(MobileBy.xpath("//android.widget.Button[@text='0']")).click();
        getDriver().findElement(MobileBy.xpath("//android.widget.Button[@text='0']")).click();
    }

    @Entao("a conversao de centimetros para metros e exibida com sucesso")
    public void a_conversao_de_centimetros_para_metros_e_exibida_com_sucesso() {

    }
}
