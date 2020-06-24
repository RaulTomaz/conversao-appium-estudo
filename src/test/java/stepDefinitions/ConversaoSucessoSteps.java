package stepDefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.ConversorScreen;

public class ConversaoSucessoSteps {

    ConversorScreen conversorScreen = new ConversorScreen();

    @Dado("que o aplicativo esta na tela de conversao")
    public void que_o_aplicativo_esta_na_tela_de_conversao() {
        conversorScreen.validaConversorCarregado();
    }

    @Quando("seleciono a opcao de centimetros")
    public void seleciono_a_opcao_de_centimetros() throws InterruptedException {
        conversorScreen.clicarSpinnerEsquerdo();
        Thread.sleep(2500);
        conversorScreen.selecionarOpcaoMedida("Centímetro");
    }

    @Quando("seleciono a opcao de metros")
    public void seleciono_a_opcao_de_metros() throws InterruptedException {
        conversorScreen.clicarSpinnerDireito();
        Thread.sleep(2500);
        conversorScreen.selecionarOpcaoMedida("Metro");

    }

    @Quando("digito {string} pelo teclado de numeros")
    public void digito_mil_pelo_teclado_de_numeros(String numero) {
        conversorScreen.digitarValorTeclado(numero);
    }

    @Entao("a conversao de centimetros para metros e exibida com o valor {string}")
    public void a_conversao_de_centimetros_para_metros_e_exibida_com_sucesso(String resultadoConversao) {
        conversorScreen.validarConversaoSucesso(resultadoConversao);
    }

}