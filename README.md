# Comandos git

git init

git status

git add .

git commit -m "Estrutura inicial do projeto"

git log


## Exercício: adicionar as sequintes validações utilizando Exceptions:

- O nome do usuário deve conter ao menos 3 caracteres.
`- O telefone do usuário deve conter ao menos 10 caracteres.
`- O CPF deve conter 11 digitos.
- O e-mail deve conter @

Para resolver esse exercício:
- crie uma nova exceção chamada DadosUsuarioInvalidosException
- crie um método valida() que irá receber o usuário e fazer ifs para verificar os campos.
- para cada problema de validação do usuário, dispare a exceção com throw new.
- altere o main do programa ou menu apropriado para tratar a exceção lançada (try/catch).

TODO corrigir.

Exercício: Envio de mensagens!

Trata do caso de envio de mensagens dos usuários para os atendentes!

Regras de envio da mensagem:

- A dataHoraEnvio deve ser criada pelo próprio Java durante a inserção.
- A mensagem deve conter de 5 a 200 caracteres. - Levanta a mão quando concluir

Regras de login:
- O usuário deverá informar o ID do usuario para acessar esse chat. Com esse ID o programa irá "logar" o usuário e dar acesso ao chat.
- Após logar, será exibida a lista de atendentes. O usuário deverá escolher um pelo ID.
- O Remetente será o nome do usuário que está enviando (usuário).
- O Destinatário será o nome do atendente que receberá a mensagem.

Regra de listagem de mensagens: (extra)
- Não gravar o nome, mas sim o id do remetente e destinatario
- O usuário só pode visualizar as mensagens onde ele é "remetente"
