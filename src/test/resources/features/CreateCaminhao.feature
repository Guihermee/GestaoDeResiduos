# language: pt
Funcionalidade: Cadastro de novo caminhão
  Como usuário da API
  Quero cadastrar um novo caminhão
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de caminhão
    Dado que eu tenha os seguintes dados do caminhão:
      | campo          | valor                 |
      | qtdAtual       | 2                     |
      | vlCapacidade   | 1000                  |
      | nmLocalizacao  | Um lugar silencioso   |
    Quando eu enviar a requisição para o endpoint "/caminhoes" de cadastro de caminhões
    Então o status code na resposta deve ser 201

  Cenário: Cadastro de caminhão sem sucesso ao passar o campo vlCapacidade inválido
    Dado que eu tenha os seguintes dados do caminhão:
      | campo          | valor                 |
      | qtdAtual       | 2                     |
      | vlCapacidade   | 19                     |
      | nmLocalizacao  | Aqu                   |
    Quando eu enviar a requisição para o endpoint "/caminhoes" de cadastro de caminhões
    Então o status code na resposta deve ser 400
    E o corpo de resposta de erro do caminhão da api deve retornar a mensagem "Campo deve conter no minimo 4 caracteres"