# Agenda API

Aplicação desenvolvida com Springbooot

### Tecnologias
- `Java 11`
- `Springboot 2.3.3`
- `Spring Data JPA`
- `H2 Database`
- `Lombok`
- `Swagger`
- `Maven`

Estrutura dos packages

![](img/estrutura_camadas.png)

- `Configuration` - Encontra-se as classes de configuração da aplicação. Ex: Configuração de Cors, Swagger.
- `domain` - Contém as classes de domínio do negócio
- `entrypoint` - Ponto de entrada para o sistema, pode ser REST endpoints, Jobs.
- `gateway` - Faz a interface com sistemas externos
- `Repository` - Classes que fazem interface com os dados, buscam e armazenam informações
- `Service` - Classes de serviços

### Execução da Aplicação

- Prerequisitos
  - `Java JDK 11`
  - `Maven`

Execute o maven para buildar o projeto

`mvn clean install`

Execute a aplicação

`mvn spring-boot:run`

A `API` estarará executando na porta `8080`

Acesse `localhost:8080/swagger-ui.html` para vê a documentação swagger.
