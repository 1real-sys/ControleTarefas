# Controle de Tarefas (TaskManager)

Projeto simples de gerenciamento de tarefas com **Spring Boot (API REST)** no back-end e foco de integração com **Angular** no front-end.

## Objetivo

Construir uma aplicação de tarefas completa, cobrindo operações CRUD, validações de negócio e integração real entre front-end e back-end.

## Requisitos do Sistema

O sistema deve permitir:

- Cadastrar uma tarefa
- Listar todas as tarefas
- Editar uma tarefa
- Excluir uma tarefa
- Marcar tarefa como concluída

Cada tarefa deve ter:

- **Título**
- **Descrição**
- **Data de criação**
- **Status** (`PENDENTE` ou `CONCLUÍDA`)

## Regras de Negócio

- ❌ Não pode cadastrar tarefa sem título
- ❌ Título deve ter no mínimo 3 caracteres
- ✔ Toda tarefa começa como `PENDENTE`
- ✔ Quando marcar como concluída, o status vira `CONCLUÍDA`
- ❌ Não pode editar ou excluir tarefa que não existe
- ✔ Data de criação é gerada automaticamente pelo sistema (back-end)

## O que você aprende neste projeto

- **Angular**: formulário, listagem, botões de ação e consumo de API
- **Spring Boot**: entidade, controller, service e validações
- **Integração REST real** entre front-end e back-end

## Stack do projeto

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- MySQL (driver configurado no projeto)
- Maven

## Estrutura atual do repositório

```text
src/
  main/
    java/dev/teamwin/controletarefa/
      ControleTarefaApplication.java
    resources/
      application.properties
  test/
    java/dev/teamwin/controletarefa/
      ControleTarefaApplicationTests.java
```

## Como executar (back-end)

Pré-requisitos:

- Java 21+
- Maven (ou usar o wrapper `./mvnw`)

Comandos:

```bash
./mvnw spring-boot:run
```

Para rodar testes:

```bash
./mvnw test
```

---

Se quiser, posso também montar a próxima versão deste README já com os endpoints REST (`POST/GET/PUT/DELETE/PATCH`) e exemplos de payload.
