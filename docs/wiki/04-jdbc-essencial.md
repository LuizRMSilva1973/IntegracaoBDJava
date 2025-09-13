# 04. JDBC Essencial — conceitos e boas práticas

JDBC é a API padrão do Java para se comunicar com bancos de dados relacionais via SQL.

## Peças principais
- Driver JDBC: biblioteca do banco (usamos `sqlite-jdbc`).
- `Connection`: conexão com o banco.
- `PreparedStatement`: comando SQL parametrizado (seguro contra SQL Injection).
- `ResultSet`: resultado de consultas (`SELECT`).

## Fluxo típico (pseudo-código)
```java
try (Connection conn = DriverManager.getConnection("jdbc:sqlite:./loja.db")) {
    String sql = "SELECT id, nome, email FROM clientes WHERE nome LIKE ? ORDER BY id DESC LIMIT ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "M%" );  // parâmetro 1
        ps.setInt(2, 10);         // parâmetro 2
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                System.out.printf("[%d] %s <%s>%n", id, nome, email);
            }
        }
    }
}
```

## Boas práticas
- Use sempre `PreparedStatement` em vez de concatenar strings.
- Feche recursos com try-with-resources (como no exemplo acima).
- Para múltiplas operações atômicas, use transações (`setAutoCommit(false)`, `commit()` e `rollback()`).

## Transações (ACID)
- Permitem agrupar várias operações; se alguma falhar, tudo reverte (`rollback`).
- Exemplo do lab: inserir cliente e pedido juntos, garantindo consistência.

