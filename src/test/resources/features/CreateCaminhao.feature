# language: pt
Funcionalidade: Cadastro de novo caminhão
  Como usuário da API
  Quero cadastrar um novo caminhão
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de caminhão
    Dado que eu tenha os seguintes dados da entrega:
      | campo          | valor                 |
      | vlCapacidade   | 1000                  |
      | nmLocalizacao  | "Um lugar silencioso" |
    Quando eu enviar a requisição para o endpoint "api/caminhoes" de cadastro de caminhões
    Então o status code na resposta deve ser 201