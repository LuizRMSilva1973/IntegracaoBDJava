package com.profluizricardomantovani.labjdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListaClientes {
    public static void main(String[] args) {
        String sql = """
            SELECT id, nome, email
            FROM clientes
            WHERE nome LIKE 'M%'
            ORDER BY id DESC
            LIMIT 10
        """;

        try (Connection conn = ConexaoDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("[%d] %s <%s>%n",
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

