# Desafio de Teste de API

<p align="center">
  <a href="https://github.com/josinaldogjunior/desafio-tecnico-agibank/actions/workflows/ci-test-api.yml">
    <img src="https://github.com/josinaldogjunior/desafio-tecnico-agibank/actions/workflows/ci-test-api.yml/badge.svg" alt="CI Pipeline Status">
  </a>
</p>

<p align="center">
  Automação de testes para a <a href="https://dog.ceo/dog-api/">Dog API</a>, desenvolvida como parte do processo seletivo de QA.
</p>

---

## 📊 Relatório de Testes (Allure)

O relatório detalhado da última execução dos testes está disponível publicamente através do GitHub Pages.

<p align="center">
  <a href="https://josinaldogjunior.github.io/desafio-tecnico-agibank/" target="_blank">
    <img src="https://img.shields.io/badge/Ver%20Relat%C3%B3rio%20de%20Testes-Allure-27AE60?style=for-the-badge&logo=allure-framework" alt="Link para o Relatório Allure">
  </a>
</p>

---

## 🧪 Cenários de Teste

A tabela abaixo detalha os cenários de teste automatizados neste projeto.

| ID | Classe de Teste   | Nome do Teste | Endpoint | Tipo | Resultado Esperado                                                      |
| :--- |:------------------| :--- | :--- | :--- |:------------------------------------------------------------------------|
| **CT001** | `BreedsTest`      | Deve Listar todas as raças com sucesso | `GET /breeds/list/all` | Positivo | Retorna `200 OK` com a lista de todas as raças.                         |
| **CT002** | `BreedsTest`      | Deve listar imagens de raça existente | `GET /breed/{breed}/images` | Positivo | Retorna `200 OK` com a lista de imagens da raça solicitada.             |
| **CT003** | `BreedsTest`      | Não deve listar imagens com raça inexistente | `GET /breed/{breed}/images` | Negativo | Retorna `404 Not Found` com uma mensagem de erro apropriada.            |
| **CT004**| `RandomImageTest` | Deve listar imagens randômicas | `GET /breeds/image/random` | Positivo | Retorna `200 OK` com a URL de uma imagem aleatória.                     |
| **CT005**| `RandomImageTest` | Deve listar uma quantidade específica de imagens randômicas | `GET /breeds/image/random/50` | Limite | Retorna `200 OK` com uma lista contendo exatamente 50 imagens.          |
| **CT006**| `RandomImageTest` | Deve validar o comportamento ao enviar mais de 50 imagens | `GET /breeds/image/random/51` | Comportamento | Retorna `200 OK` com a lista limitada a 50 imagens, tratando o excesso. |

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Gerenciador de Dependências:** Gradle
* **Testes de API:** Rest-Assured
* **Framework de Testes:** JUnit 5
* **Relatórios:** Allure Report
* **CI/CD:** GitHub Actions

---

## 📁 Estrutura do Projeto

```
desafio-tecnico-agibank/
├── .github/
│   └── workflows/
│       └── ci-test-api.yml                    # Pipeline de CI/CD
├── build/                      
├── gradle/
├── src/
│   └── test/
│       └── java/
│           └──.../
│               ├── base/
│               │   └── BaseTest.java          # Classe base para testes
│               └── tests/
│                   ├── BreedsTest.java        # Classe de Testes
│                   └── RandomImageTest.java   # Classe de Testes
├── .gitignore                                 # Arquivos ignorados pelo Git
├── build.gradle                               # Arquivo de configuração do Gradle
└── README.md                                  # Documentação do projeto
```

---

## ⚙️ Pré-requisitos

Para executar este projeto localmente, você precisará ter instalado:
- Java (JDK 17 ou superior)
- Gradle (o projeto utiliza o Gradle Wrapper)
- Allure Commandline (para visualizar os relatórios localmente)


---

## 🚀 Como Executar Localmente

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/josinaldogjunior/desafio-tecnico-agibank.git
    cd desafio-tecnico-agibank
    ```

2.  **Execute os testes:**
    O projeto utiliza o Gradle Wrapper, que dispensa a instalação manual do Gradle.
    ```bash
    # No Windows
    gradlew.bat clean test

    # No Linux ou macOS
    ./gradlew clean test
    ```

3.  **Gere e visualize o Relatório Allure:**
    ```bash
    # Gera o relatório
    ./gradlew allureReport

    # Abre o relatório no navegador
    ./gradlew allureServe
    ```

---

## 🔄 Pipeline de Integração Contínua (CI)

Este projeto utiliza o **GitHub Actions** para executar os testes automaticamente. O pipeline é acionado nos seguintes eventos:
* A cada `push` na branch `main`.
* A cada `pull request` para a branch `main`.
* Diariamente, de forma agendada.
* Manualmente, através da aba "Actions" do repositório.

A cada execução bem-sucedida na branch `main`, o relatório de testes é automaticamente atualizado e publicado no GitHub Pages.

---

## 🕵️ Descobertas e Observações da API

Durante a automação, foram identificados os seguintes comportamentos na API que divergem de uma expectativa padrão:

* **Endpoint `GET /breeds/image/random/{count}`:**
    * **Cenário de `count` > 50:** Ao solicitar um número de imagens superior ao limite de 50 (ex: 51), a API não retorna um erro. Em vez disso, ela limita o resultado a **50 imagens** e retorna um status `200 OK`.
    * **Cenário de `count` = 0:** Da mesma forma, ao solicitar 0 imagens, a API não retorna um erro. Ela retorna `200 OK` com **1 imagem** aleatória.

Foram criados testes de comportamento específicos para validar e documentar estas regras de negócio da API.
