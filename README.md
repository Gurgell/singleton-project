# Sistema de Gerenciamento de Produtos - Desafio Técnico

Este projeto é uma implementação de um sistema de gerenciamento de produtos, com funcionalidades básicas de CRUD (Create, Read, Update, Delete) em Java SE 11. O sistema utiliza o padrão de projeto Singleton para a classe de serviço e um repositório para o gerenciamento dos dados dos produtos. Os testes unitários foram implementados utilizando JUnit 4 e Mockito.

## Requisitos

### Testes
 Implementar testes unitários para cada operação do `ProdutoService` utilizando `JUnit 4` e `Mockito 3.6`.
### Funcionalidades

O sistema deve implementar as seguintes operações:

1. **Adicionar Produto**: Método para adicionar um novo produto com ID, nome e preço.
2. **Buscar Produto por ID**: Método para retornar um produto com base no seu ID.
3. **Atualizar Produto**: Método para atualizar o nome e preço de um produto existente.
4. **Deletar Produto**: Método para remover um produto com base no ID.
5. **Listar Produtos**: Método para listar todos os produtos.

### Estrutura do Projeto

- **Produto**: Classe que representa um produto, com `id`, `nome`, e `preco` como atributos.
- **ProdutoService**: Classe de serviço responsável pelo gerenciamento dos produtos, implementada como Singleton e utilizando um `ProdutoRepositoryImpl` para manipular os dados.
- **ProdutoRepository**: Interface para operações básicas de CRUD.
- **ProdutoRepositoryImpl**: Implementação da interface `ProdutoRepository`, que armazena os dados dos produtos em uma `ArrayList`.


## Decisões de Implementação
### Classe Produto
Optei por criar o `ID` do produto utilizando um `UUID`, facilitando a geração de IDs únicos para cada produto.

### Classe `Singleton` - ProdutoService
Foi criada uma variável estática chamada `instance` e ela armazena a única instância de `ProdutoService`. Popular esta variável é trabalho do método `getInstance()`, que quando é chamado verifica se já existe uma instância de `ProdutoService` na variável `instance`, caso exista ele retorna esta instância e caso não, ele cria, popula a variável e a retorna da mesma forma. Eu criei o método `setProdutoRepository()` para que eu pudesse injetar manualmente um `mock` de `ProdutoRepositoryImpl` na hora de realizar os testes unitários. Isso foi necessário porque como o padrão `Singleton` restringe a criação de sua instância apenas via um método como o `getInstance()`, acaba não sobrando nenhuma forma de injetar `Mocks` e com isso os testes acabariam tendo que ser executados com uma instância real do repositório.

### Testes

Os testes unitários foram implementados utilizando o framework JUnit 4 e a biblioteca Mockito para mockar o `ProdutoRepositoryImpl`. Os seguintes testes foram cobertos:

- A adição de um produto chama o método `salvar` no repositório.
- A busca por ID retorna o produto correto.
- A atualização de um produto existente modifica o nome e o preço corretamente.
- A tentativa de atualizar um produto inexistente lança uma exceção `ProdutoNaoEncontradoException`.
- A exclusão de um produto chama o método `deletar` no repositório.
- A listagem de produtos retorna todos os produtos adicionados.

### Maven
O projeto foi configurado para utilizar o Maven como ferramenta de build automation, porém, todas dependências foram adicionadas manualmente conforme as instruções fornecidas.

### Dependências
Além das dependências principais (`junit-4.13.2`,`mockito-core-3.6.0`), foi necessário buscar mais três dependências transitivas: `byte-buddy-1.12.11`, `hamcrest-core-1.3`,`objenesis-2.6`. Quando utiliza-se o `pom.xml` como gerenciador das dependências, ele automaticamente baixa todas as dependências transitivas com base nas dependências diretas. No entanto, ao gerenciar as dependências manualmente, essa tarefa fica a cargo do desenvolvedor.

### Menu
Foi criado um menu para o usuário interagir com a aplicação, facilitando para que todas as funcionalidades do sistema sejam testadas. Utilizei a biblioteca `Scanner` para que o programa leia os inputs do usuário.

## Como Rodar o Projeto

1. Clone o repositório.
2. Compile o projeto com Maven:
    ```bash
    mvn clean install
    ```
3. Para rodar a aplicação, execute a classe `Main`:
    ```bash
    mvn exec:java -Dexec.mainClass="org.example.Main"
    ```

