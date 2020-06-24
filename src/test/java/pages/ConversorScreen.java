package pages;

import core.CommonsBasePage;
import io.appium.java_client.MobileBy;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ConversorScreen extends CommonsBasePage {

    public void validaConversorCarregado(){
        assertTrue(getDriver().findElement(MobileBy.AccessibilityId("Gaveta de navegação abrir")).isDisplayed());
    }

    public void clicarSpinnerEsquerdo(){
        clicarElemento(MobileBy.id("from_units_spinner"));
    }

    public void clicarSpinnerDireito(){
        clicarElemento(MobileBy.id("to_units_spinner"));
    }

    public void selecionarOpcaoMedida(String medida){
        scrollAteElemento(0.10, 0.90);
        getDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='"+medida+"']")).click();
    }

    public void digitarValorTeclado(String numero){
        for(int i = 0; i < numero.length(); i++)
        {
            getDriver().findElement(MobileBy.xpath("//android.widget.Button[@text='"+numero.charAt(i)+"']")).click();
        }
    }

    public void validarConversaoSucesso(String valorEsperado){
        String valorConvertido = getDriver().findElement(MobileBy.id("target_value")).getText();
        assertEquals(valorEsperado, valorConvertido);
    }

}
