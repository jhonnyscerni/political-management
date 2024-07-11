# Sistema de Gestão Política

## Visão Geral
O Sistema de Gestão Política é projetado para otimizar a gestão de entidades políticas e seus papéis associados. Este sistema é construído usando Java com Spring Boot, aproveitando SQL para gerenciamento de dados e gerenciado através do Maven para dependências do projeto.

## Tecnologias
- **Java**: Linguagem de programação principal.
- **SQL**: Usado para gerenciamento de banco de dados e consultas.
- **Spring Boot**: Framework para criar aplicações Spring autônomas e de grau de produção.
- **Maven**: Gerenciamento de dependências.

## Funcionalidades
O sistema oferece um conjunto abrangente de funcionalidades para gerenciar usuários e seus papéis dentro de um contexto político. As principais funcionalidades incluem:

### Gerenciamento de Usuários
- **Criar Usuário Pessoa**: Registrar um novo usuário como pessoa física ou jurídica.
- **Atualizar Usuário**: Atualizar informações do usuário.
- **Deletar Usuário**: Remover um usuário do sistema.
- **Encontrar Usuário por ID**: Recuperar detalhes do usuário pelo seu identificador único.
- **Buscar Usuários**: Encontrar usuários com base em filtros específicos.
- **Redefinir Senha**: Permite aos usuários redefinir sua senha.
- **Associação de Papel ao Usuário**: Conectar ou desassociar papéis de/para usuários.

### Gerenciamento de Papéis
- **Conectar Papel**: Associar um papel a um usuário.
- **Desassociar Papel**: Remover uma associação entre um usuário e um papel.

## Endpoints
O sistema expõe vários endpoints RESTful para interagir com as funcionalidades

![Swagger-UI.png](src%2Fmain%2Fresources%2Fimg%2FSwagger-UI.png)

## Começando
Para começar com o Sistema de Gestão Política, certifique-se de ter o Java e o Maven instalados. Clone o repositório, navegue até o diretório do projeto e execute o seguinte comando para iniciar a aplicação:

```bash
mvn spring-boot:run

