# language: pt
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de aterro
  Como usuário da API
  Quero cadastrar um novo aterro bem-sucedido
  Para que eu consiga validar se o contrato esta conforme o esperado
  Cenario: Validar contrato do cadastro bem-sucedido do aterro
    Dado que eu tenha os seguintes dados do Aterro:
      | campo          | valor             |
      | qtdAtual       | 50                |
      | qtdAterro      | 120               |
      | nmLocalizacao  | Rua Amantes 256   |
      | stCapacidade   | true              |
    Quando eu enviar a requisição para o endpoint "api/aterros" de cadastro de Aterro
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato de aterro esperado é o "Cadastro bem-sucedido de aterro"
    Então a resposta da requisição deve estar em conformidade com o contrato de aterro selecionado