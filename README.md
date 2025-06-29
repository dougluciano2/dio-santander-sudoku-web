# 🎮 Jogo de Sudoku Web (Bootcamp DIO - Santander 2025)

[![Java](https://img.shields.io/badge/Java-21-blue.svg?logo=java)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.1-brightgreen.svg?logo=spring)](https://spring.io/projects/spring-boot)
[![Spring MVC](https://img.shields.io/badge/Spring%20MVC-Web-6DB33F.svg?logo=spring-framework)](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-005C0F.svg?logo=thymeleaf)](https://www.thymeleaf.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36.svg?logo=apache-maven)](https://maven.apache.org/)


## ✨ Sobre o Projeto

Este projeto consiste na reimplementação e modernização de um jogo de Sudoku, originalmente desenvolvido em Java Swing (desktop), para uma **aplicação web utilizando o framework Spring Boot com Spring MVC e Thymeleaf**. O objetivo principal foi migrar a lógica de negócio existente para um ambiente web, proporcionando uma interface de usuário acessível via navegador.

O jogo permite:
* Exibir um tabuleiro de Sudoku pré-definido.
* Inserir números nas células editáveis.
* Limpar o conteúdo das células editáveis.
* Reiniciar o tabuleiro para o estado inicial.
* Verificar o status atual do jogo (incompleto, completo, com ou sem erros, baseado na solução final).
* Concluir o jogo, verificando se foi resolvido corretamente.

## 🚀 Tecnologias Utilizadas

* **Java 21:** Linguagem de programação.
* **Spring Boot 3.3.1:** Framework para desenvolvimento rápido de aplicações Java.
* **Spring MVC:** Módulo do Spring para construção de aplicações web baseadas no padrão Model-View-Controller.
* **Thymeleaf:** Motor de templates para renderização de HTML no lado do servidor.
* **Lombok:** Biblioteca para reduzir código boilerplate (getters, setters, construtores).
* **Maven:** Ferramenta de automação de build e gerenciamento de dependências.
* **HTML5, CSS3:** Para a estrutura e estilização da interface web.

## 💡 Arquitetura

A aplicação segue uma arquitetura em camadas, com uma clara separação de responsabilidades:

* **`controller`**: Responsável por receber as requisições HTTP do navegador, interagir com a camada de serviço e preparar os dados para as views (Thymeleaf).
* **`service`**: Contém a lógica de negócio do jogo Sudoku (`BoardService`) e o sistema de notificação de eventos (`NotifierService`).
* **`models`**: Define as entidades de domínio do jogo (`Board`, `Space`, `GameStatusEnum`).
* **`config`**: Classes de configuração do Spring, incluindo a inicialização do tabuleiro de Sudoku a partir de uma string de dados.
* **`resources/templates`**: Contém os arquivos HTML (Thymeleaf) que definem a interface do usuário.
* **`resources/static`**: Contém arquivos estáticos como CSS para estilização.

## ▶️ Como Executar o Projeto

Para executar este projeto em sua máquina local, siga os passos abaixo:

### Pré-requisitos

Certifique-se de ter instalado:

* **Java Development Kit (JDK) 21** ou superior.
* **Apache Maven 3.8+**.
* Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

### Passos

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/dougluciano2/dio-santander-sudoku-web.git](https://github.com/dougluciano2/dio-santander-sudoku-web.git)
    ```
2.  **Navegue até o Diretório do Projeto:**
    ```bash
    cd dio-santander-sudoku-web
    ```
3.  **Construa o Projeto com Maven:**
    ```bash
    mvn clean install
    ```
    Isso irá baixar todas as dependências e compilar o projeto.
4.  **Execute a Aplicação Spring Boot:**
    Você pode executar a aplicação de algumas maneiras:
    * **Via Maven:**
        ```bash
        mvn spring-boot:run
        ```
    * **Via IDE:** Abra o projeto em sua IDE e execute a classe principal `br.com.dougluciano.sudoku_web.Application` (que contém o método `main`).

5.  **Acesse a Aplicação no Navegador:**
    Após a aplicação iniciar (você verá logs indicando que o Tomcat iniciou na porta 8080), abra seu navegador e acesse:
    ```
    http://localhost:8080/sudoku/play
    ```

## 🎮 Como Jogar (Teste de Usabilidade)

O jogo de Sudoku exige que cada linha, coluna e bloco 3x3 contenha os números de 1 a 9 sem repetição. O tabuleiro inicial possui números fixos (com fundo cinza), que não podem ser alterados.

Para testar as funcionalidades da aplicação:

1.  **Fazer uma Jogada:**
    * Clique em uma célula **vazia** do tabuleiro.
    * Digite um número de `1` a `9`.
    * Pressione `Enter` ou clique fora da célula.
    * A página irá recarregar e a célula mostrará o novo valor. Uma mensagem de sucesso será exibida.

2.  **Limpar uma Célula:**
    * Clique em uma célula que você preencheu.
    * Digite `0` (zero) no campo.
    * Pressione `Enter` ou clique fora da célula.
    * A célula ficará vazia e uma mensagem de sucesso será exibida.

3.  **Tentar Editar Célula Fixa:**
    * Tente clicar e digitar em uma célula com fundo cinza (que já tem um número).
    * Você não conseguirá editar a célula, e uma mensagem de erro indicará que é uma posição fixa.

4.  **Reiniciar o Jogo:**
    * Clique no botão "**Reiniciar Jogo**".
    * O tabuleiro será resetado para sua configuração inicial.

5.  **Verificar Status:**
    * Clique no botão "**Verificar**".
    * Uma mensagem informará o status atual do jogo (se está incompleto/completo e se há erros, baseado na solução final).

6.  **Concluir Jogo:**
    * Clique no botão "**Concluir Jogo**".
    * O sistema verificará se o tabuleiro está completamente preenchido e sem erros (comparando com a solução predefinida). Mensagens de sucesso ou de falha serão exibidas.

## 🔑 Solução do Tabuleiro (Para Teste de Conclusão)

Para testar a funcionalidade de "Concluir Jogo" com sucesso, você precisará preencher o tabuleiro com a seguinte solução:

|       |       |       |       |       |       |       |       |       |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
| 4     | 7     | 9     | 5     | 8     | 6     | 2     | 3     | 1     |
| 1     | 3     | 5     | 4     | 7     | 2     | 8     | 9     | 6     |
| 2     | 6     | 8     | 9     | 1     | 3     | 7     | 4     | 5     |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
| 5     | 1     | 3     | 7     | 6     | 4     | 9     | 8     | 2     |
| 8     | 9     | 7     | 1     | 2     | 5     | 3     | 6     | 4     |
| 6     | 4     | 2     | 3     | 9     | 8     | 1     | 5     | 7     |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
| 7     | 5     | 4     | 2     | 3     | 9     | 6     | 1     | 8     |
| 9     | 8     | 1     | 6     | 4     | 7     | 5     | 2     | 3     |
| 3     | 2     | 6     | 8     | 5     | 1     | 4     | 7     | 9     |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|


Após preencher todas as células com estes valores e clicar em "Concluir Jogo", você deverá ver a mensagem de sucesso.

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir "issues" ou "pull requests" para melhorias.

