package com.torredefensa;

import com.torredefensa.dao.JugadorDAO;
import com.torredefensa.model.Jugador;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Demostración de las cuatro operaciones CRUD contra Supabase vía JDBC.
 * Ejecuta este main después de configurar SUPABASE_DB_URL, SUPABASE_DB_USER
 * y SUPABASE_DB_PASSWORD como variables de entorno (ver DBConnection.java).
 */
public class Main {

    public static void main(String[] args) {
        JugadorDAO dao = new JugadorDAO();

        try {
            // CREATE
            Jugador nuevo = new Jugador("test_pilot", "Test Pilot", "test_pilot@correo.com");
            int id = dao.crear(nuevo);
            System.out.println("[CREATE] Jugador creado con id " + id);

            // READ (uno)
            Optional<Jugador> encontrado = dao.leerPorId(id);
            encontrado.ifPresent(j -> System.out.println("[READ] " + j));

            // READ (todos)
            List<Jugador> todos = dao.leerTodos();
            System.out.println("[READ ALL] Total de jugadores: " + todos.size());

            // UPDATE
            Jugador aActualizar = encontrado.orElseThrow();
            aActualizar.setNombreMostrar("Test Pilot (actualizado)");
            boolean actualizado = dao.actualizar(aActualizar);
            System.out.println("[UPDATE] ¿Se actualizó? " + actualizado);

            // DELETE
            boolean eliminado = dao.eliminar(id);
            System.out.println("[DELETE] ¿Se eliminó? " + eliminado);

        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        }
    }
}
