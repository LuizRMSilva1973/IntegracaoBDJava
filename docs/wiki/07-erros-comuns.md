# 07. Erros Comuns e Soluções

- `java: command not found` ou versão antiga
  - Instale o JDK 17+ e verifique `java -version`/`javac -version`.

- `mvn: command not found`
  - Instale o Maven 3.8+ ou use o VS Code com Java Extension Pack.

- Dependência do SQLite ausente
  - Confira o `pom.xml` — deve conter `org.xerial:sqlite-jdbc`.

- Não criou `loja.db`
  - Rode a classe de conexão/criação de schema (`ConexaoDB` / `CriaSchema`).
  - Verifique o caminho JDBC: `jdbc:sqlite:./loja.db`.

- UPDATE/DELETE afetando tudo
  - Sempre use `WHERE` e parâmetros em `PreparedStatement`.

- Erros de chave estrangeira
  - Confirme que `PRAGMA foreign_keys = ON` está habilitado (no projeto, já é feito na conexão).

