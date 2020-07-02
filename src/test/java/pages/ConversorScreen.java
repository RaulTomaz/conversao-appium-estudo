package pages;

import core.CommonsBasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class ConversorScreen extends CommonsBasePage {

    public void validaConversorCarregado(){
        validarElementoApareceuNaTela(MobileBy.AccessibilityId("Gaveta de navegação abrir"));
    }

    public void clicarSpinnerEsquerdo(){
        clicarElemento(MobileBy.id("from_units_spinner"));
    }

    public void clicarSpinnerDireito(){
        clicarElemento(MobileBy.id("to_units_spinner"));
    }

    public void selecionarOpcaoMedida(String medida){
        scrollAteElemento(0.10, 0.90);
        clicarElemento(MobileBy.xpath("//android.widget.TextView[@text='"+medida+"']"));
    }

    public void digitarValorTeclado(String numero){
        for(int i = 0; i < numero.length(); i++)
        {
            clicarElemento(MobileBy.xpath("//android.widget.Button[@text='"+numero.charAt(i)+"']"));
        }
    }

    public void validarConversaoSucesso(String valorEsperado){
        validartextoIgual(valorEsperado, getDriver().findElement(MobileBy.id("target_value")).getText());
    }

}
