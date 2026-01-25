## API desenvolvida em Java + Quarkus + MongoDB, utilizando Arquitetura Hexagonal e conceitos de DDD.

### O sistema permite gerenciar:

- Clientes
- Produtos
- Vendedores
- Vendas
- Relatórios financeiros

### Tecnologias

- Java 21
- Quarkus
- MongoDB Atlas
- RESTEasy Reactive
- Hibernate Validator
- OpenAPI / Swagger
- Mockito
- REST Assured
- Maven

## Primeiros passos

#### Este projeto utiliza um arquivo .env para configuração do MongoDB. Crie um arquivo .env na raiz do projeto com o seguinte conteúdo:
``
MONGO_URL=mongodb+srv://USER:PASSWORD@cluster.mongodb.net/?retryWrites=true&w=majority
``
``MONGO_DATABASE=store
``


## Executando a aplicação (modo desenvolvimento)
```shell script
./mvnw quarkus:dev
```
### Aplicação disponível em:
``
http://localhost:8080
``
## Após subir a aplicação:
``
http://localhost:8080/q/swagger-ui
``

## Build do projeto
```shell script
./mvnw package
```

## Executar testes

```shell script
./mvnw test
```


