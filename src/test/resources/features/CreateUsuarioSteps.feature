# language: pt
Funcionalidade: Cadastro de um novo usuário
  Como um novo usuário da API
  Quero cadastrar uma nova conta
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados da entrada:
      | campo | valor          |
      | nome  | Teste          |
      | senha | 12345678       |
      | email | test@email.com |
      | role  | ADMIN          |
    Quando eu enviar a requisicao para o endpoint "auth/register" de cadastro de usuarios
    Então o status da resposta deve ser 201


  Cenário: Cadastro mal-sucedido de usuário
    Dado que eu tenha os seguintes dados da entrada:
      | campo | valor          |
      | nome  | Nicolas        |
      | senha | 1234           |
      | email | nick@email.com |
      | role  | ADMIN          |
    Quando eu enviar a requisicao para o endpoint "auth/register" de cadastro de usuarios
    Então o status da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "A senha deve conter de 6 a 25 caracteres"
