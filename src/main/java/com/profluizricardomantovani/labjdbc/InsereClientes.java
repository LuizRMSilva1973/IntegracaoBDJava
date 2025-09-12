package com.profluizricardomantovani.labjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InsereClientes {
    public static void main(String[] args) {
        List<String[]> dados = List.of(
            new String[]{"Maria Silva", "maria@email.com", "11999887766"},
            new String[]{"João Souza", "joao@email.com", "11999995555"}
        );

        String sql = "INSERT INTO clientes (nome, email, telefone, data_cadastro) VALUES (?, ?, ?, date('now'))";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (String[] d : dados) {
                ps.setString(1, d[0]);
                ps.setString(2, d[1]);
                ps.setString(3, d[2]);
                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit();
            System.out.println("Clientes inseridos com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

