package com.profluizricardomantovani.labjdbc;

import com.profluizricardomantovani.labjdbc.dao.ClienteDAO;
import com.profluizricardomantovani.labjdbc.model.Cliente;

public class MainDAO {
    public static void main(String[] args) {
        // Garanta que o schema exista (execute CriaSchema antes)
        ClienteDAO dao = new ClienteDAO();

        // Salvar
        Cliente c = new Cliente("Aluno DAO", "aluno.dao@example.com", "11970001234");
        int idGerado = dao.salvar(c);
        System.out.println("Salvo com ID: " + idGerado);

        // Listar
        System.out.println("Clientes:");
        dao.listar().forEach(System.out::println);

        // Buscar por ID
        System.out.println("Buscar ID " + idGerado + ": " + dao.buscarPorId(idGerado));

        // Atualizar telefone
        boolean atualizado = dao.atualizarTelefone(idGerado, "11988887777");
        System.out.println("Telefone atualizado? " + atualizado);

        // Remover
        boolean removido = dao.remover(idGerado);
        System.out.println("Removido? " + removido);
    }
}

