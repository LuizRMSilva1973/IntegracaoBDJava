package com.profluizricardomantovani.labjdbc;

import java.math.BigDecimal;
import java.sql.*;

public class PedidoTransacional {
    public static void main(String[] args) {
        String insereCliente = "INSERT INTO clientes (nome, email, telefone, data_cadastro) VALUES (?, ?, ?, date('now'))";
        String inserePedido  = "INSERT INTO pedidos (cliente_id, data_pedido, valor) VALUES (?, date('now'), ?)";

        try (Connection conn = ConexaoDB.getConnection()) {
            conn.setAutoCommit(false); // Início da transação

            int novoClienteId;
            try (PreparedStatement ps = conn.prepareStatement(insereCliente, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, "Cliente Tx");
                ps.setString(2, "ctx@example.com");
                ps.setString(3, "11911112222");
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    keys.next();
                    novoClienteId = keys.getInt(1);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(inserePedido)) {
                ps.setInt(1, novoClienteId);
                ps.setBigDecimal(2, new BigDecimal("199.90"));
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("Transação confirmada.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Em um app real, faça rollback em outro bloco try/catch
        }
    }
}

