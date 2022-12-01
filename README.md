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

- **Cliente:** possui identificação númerica(Long) e nome (String)
- **Crypto:** possui código único(String), nome(String), valor de Compra(double) e valor de Venda(double)
- **CryptoCliente:** possui a id do Cliente(Long), o código da criptomoeda(String) e a quantidade possuida (BigDecimal)

## ⚙️ Executando o projeto

### Profile Desenvolvimento (H2)

1. Executar aplicação quarkus:

```bash
./mvnw compile quarkus:dev
```

> **_NOTA:_**  Neste profile, o banco de dados utilizado será o H2 e será recriado a cada execução. 


### Profile Produção (PostgreSQL)

1. Com o docker rodando, subir serviço PostgreSQL: 

```bash
docker-compose up -d
```

2. Executar aplicação quarkus: 

```bash
./mvnw compile quarkus:dev -Dquarkus.profile=prod
```

Opcionalmente, poderá ser configurado o pgAdmin (Administração do PostgreSQL) para visualização dos dados persistidos no banco de dados.

- Acessar página web do pgAdmin:
  * Endereço: http://localhost:16543
  * Login/Email: pgadmin@email.com
  * Login/Password: senha

- Adicionar novo servidor (Add new Server):
  - (Aba General)
    * Name: postgres-bd
  - (Aba Connection)
    * Host: postgres
    * Port: 5432
    * Maintenance database: postgres
    * Username: postgres
    * Password: docker

- Acessar tabelas: 
  * No canto lateral esquerdo, clicar sobre Servers > postgres-bd > Databases > carteira-cryptos > Schemas > public > Tables

  
## 🩺 Testando o projeto

Para facilitar a execução, o banco de dados é alimentado durante a inicialização do projeto com:

- 4 Clientes (1, 2, 3 e 4)
- 5 Criptomoedas (BTC, ETH, USDT, ADA, USDC)
- 1 Posse na carteira (Cliente: 1, Criptomoeda: BTC, quanitdade: 10)

### SWAGGER 

A interface Swagger pode ser acessada em: http://localhost:8080/q/swagger-ui/

### Endpoints

#### Entidade CLIENTE

- GET /cliente/lista => Retorna a lista de clientes
- GET /cliente/{id} => Retorna o cliente com o id informado em formato numerico
- POST /cliente => Salva um novo cliente na base passado por json com o atributo:

  ```json
  "nome": "{String}"
  ```

- PATCH /cliente => Altera o cliente com o id informado por json conforme:

  ```json
  "id": {Numero},
  "nome": "{String}"
  ```

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

#### Entidade CRYPTO

- GET /crypto/lista => Lista todas as criptomoedas disponíveis para compra/venda
- GET /crypto/{codigo} => Mostra dados da criptomoeda de codigo string informado
- POST /crypto => Cadastra uma nova criptomoeda com os dados informados por json conforme:

  ```json
  "codigo": "{String}",
  "nome": "{String}",
  "valorCompra": {numero decimal},
  "vallorVenda": {numero decimal}
  ```

- DELETE /crypto/{codigo} => Apaga a criptomoeda com {codigo} informado

#### Entidade CRYPTOCLIENTE

- GET /cryptocliente/lista => Lista todas as propriedades de criptomoedas de todos os usuários **APENAS PARA TESTES**
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