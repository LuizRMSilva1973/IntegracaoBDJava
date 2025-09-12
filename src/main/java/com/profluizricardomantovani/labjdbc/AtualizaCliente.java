package com.profluizricardomantovani.labjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizaCliente {
    public static void main(String[] args) {
        String sql = "UPDATE clientes SET telefone = ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "11999998888");
            ps.setInt(2, 1);
            int linhas = ps.executeUpdate();
            System.out.println("Linhas atualizadas: " + linhas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

