# carteiracryptos Project

## 🎯 Objetivo

Sistema de controle de uma carteira de criptomoedas contendo uma base de clientes, criptomoedas, o registro de posse e a possibilidade de transações de compra e venda.

## 🛠️ Recursos utilizados

- Java JDK 17
- Quarkus
- Hibernate
- Panache
- Resteasy-Jackson
- GitHub
- OpenAPI - Swagger
- Banco de dados H2

## 📐 Projeto da aplicação

O sistema foi planejado para ser composto por 3 projetos: o frontend(não incluso aqui) para acesso e autenticação, o motor de execução e controle de transações e persistencia (este projeto) e uma API externa para controle e atualização das criptomoedas existentes no momento com suas cotações.

Este projeto engloba todo controle e manutenção da base de clientes (simplificados, com apenas ID e nome), a base de criptomoedas (com os principais dados para as transações) e a carteira em si que é a vinculação de posse de criptomoedas para cada cliente, conforme abaixo:

**Cliente:** possui identificação númerica(Long) e nome (String)

**Crypto:** possui código único(String), nome(String), valor de Compra(double) e valor de Venda(double)

**CryptoCliente:** possui a id do Cliente(Long), o código da criptomoeda(String) e a quantidade possuida (BigDecimal)

## ⚙️ Executando o projeto

Para execução em modo de desenvolvimento:

```shell script
./mvnw compile quarkus:dev
```

A geração do pacote da aplicação pode ser feito com:

```shell script
./mvnw package
```

> **_NOTA:_**  O Quarkus conta com uma Dev UI que pode ser acessada em http://localhost:8080/q/dev/.

## 🩺 Testando o projeto

Para fins de testes, o banco de dados é alimentado durante a inicilização do projeto com:

- 4 Clientes (1, 2, 3 e 4)

- 5 Criptomoedas (BTC, ETH, USDT, ADA, USDC)

- 1 Posse na carteira (Cliente: 1, Criptomoeda: BTC, quanitdade: 10)

A interface Swagger pode ser acessada em: http://localhost:8080/q/swagger-ui/

### Endpoints

#### Entidade CLIENTE

- GET /cliente/lista => Retorna toda a lista de clientes
- GET /cliente/{id} => Retorna o cliente com o id informado em fotmato numerico
- POST /cliente => Salva um novo clliente na base passado por json com o atributo:

  ```json
  "nome": "{String}"
  ```

- PATCH /cliente => Altera o cliente com o id informado por json conforme:

  ```json
  "id": {Numero},
  "nome": "{String}"
  ```

- POST /cliente/compra => Efetua a compra da criptomoeda com {codigoCrypto} informado para o cliente de {idCliente} na {quantidade} passada por json conforme:

  ```json
  "idCliente": {numero},
  "codigoCrypto": "{String}",
  "quantidade": {numero decimal}
  ```

- POST /cliente/venda => Efetua a venda da criptomoeda com {codigoCrypto} para o cliente de {idCliente} na {quantidade} passada por json conforme:

  ```json
  "idCliente": {numero},
  "codigoCrypto": "{String}",
  "quantidade": {numero decimal}
  ```

#### Entidade CRYPTO

- GET /crypto/lista => Lista todas as criptomoedas disponíveis para compra/venda
- GET /crypto/{codigo} => Mostra os dados da criptomoeda de codigo string informado
- POST /crypto => Cadastra uma nova criptomoeda com os dados informados por json conforme:

  ```json
  "codigo": "{String}",
  "nome": "{String}",
  "valorCompra": {numero decimal},
  "vallorVenda": {numero decimal}
  ```

- DELETE /crypto/{codigo} => Apaga a criptomoeda com {codigo} informado

#### Entidade CRYPTOCLIENTE

- GET /cryptocliente/lista => Lista todas as propriedades de criptomoedas de todos os usuário **APENAS PARA TESTES**
- GET /cryptocliente/{idCliente} => Lista todas as criptomoedas possuidas pelo cliente de {idCliente} numerico
- POST /cryptocliente => Cria uma nova posse de criptomoeda com os dados passados por json conforme:

  ```json
  "idCliente": {numero},
  "codigoCrypto": "{String}",
  "quantidade": {numero decimal}
  ```

## 👨🏽‍💻 Desenvolvedores

| [<img src="https://avatars.githubusercontent.com/AlexDamiao86" width=115><br><sub>Alexandre Damião Mendonça Maia</sub>](https://github.com/AlexDamiao86) |  [<img src="https://avatars.githubusercontent.com/FabioQuimico" width=115><br><sub>Fabio Ferreira dos Santos</sub>](https://github.com/FabioQuimico) |  [<img src="https://avatars.githubusercontent.com/Gabriel2503" width=115><br><sub>Gabriel Oliveira Barbosa</sub>](https://github.com/Gabriel2503) | [<img src="https://avatars.githubusercontent.com/ferreirabraga" width=115><br><sub>Rafael Braga da Silva Ferreira</sub>](https://github.com/ferreirabraga) | 
| :---: | :---: | :---: | :---: |

>
>Projeto realizado como requisito para conclusão da disciplina de _Quarkus_ do MBA Full Stack Development - FIAP 2022
>