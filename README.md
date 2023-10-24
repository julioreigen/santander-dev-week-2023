# **Santander Dev Week 2023**

**RESTful API da Santander Dev Week 2023 construída em Java 17 com Spring Boot 3.**

**Descrição**

Este repositório contém uma API de gerenciamento de usuarios de um banco desenvolvida durante a Santander Dev Week 2023. A API fornece um conjunto de endpoints para realizar operações básicas, como:

* Criação de conta de usuário
* Atualizar usuário
* Deletar usuário
* Listar todos os usuários
* Consultar um usuário


**API rodando online:** 
<br>
```https://juliocodes.tech/``` 
<br>
**Interface SWAGGER da API online:**
<br>
[https://juliocodes.tech/swagger-ui/index.html](https://juliocodes.tech/swagger-ui/index.html)



<div align="center">

**Tecnologias**

[![Java 17](https://img.shields.io/badge/-Java%2017-000?style=for-the-badge&logo=openjdk&logoColor=Ff7f00)](https://docs.oracle.com/en/java/)
[![Spring Boot 3](https://img.shields.io/badge/Springboot%203-000?style=for-the-badge&logo=springboot&logoColor=00FF00)](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-000?style=for-the-badge&logo=spring&logoColor=00FF00)](https://spring.io/projects/spring-data-jpa)
[![Swagger](https://img.shields.io/badge/Swagger-000?style=for-the-badge&logo=swagger&logoColor=00FF00)](https://swagger.io/)
[![Railway](https://img.shields.io/badge/Railway-000?style=for-the-badge&logo=railway&logoColor=FFFFFF)](https://https://railway.app)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-000?style=for-the-badge&logo=postgresql&logoColor=Add8e6)](https://www.postgresql.org/)

</div>

## Diagrama de Classes

```mermaid
classDiagram
  class User {
    - name: String
    - account: Account
    - features: List<Feature>
    - creditCard: CreditCard
    - hints: List<Hint>
  }

  class Account {
    - agency: String
    - number: String
    - balance: BigDecimal
    - limit: BigDecimal
  }

  class Feature {
    - icon: String
    - description: String
  }

  class CreditCard {
    - number: String
    - limit: BigDecimal
  }

  class Hint {
    - icon: String
    - description: String
  }

  User "1" *-- "1" Account
  User "1" *-- "N" Feature
  User "1" *-- "1" CreditCard
  User "1" *-- "N" Hint
```

**Instalação**

Para instalar a API, siga estas etapas:

1. Clone o repositório:

```
git clone https://github.com/julioreigen/santander-dev-week-2023.git
```

2. Entre na pasta do projeto:

```
cd santander-dev-week-2023
```

3. Crie um arquivo `application.properties` na pasta `src/main/resources` com o seguinte conteúdo para banco de dados H2:

```
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    username: julio
    password: 123
  jpa:
    show-sql: true
    open-in-view: false
    hibernate:
      ddl-auto: create # validate / update / create / create-drop
    properties:
      hibernate:
        format_sql: true
  h2:
    console:
      enabled: true
      path: /h2
      settings:
        trace: false
        web-allow-others: false
```

4. Construa a imagem do Docker:

```
docker build -t santander-dev-week-2023:latest .
```

5. Execute a imagem do Docker:

```
docker run -p 8080:8080 santander-dev-week-2023:latest
```

A API estará disponível em http://localhost:8080.

**Documentação**

A documentação da API está disponível no endereço [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

**Exemplos**

Aqui estão alguns exemplos de como usar a API:

**Criar uma conta**

```
POST /users
```

Corpo da requisição:

```
{
  "name": "Julio Brito",
  "account": {
    "number": "000b0000-9",
    "agency": "0001",
    "balance": 1504.22,
    "limit": 1000.00
  },
  "creditCard": {
    "number": "xxxx xxxx xxxx 0022",
    "limit": 1000.00
  },
  "hints": [
    {
      "icon": "https://seusite.com/invest.png",
      "description": "Veja nossas novas formas de investimento"
    }
  ],
  "features": [
    {
      "icon": "https://seusite.com/pagar.png",
      "description": "Pagar"
    },
    {
      "icon": "http://seusite.com/pix.png",
      "description": "Pix"
    }
  ]
}
```


**Consultar usuário**

```
GET /users/{id}
```

Parâmetros:

* `id`: ID do usuário


**Consultar todos os usuários**

```
GET /users
```
