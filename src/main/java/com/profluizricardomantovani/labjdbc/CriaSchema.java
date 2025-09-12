package com.profluizricardomantovani.labjdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriaSchema {
    public static void main(String[] args) {
        String sqlClientes = """
            CREATE TABLE IF NOT EXISTS clientes (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              nome TEXT NOT NULL,
              email TEXT UNIQUE,
              telefone TEXT,
              data_cadastro DATE
            );
            """;

        String sqlPedidos = """
            CREATE TABLE IF NOT EXISTS pedidos (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              cliente_id INTEGER,
              data_pedido DATE,
              valor DECIMAL(10,2),
              FOREIGN KEY (cliente_id) REFERENCES clientes(id)
            );
            """;

        try (Connection conn = ConexaoDB.getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sqlClientes);
            st.execute(sqlPedidos);
            System.out.println("Tabelas criadas/validadas com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}

