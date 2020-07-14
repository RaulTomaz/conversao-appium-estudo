# language: pt

  Funcionalidade: Realizar uma conversão com sucesso

  Eu como usuário
  Gostaria de realizar uma conversão de medidas
  Para que eu possa verificar a diferença nas medidas

  Cenario: Realizar uma conversão de centímetros para metros
    Dado que o aplicativo esta na tela de conversao
    Quando seleciono a opcao de centimetros
    E seleciono a opcao de metros
    E digito "1000" pelo teclado de numeros
    Entao a conversao de centimetros para metros e exibida com o valor "11"