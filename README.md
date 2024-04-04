<div align="center">
  <h1>Gestão Vagas API</h1>
  <p>Gestão Vagas API é uma API Rest, que foi desenvolvida com de gerenciamento para cadastramento de empresa e suas vagas de emprego e candidatos para o preenchimento das mesmas. Com possibilidades de cadastramento de vagas, aplicação do candidato e filtro de busca pelo mesmo, exibição do currículo do candidato e listagem de informações das vagas. Desenvolvido utilizando as tecnologias Java e seu framework Spring, com banco de dados MySQL e H2 Database e cobertura de testes com JUnit, tornando a aplicação flexível e robusta.</p>
</div>

# 📒 Índice
* [Descrição](#descrição)
* [Requisitos Funcionais](#requisitos)
  * [Features](#features)
* [Tecnologias](#tecnologias)
* [Endpoints](#endpoints)
* [Instalação](#instalação)
* [Licença](#licença)

# 📃 <span id="descrição">Descrição</span>
Gestão Vagas API é uma API Rest, que foi desenvolvida com de gerenciamento para cadastramento de empresa e suas vagas de emprego e candidatos para o preenchimento das mesmas. Com possibilidades de cadastramento de vagas, aplicação do candidato e filtro de busca pelo mesmo, exibição do currículo do candidato e listagem de informações das vagas. Desenvolvido utilizando as tecnologias [**Java**](https://www.java.com/) e o framework [**Spring**](https://spring.io/), com banco de dados [**MySQL**](https://www.mysql.com/) e [**H2 Database**](https://www.h2database.com/) e cobertura de testes com [**JUnit**](https://junit.org/junit5/), autenticação usando o [**Spring Security**](https://spring.io/projects/spring-security), token [**JWT**](https://jwt.io/), encriptação de senhas utilizando [**BCrypt**](https://en.wikipedia.org/wiki/Bcrypt) e documentação desenvolvida pelo [**Swagger**](https://swagger.io/), tornando a aplicação flexível e robusta.

# 📌 <span id="requisitos">Requisitos Funcionais</span>
- [x] Realizar o cadastro de um usuário representando a empresa<br>
- [x] Cadastro de vaga por um usuário com perfil de empresa<br>
- [x] Realizar o cadastro do usuário com perfil de candidato<br>
- [x] Listar o currículo da usuário com perfil de candidato<br>
- [x] Listar as vagas disponíveis para o usuário com perfil de candidato por um filtro<br>
- [x] Aplicar o usuário com perfil de candidato a uma vaga<br>

## Features
- [x] Autenticação de usuário utilizando JWT Token<br>
- [x] Exibição de informações do próprio usuário em sessão ativa<br>
- [x] Adicionando mapeamento de CORS<br>
- [x] Modelo de domínio complexo<br>
- [x] Projeção com SQL nativo<br>
- [x] Cobertura de testes<br>
- [x] Docker-compose com imagem para um banco de dados PostgreSQL<br>

# 💻 <span id="tecnologias">Tecnologias</span>
- **Java**
- **Spring**
- **Spring Web**
- **Spring Boot DevTools**
- **Spring Data JPA**
- **Spring Bean Validation**
- **JUnit**
- **JWT**
- **BCrypt**
- **Swagger**
- **MySQL**
- **PostgreSQL**
- **H2 Database**
- **Docker**

# 📍 <span id="endpoints">Endpoints</span>
| Endpoint               | Resumo                                          | Autenticação
|----------------------|-----------------------------------------------------|----------------------
| <kbd>POST /companies/auth </kbd> | Responsável por autenticar o usuário com perfil de empresa, gerando o Bearer Token *JWT* | Sim
| <kbd>POST /companies </kbd> | Responsável por realizar o cadastro de um usuário representando a empresa | Não
| <kbd>POST /companies/jobs </kbd> | Responsável por realizar o cadastro de vaga por um usuário com perfil de empresa | COMPANY
| <kbd>POST /candidates/auth </kbd> | Responsável por autenticar o usuário com perfil de candidato, gerando o Bearer Token *JWT* | Sim
| <kbd>GET /candidates </kbd> | Responsável por listar o currículo da usuário com perfil de candidato | CANDIDATE
| <kbd>POST /candidates </kbd> | Responsável por realizar o cadastro do usuário com perfil de candidato | Não
| <kbd>GET /candidates/jobs </kbd> | Responsável por listar as vagas disponíveis para o usuário com perfil de candidato informando um *query param* como filtro | CANDIDATE
| <kbd>POST /candidates/jobs/apply </kbd> | Responsável por aplicar o usuário com perfil de candidato a uma vaga, informando o ID | CANDIDATE
| <kbd>GET /swagger-ui/index.html </kbd> | Responsável por servir a documentação dos recursos da API
| <kbd>GET /h2-console </kbd> | Responsável por acesso ao *H2 Database*

# 🚀 <span id="instalação">Instalação</span>
```bash
  # Clone este repositório:
  $ git clone https://github.com/CleilsonAndrade/gestao-vagas-api.git
  $ cd ./gestao-vagas-api

  # Instalar as dependências:
  $ mvn clean install

  # Executar:
  $ mvn spring-boot:run
```

# 📝 <span id="licença">Licença</span>
Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">
  Feito com 💜 by CleilsonAndrade
</p>
