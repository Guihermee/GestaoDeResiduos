# language: pt
Funcionalidade: Validar o contrato ao realizar um cadastro bem sucedido de caminhão
  Como usuário da API
  Quero cadastrar um novo caminhão
  Para que eu possa validar se o contrato esta conforme o esperado
  Cenário: Validar contrato do cadastro bem sucedido de caminhao
    Dado que eu tenha os seguintes dados do caminhão:
      | campo          | valor                 |
      | qtdAtual       | 2                     |
      | vlCapacidade   | 1000                  |
      | nmLocalizacao  | Um lugar silencioso   |
    Quando eu enviar a requisição para o endpoint "/caminhoes" de cadastro de caminhões
    Então o status code na resposta deve ser 201
    E que o arquivo de contrato de caminhao esperado é o "Cadastro bem-sucedido de caminhão"
    Então a resposta da requisição deve estar em conformidade com o contrato de caminhao selecionado