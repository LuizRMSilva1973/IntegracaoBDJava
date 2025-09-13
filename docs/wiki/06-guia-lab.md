# 06. Guia do Laboratório — como rodar e validar

Esta seção resume o passo a passo para executar os exercícios com Maven. Os nomes das classes e validações estão detalhados no `README.md` do projeto.

## Passos rápidos
1) Compilar:
```
mvn -q -DskipTests package
```
2) Executar cada exercício (troque o nome da classe):
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ConexaoDB
```
3) Validar: mensagens no console e (opcionalmente) conferir o `loja.db` no SQLite.

## Ciclo sugerido de prática
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ResetDB
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.InsereClientes
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.DumpDB
```

## Dicas didáticas
- Observe o uso de `PreparedStatement` e placeholders `?` — é assim que evitamos SQL Injection.
- Repare no `try-with-resources`: o Java fecha a conexão e o statement automaticamente.
- Em `PedidoTransacional`, note `setAutoCommit(false)` e `commit()`/`rollback()` garantindo atomicidade.

