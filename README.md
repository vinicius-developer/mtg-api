# mtg-api


### Ideia
Esse é um desafio que fiz esses dias utilizando java com Spring Boot
e postrgres como banco de dados.

O tema do desafio era o jogo chamada Magic the Gathering ou **MTG**. Caso não conheça 
é um jogo de cartas mundialmente conhecido. Neste jogo de cartas é comum, jogadores 
terem as suas listas cartas sejam para vender, trocar ou jogar.

Então a ideia do desafio foi basicamente construir um CRUD basico onde o usuario
poderia criar uma lista adicionar as suas cartas e também observar as listas dos seus
amigos sem poder modifica-lás.

### Deficuldades

Fiz esse desafio num período bem intenso da minha vida, onde a minha atenção estava
distribuida entre diversas coisas, porém a maior dificuldade tecnica que eu tive foi a quntidade 
de informação que eu precisei procurar para fazer com que ele ficasse aceitável.

### Configuração 

Essa api utiliza ***docker*** e ***docker-compose*** para criar para prevenir que seja
necessário baixar programas no seu computador. Peço que caso não seja possível 
configurar o projeto entre em contato.

Todas as configurações de dev-ops estão no arquivo devOps menos o arquivo 
___docker-compose.yaml___ e para configura basta seguir os seguintes passos: 

Para configurar o projeto basta rodas o comando ***docker-compose up***

### Documentação

Rotas HTTP para acessar a API

#### Criação de Usuario

**Criar Usuario** </br>
Rota: /user/create </br>
Método: POST </br>
Body:
`{ "email": "email@hotmail.com", "password": "Aa@123456", "username":  }` </br>

___email:___ Precisa ser compativel com um e-mail padrão. Não deve ter
mais de um e-mail repetido

___password:___ A senha precisa ter no minimo um caractere maiusculo, 
um minusculo, um número e um caractere especial.

___username:___ O apelido que será utilizado no sistema, não é possível 
exister mais de um username repetido no sistema.

Resposta: </br> 
Caso tudo tenha ocorrido bem a API retorna o status ___HTTP 201
CREATED___ 

**Login Usuario** </br>
Rota: /user/login </br>
Método: POST </br>
Body: `{"email": "email@hotmail.com", "password": "senha"}`

___email:___ e-mail cadastro do sistema anteriormente

___password:___ senha cadastrado no sistema anteriormente

Resposta: </br>
Caso tudo tenha acontecido bem ocorrido bem será
retornado o seguinte retorno.

`{ "token": "<jwt>", "type": "Bearer" }`

___token:___ Token **JWT** gerado pelo sistema

___type:___ Tipo de header onde o **JWT** deve ser armazenado

#### Criação de Lista

**Criação de Lista** </br>
Rota: /list/create </br>
Método: POST </br>
Body: `{ "name": "nome_da_lista" }` </br>

___name:___ Nome da lista

Resposta: </br>
Caso tudo tenha ocorrido bem será retornado o status ___HTTP 201 CREATED___

E o header ___Location___ com o caminho para acessar o arquivo

**Procurar de Lista** </br>
Rota: /list/find/{id_lista} -> Id a lista passado através da rota
Método: GET </br>

Resposta: </br>
Caso tudo tenha acontecido bem o status será ___HTTP 200 OK___

O corpo da resposta será algo parecido com isso: </br>

`
{"ownerName": "nome_do_dono", "listName": "nome_da_lista", 
"cards": [ {"id": "id_da_lista", "name": "nome_da_lista", 
"edition": "nome_da_edicao", "language": "nome_da_liguagem"
"price": "preco_da_carta", "amount": "qauntidade_de_cartas_na_lista",
"foil": "carta_e_foil"} ...]}
`

**Deletar Lista** </br>
Rota: /list/delete/{id_lista} -> Id a lista passado através da rota
Método: DELETE </br>

Resposta: </br>
Caso tudo tenha acontecido bem o status será ___HTTP 200 OK___

**Atualizar Lista** </br>
Rota: /list/update/{id_lista} -> Id a lista passado através da rota </br>
Método: PATCH </br>
Body: `{"name": "nome_da_lista"}`

___name:___ Nome da lista que será atulizada

**Listas Listas do Sistema** </br>
Rota: /list/all?page= <br>

___page:___  número da página, precisar ser um número e começa
do zero

Método: GET </br>

#### Criação de Cartas

**Inserir Carta em Lista:** </br>
Rota: /card/insert </br>
Método: POST </br>
Body: `{"name": "Nome de lição", "edition": "Edição de Lista", 
"list": Id da lista, "language": "Linguagem da carta (portuguẽs, inglês, japones)"
"foil": "Se é foil ou não", "price": "Preço da carta"}`

Resposta: </br>
Se tudo ocorrer bem será retornado o status ___HTTP 201 CREATED___

**Remover Carta:** </br>
Rota: /card/delete/{id_da_carta}?quantity=

id_da_carta: Id da carta

quantity: quantidade de cartas que será apagadas da lista

Método: DELETE

Resposta: </br>
Se tudo ocorrer bem será retornado o status ___HTTP 200 OK___

**Autilizar Carta** </br>
Rota: /card/update/{id_da_carta} <- Id da carta </br>
Método: PUT </br>
Body: `{"name": "Nome de lição", "edition": "Edição de Lista",
"list": Id da lista, "language": "Linguagem da carta (portuguẽs, inglês, japones)"
"foil": "Se é foil ou não", "price": "Preço da carta"}` </br>

Reposta:
Se tudo ocorrer bem será retornado o status ___HTTP 200 OK___




