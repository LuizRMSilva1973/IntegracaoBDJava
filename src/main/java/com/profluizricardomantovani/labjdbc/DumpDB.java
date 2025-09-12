package com.profluizricardomantovani.labjdbc;

import java.sql.*;

public class DumpDB {
    public static void main(String[] args) {
        try (Connection conn = ConexaoDB.getConnection()) {
            System.out.println("=== Tabelas no banco ===");
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%' ORDER BY name")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String table = rs.getString(1);
                        dumpTable(conn, table);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dumpTable(Connection conn, String table) throws SQLException {
        System.out.println();
        System.out.println("-- " + table + " --");
        String countSql = "SELECT COUNT(*) FROM " + table;
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(countSql)) {
            if (rs.next()) {
                System.out.println("Linhas: " + rs.getInt(1));
            }
        }

        String sql = "SELECT * FROM " + table + " ORDER BY 1";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();
            // Cabeçalho
            StringBuilder header = new StringBuilder();
            for (int i = 1; i <= cols; i++) {
                if (i > 1) header.append(" | ");
                header.append(md.getColumnName(i));
            }
            System.out.println(header);

            // Linhas
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= cols; i++) {
                    if (i > 1) row.append(" | ");
                    row.append(String.valueOf(rs.getObject(i)));
                }
                System.out.println(row);
            }
        }
    }
}

