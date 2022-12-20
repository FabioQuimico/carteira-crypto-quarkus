# Carteira-Cryptos

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
- Banco de dados H2 / PostgreSQL

## 📐 Projeto da aplicação

Este projeto engloba todo controle e manutenção da base de clientes (simplificados, com apenas ID e nome), a base de criptomoedas (com os principais dados para as transações) e a carteira em si que é a vinculação de posse de criptomoedas para cada cliente, conforme abaixo:

- **Cliente:** possui identificação numérica(Long), nome (String) e conjunto de criptomoedas possuída
- **CryptoCliente:** possui a id do Cliente(Long), o código da criptomoeda(String) e a quantidade possuída (BigDecimal)

## ⚙️ Executando o projeto

### Profile Desenvolvimento (H2)

> **_ATENÇÃO_**: Renomear o arquivo .env.localhost para .env antes de prosseguir com os procedimentos a seguir:

1. Com o Docker rodando, subir microserviço [cotacao-crypto-api](https://github.com/AlexDamiao86/trabalho-microservices/tree/main/cotacao-crypto-api):

```bash
docker-compose -f docker-compose-cotacao.yml up -d --remove-orphans
```

2. Executar aplicação quarkus:

2.1. Usando quarkus

```bash
quarkus dev
```

2.2. Usando Maven

```bash
./mvnw compile quarkus:dev
```

> **_NOTA:_**  Neste profile, o banco de dados utilizado será o H2 já populado com algumas informações e será recriado a cada execução.

### Profile Produção (PostgreSQL)

> **_ATENÇÃO_**: Renomear o arquivo .env.docker para .env antes de prosseguir com os procedimentos a seguir:

1. Com o Docker rodando, subir aplicação, aplicação [cotacao-crypto-api](https://github.com/AlexDamiao86/trabalho-microservices/tree/main/cotacao-crypto-api), bancos de dados MySQL e Postgres. 

```bash
docker-compose up -d
```

Opcionalmente, poderá ser configurado o pgAdmin (Administração do PostgreSQL) para visualização dos dados persistidos no banco de dados.

- Acessar página web do pgAdmin:
  - [Aplicação pelo browser](http://localhost:16543)
  - Login/Email: pgadmin@email.com
  - Login/Password: senha

- Adicionar novo servidor (Add new Server):
  - (Aba General)
    - Name: postgres-bd
  - (Aba Connection)
    - Host: postgres
    - Port: 5432
    - Maintenance database: postgres
    - Username: postgres
    - Password: docker

- Acessar tabelas:
  - No canto lateral esquerdo, clicar sobre Servers > postgres-bd > Databases > carteira-cryptos > Schemas > public > Tables

## 🩺 Testando o projeto

Para facilitar a execução em profile Desenvolvimento, o banco de dados é alimentado durante a inicialização do projeto com:

- 4 Clientes (1, 2, 3 e 4)
- 3 Posses na carteira (Clientes 1 e 2)

### SWAGGER

A interface Swagger pode ser acessada no [browser](http://localhost:8080/q/swagger-ui/)

### Endpoints

#### Entidade CLIENTE

- GET /cliente/ => Retorna a lista de clientes
- GET /cliente/{id} => Retorna o cliente com o id informado em formato numerico
- POST /cliente => Salva um novo cliente na base passado por json
  ```json
  "nome": "{String}"
  ```
  
- DELETE /cliente/{id} => Exclui o cliente com a id informada
- PUT /cliente => Altera o cliente com o id informado por json conforme:
  ```json
  "id": {Numero},
  "nome": "{String}"
  ```

#### Entidade CRYPTO_CLIENTE

- POST /cliente/compra => Registra a compra da criptomoeda com {codigoCrypto} informado para o cliente de {idCliente} na {quantidade} passada por json conforme:
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
  
- GET /cliente/{idCliente}/cryptos => Lista todas as criptomoedas possuidas pelo cliente de {idCliente} numerico

## 👨🏽‍💻 Desenvolvedores

| [<img src="https://avatars.githubusercontent.com/AlexDamiao86" width=115><br><sub>Alexandre Damião Mendonça Maia</sub>](https://github.com/AlexDamiao86) |  [<img src="https://avatars.githubusercontent.com/FabioQuimico" width=115><br><sub>Fabio Ferreira dos Santos</sub>](https://github.com/FabioQuimico) |  [<img src="https://avatars.githubusercontent.com/Gabriel2503" width=115><br><sub>Gabriel Oliveira Barbosa</sub>](https://github.com/Gabriel2503) | [<img src="https://avatars.githubusercontent.com/ferreirabraga" width=115><br><sub>Rafael Braga da Silva Ferreira</sub>](https://github.com/ferreirabraga) |
| :---: | :---: | :---: | :---: |

>
>Projeto realizado como requisito parcial do MBA Full Stack Development - FIAP 2022
>