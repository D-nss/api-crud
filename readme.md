# Projeto API de Funcionários e Dependentes
Este projeto é uma aplicação Spring Boot que gerencia funcionários e seus dependentes. Ele inclui endpoints para criar, ler, atualizar e deletar funcionários e dependentes.

# Configuração e Execução

## Pré-requisitos

- Java JDK 17 ou superior
- Banco de dados PostgreSql
- Maven
- Docker (opcional, para execução em contêiner)

## Executando a Aplicação

## Localmente

1. Clone o repositório:

        git clone https://github.com/seu-repositorio/api-ti9.git

2. Navegue até o diretório do projeto:

         cd api-ti9
      
3. Execute a aplicação pelo Intellij

## Usando Docker

1. Construa a imagem Docker:

        docker build -t api-ti9 .

2. Execute o contêiner:

        docker run -p 3001:3001 api-ti9

# Configuração do DB

A configuração é feita no arquivo aplication.properties, nesse caso o DB tem como configuração:
- Nome do DB: postgres

        spring.datasource.url = jdbc:postgresql://localhost:5432/postgres
- User: postgres
  
        spring.datasource.username=postgres
- Senha: 1234

        spring.datasource.password=1234

# Endpoints Disponíveis

## Funcionários
- POST /funcionarios: Cria um novo funcionário.

        Exemplo JSON:

            {   
            "nome":"Daniel",
            "cpf":"22144398038",
            "cargo":"chefe",
            "salario":1200,
            "data_de_admissao":"2023-02-01",
            "dependentes":[{
            "data_de_nascimento":"2023-02-01",
            "nome":"Laura",
            "parentesco":"Marido"}
            ]
            }

- GET /funcionarios/{id}: Busca um funcionário por ID.


- GET /funcionarios/: Lista todos os funcionários.


- PUT /funcionarios/{id}: Atualiza um funcionário.


- DELETE /funcionarios/{id}: Deleta um funcionário.


## Dependentes
  
- POST /dependentes/{idFuncionario}: Cria um novo dependente para um funcionário.

        Exemplo JSON:

            {
            "data_de_nascimento":"2023-02-01",
            "nome":"Laura",
            "parentesco":"Marido"
            }

- GET /dependentes/{id}: Busca um dependente por ID.


- GET /dependentes: Lista todos os dependentes.


- PUT /dependentes/{id}: Atualiza um dependente.


- DELETE /dependentes/{id}: Deleta um dependente.


# Classes de DTO

- FuncionarioDTO: Utilizado para criar ou atualizar dados do funcionário.

- DependenteDTO: Utilizado para criar ou atualizar dados dos dependentes.

- FuncionarioResponseDTO: Deve incluir os dados do funcionário e a lista de seus dependentes (se houver).

# Exceções Personalizadas
O projeto inclui várias exceções personalizadas para lidar com erros específicos, como:
- CampoObrigatorio: Campo obrigatório não preenchido.
- DependenteNaoExiste: Dependente não encontrado.
- FuncionarioNaoExiste: Funcionário não encontrado.
- InvalidoCPF: CPF inválido.
- InvalidoDataAdmissao: Data de admissão inválida.
- InvalidoDataNascimento: Data de nascimento inválida.
- InvalidoNome: Nome inválido.
- InvalidoParentesco: Parentesco inválido.
- InvalidoSalario: Salário inválido.