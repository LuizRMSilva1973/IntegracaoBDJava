package com.profluizricardomantovani.labjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCliente {
    public static void main(String[] args) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 2);
            int linhas = ps.executeUpdate();
            System.out.println("Linhas removidas: " + linhas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

