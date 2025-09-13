# Laboratório — Integração com Banco de Dados (Java + JDBC)

Professor: Luiz Ricardo Mantovani da Silva

Bem-vindo(a)! Este laboratório prático de JDBC com SQLite foi preparado para você aprender, de forma guiada, como conectar, criar esquema, inserir, consultar, atualizar, remover e trabalhar com transações em Java.

Ambiente recomendado: VS Code + Java Extension Pack + Maven. Usamos exclusivamente SQLite (sem servidor).

Wiki para iniciantes (Java/JDK/JVM, OO, JDBC e SQLite): veja a [Wiki do GitHub](https://github.com/LuizRMSilva1973/IntegracaoBDJava/wiki).

## Instruções rápidas
- Entrar no módulo: `cd lab-jdbc`
- Compilar: `mvn package`
- Executar cada exercício: `mvn exec:java -Dexec.mainClass=<classe>`

Exemplos de `<classe>` (use o nome completo do pacote):
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ConexaoDB
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.CriaSchema
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ResetDB
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.InsereClientes
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ListaClientes
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.AtualizaCliente
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.RemoveCliente
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.PedidoTransacional
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.MainDAO
```

## Como inspecionar o banco (ver o que mudou)
- Opção GUI: abra o arquivo `lab-jdbc/loja.db` no DB Browser for SQLite (ou ferramenta similar) para ver tabelas e dados.
- Opção via código (sem instalar nada): use a classe `DumpDB` para listar todas as tabelas e seus registros:
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.DumpDB
```

## Ciclo rápido de validação
Reiniciar → inserir → inspecionar:
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ResetDB
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.InsereClientes
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.DumpDB
```

## 1) Pré‑requisitos
- Java 17+ (`java -version`).
- Maven 3.8+ (`mvn -version`).
- Opcional: DB Browser for SQLite (para inspecionar `loja.db`).

## 2) Obter e compilar o projeto
- No terminal, entre na pasta `lab-jdbc`.
- Compile:
```
mvn -q -DskipTests package
```
Se compilar sem erros, o ambiente está ok.

## 3) Banco de dados
- O arquivo do banco (SQLite) é `loja.db`, criado no diretório do projeto.
- A conexão (`ConexaoDB`) habilita `PRAGMA foreign_keys = ON` para respeitar FKs.

## 4) Execução dos exercícios (passo a passo)
Use o Exec Plugin do Maven e troque o nome da classe conforme indicado.

### Exercício 1 — “Hello, DB!”
Objetivo: abrir/fechar conexão com SQLite.
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ConexaoDB
```
Validação: `Conexão estabelecida!` e arquivo `loja.db` criado.

### Exercício 2 — Criar esquema (DDL)
Objetivo: criar tabelas `clientes` e `pedidos`.
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.CriaSchema
```
Validação: `Tabelas criadas/validadas com sucesso.`

### Exercício 3 — Inserção segura (PreparedStatement + batch)
Objetivo: inserir clientes com parâmetros.
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.InsereClientes
```
Validação: `Clientes inseridos com sucesso.`

### Exercício 4 — SELECT com filtros e ordenação
Objetivo: ler e exibir no console.
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ListaClientes
```
Validação: linhas no formato `[id] Nome <email>` (até 10, nomes iniciando com M).

### Exercício 5 — UPDATE e DELETE com segurança
Objetivo: atualizar telefone e remover cliente com WHERE.
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.AtualizaCliente
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.RemoveCliente
```
Validação: `Linhas atualizadas: X` e `Linhas removidas: Y` (X/Y >= 0). Confirme com o Exercício 4.

### Exercício 6 — Transações (commit/rollback)
Objetivo: inserir cliente + pedido na mesma transação.
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.PedidoTransacional
```
Validação: `Transação confirmada.`
Desafio: force um erro entre as etapas e observe o rollback (nenhuma das inserções deve persistir após `rollback()`).

### Exercício 8 (Bônus) — Mini-DAO de Cliente
Objetivo: encapsular acesso a dados (CRUD básico).
Arquivos: `model/Cliente`, `dao/ClienteDAO`, `MainDAO`.
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.CriaSchema
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.MainDAO
```
Validação: saída mostrando salvar → listar → buscar → atualizar → remover.

## 5) Ciclo rápido de prática (reset → inserir → listar)
Para reiniciar o ambiente e validar rapidamente que tudo funciona:
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ResetDB
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.InsereClientes
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ListaClientes
```
O primeiro comando limpa as tabelas e reinicia os IDs; os demais inserem dados e listam.

## 6) Guia passo a passo de testes e conceitos (didático)
- Conexão (JDBC + try-with-resources): rode `ConexaoDB` e observe a criação do arquivo `loja.db` e a mensagem “Conexão estabelecida!”. O bloco try-with-resources fecha a conexão automaticamente.
- DDL e chaves (FK): rode `CriaSchema`. Conceitos: CREATE TABLE, IF NOT EXISTS, chave primária AUTOINCREMENT e FK `pedidos.cliente_id → clientes.id`. No SQLite, `PRAGMA foreign_keys = ON` já é habilitado em `ConexaoDB`.
- Inserção segura (PreparedStatement + batch): rode `InsereClientes`. Conceitos: placeholders `?`, prevenção a SQL Injection, execução em lote, `setAutoCommit(false)` + `commit()`.
- SELECT com WHERE/ORDER BY/LIMIT: rode `ListaClientes`. Conceitos: filtrar por prefixo (`LIKE 'M%'`), ordenar (`ORDER BY id DESC`), limitar (`LIMIT 10`). Observe o `ResultSet` e a leitura por nome de coluna.
- UPDATE com WHERE: rode `AtualizaCliente`. Conceitos: atualização parametrizada e boas práticas (sempre usar WHERE). Valide com `ListaClientes` após executar.
- DELETE com WHERE: rode `RemoveCliente`. Conceitos: remoção segura, checar `linhas afetadas` e validar depois com SELECT. Nunca remova sem WHERE.
- Transações (ACID): rode `PedidoTransacional`. Conceitos: `setAutoCommit(false)`, múltiplas operações atômicas, `RETURN_GENERATED_KEYS` para obter o ID do cliente e encadear no pedido, `commit()`/`rollback()`. Para testar rollback, provoque um erro (ex.: mude o valor para `null` ou comente a segunda inserção e force exceção) e confirme que nada fica persistido.
- Padrão DAO (bônus): rode `MainDAO` após `CriaSchema`. Conceitos: encapsulamento de acesso a dados, métodos `salvar`, `listar`, `buscarPorId`, `atualizarTelefone`, `remover`, mapeamento `ResultSet → objeto` e tratamento de exceções.

## 7) Testes rápidos (checagens de saída)
- Conexão: `Conexão estabelecida!`.
- Schema: `Tabelas criadas/validadas com sucesso.`
- Inserção: `Clientes inseridos com sucesso.`
- Listagem: ao menos um `[id] Nome <email>`.
- Update/Delete: `Linhas atualizadas: ...` / `Linhas removidas: ...` e listagem coerente.
- Transação: `Transação confirmada.` (e, no cenário de erro, nada persistido após rollback).

## 8) Entregáveis sugeridos (para alunos)
- Ex. 0: print do `pom.xml` (dependência SQLite) + build concluído.
- Ex. 1: print com “Conexão estabelecida!”.
- Ex. 2: print da execução (opcional: esquema no DB Browser).
- Ex. 3: print da inserção + listagem do Ex. 4.
- Ex. 4: print da listagem.
- Ex. 5: prints de UPDATE/DELETE + listagem confirmando.
- Ex. 6: prints de commit e do caso com erro/rollback.
- Ex. 8 (bônus): projeto com DAO e execução do `MainDAO`.

## 9) Rubrica de avaliação (sugestão)
- Projeto compila e roda (10%).
- Esquema com FK corretamente (15%).
- Inserção segura com PreparedStatement e transação (20%).
- Consultas com filtros/ordenação e impressão correta (15%).
- UPDATE/DELETE com boas práticas (20%).
- Transações commit/rollback funcionando (20%).

## 10) Dicas e problemas comuns
- Dependência JDBC ausente/errada no `pom.xml`.
- Caminho do SQLite: use `jdbc:sqlite:./loja.db` (relativo ao projeto).
- Sempre use `PreparedStatement` e `WHERE` para UPDATE/DELETE.
- Feche recursos com try-with-resources (como no código).
- Para inspecionar o DB graficamente, use DB Browser for SQLite e abra `loja.db`.

## 11) Estrutura do projeto
```
lab-jdbc/
  pom.xml
  README.md
  .gitignore
  src/main/java/
    com/profluizricardomantovani/labjdbc/
      ConexaoDB.java
      CriaSchema.java
      ResetDB.java
      InsereClientes.java
      ListaClientes.java
      AtualizaCliente.java
      RemoveCliente.java
      PedidoTransacional.java
      MainDAO.java
      DumpDB.java
    com/profluizricardomantovani/labjdbc/dao/ClienteDAO.java
    com/profluizricardomantovani/labjdbc/model/Cliente.java
```

---

Para dúvidas, consulte o material da aula e os comentários no código.

Bom estudo! — Prof. Luiz Ricardo Mantovani da Silva
