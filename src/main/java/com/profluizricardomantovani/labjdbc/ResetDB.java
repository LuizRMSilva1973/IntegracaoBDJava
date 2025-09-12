package com.profluizricardomantovani.labjdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ResetDB {
    public static void main(String[] args) {
        // Garante que as tabelas existam
        CriaSchema.main(new String[0]);

        try (Connection conn = ConexaoDB.getConnection();
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(false);

            // Apaga primeiro as tabelas dependentes
            st.execute("DELETE FROM pedidos");
            st.execute("DELETE FROM clientes");

            // Reinicia os IDs (apenas para SQLite com AUTOINCREMENT)
            try {
                st.execute("DELETE FROM sqlite_sequence WHERE name IN ('clientes','pedidos')");
            } catch (SQLException ignored) {
                // sqlite_sequence pode não existir; ignore
            }

            conn.commit();
            System.out.println("Banco limpo e IDs reiniciados.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

