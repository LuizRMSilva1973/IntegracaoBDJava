# 05. SQLite Básico — o banco usado no lab

SQLite é um banco de dados relacional leve, baseado em arquivo. Não precisa de servidor — perfeito para labs e pequenos projetos.

## Características
- Um único arquivo `.db` no diretório do projeto (aqui, `loja.db`).
- Suporta SQL padrão (CREATE TABLE, INSERT, SELECT, UPDATE, DELETE etc.).
- Chaves estrangeiras (FK) precisam de `PRAGMA foreign_keys = ON` (no projeto já habilitado ao conectar).

## Esquema usado no lab (resumo)
- Tabela `clientes` com `id` (PK AUTOINCREMENT), `nome`, `email`, `telefone`.
- Tabela `pedidos` com `id` (PK AUTOINCREMENT), `cliente_id` (FK para `clientes.id`), `valor_total` e `data`.

## Inspeção do banco
- GUI: DB Browser for SQLite (abra `loja.db`).
- Via código: classe `DumpDB` (veja README para o comando Maven correspondente).

