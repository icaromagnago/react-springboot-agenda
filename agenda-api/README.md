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

Para a criação da agenda foi utilizado o padrão strategy, pois para cada tipo de agenda o seu padrão de criação muda.

![](img/strategy.png)

A única particularidade é em relação ao strategy do fórum. O fórum possui dois strategy, o `CriaAgendaForumStrategy` tem a responsabilidade de cria a agenda no sistema e na api do fórum, já o `CriaAgendaForumDaApiStrategy` tem a responsabilidade de cria no sistema as agendas obtidas da api do fórum.

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
