package com.profluizricardomantovani.labjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoDB {
    private static final String URL = "jdbc:sqlite:./loja.db"; // cria o arquivo no diretório do projeto

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        // Garante validação de chaves estrangeiras no SQLite
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) { // try-with-resources fecha automático
            System.out.println("Conexão estabelecida!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}

