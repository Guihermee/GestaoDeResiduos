# language: pt
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de usuario
  Como usuário da API
  Quero cadastrar um novo usuario
  Para que eu cosniga validar se o contrato esta conforme o esperado
  Cenario: Validar contrato do cadastro bem-sucedido de usuario
    Dado que eu tenha os seguintes dados da entrada:
      | campo | valor          |
      | nome  | Teste          |
      | senha | 12345678       |
      | email | test@email.com |
      | role  | ADMIN          |
    Quando eu enviar a requisicao para o endpoint "auth/register" de cadastro de usuarios
    Então o status da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de usuario"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado