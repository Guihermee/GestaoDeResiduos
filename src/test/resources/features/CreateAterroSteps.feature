# language: pt
Funcionalidade: Cadastro de um novo Aterro
  Como usuário da API
  Quero cadastrar um novo Aterro
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido do Aterro
    Dado que eu tenha os seguintes dados do Aterro:
      | campo          | valor             |
      | qtdAtual       | 50                |
      | qtdAterro      | 120               |
      | nmLocalizacao  | Rua Amantes 256   |
      | stCapacidade   | true              |
    Quando eu enviar a requisição para o endpoint "api/aterros" de cadastro de Aterro
    Então o status code da resposta deve ser 201

    Cenario: Cadastro de Aterro sem sucesso ao passar o campo nmLocalizacao invalido
      Dado que eu tenha os seguintes dados do Aterro:
        | campo          | valor             |
        | qtdAtual       | 50                |
        | qtdAterro      | 120               |
        | nmLocalizacao  | teste             |
        | stCapacidade   | true              |
      Quando eu enviar a requisição para o endpoint "api/aterros" de cadastro de Aterro
      Então o status code da resposta deve ser 400
      E O corpo de resposta de erro da API deve retornar a mensagem "Nome da localização é muito curto!"