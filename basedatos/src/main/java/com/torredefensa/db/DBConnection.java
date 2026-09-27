package com.torredefensa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Punto único de conexión a la base de datos de Supabase (PostgreSQL).
 *
 * IMPORTANTE — de dónde sacar estos valores:
 * Supabase Dashboard -> Project Settings -> Database -> Connection string ->
 * JDBC
 *
 * Usa el "Connection pooler" (puerto 6543, modo "Transaction") para apps
 * cliente normales; usa el puerto 5432 (conexión directa) si necesitas
 * sesiones largas o LISTEN/NOTIFY.
 *
 * NUNCA subas tu contraseña real a un repositorio. Aquí se leen primero de
 * variables de entorno; si no existen, se usan los valores de relleno de
 * abajo (reemplázalos únicamente para pruebas locales).
 */
public class DBConnection {

    private static final String URL = System.getenv().getOrDefault(
            "SUPABASE_DB_URL",
            "jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:6543/postgres?sslmode=require");

    private static final String USER = System.getenv().getOrDefault(
            "SUPABASE_DB_USER",
            "postgres.TU_PROJECT_REF");

    private static final String PASSWORD = System.getenv().getOrDefault(
            "SUPABASE_DB_PASSWORD",
            "TU_PASSWORD_AQUI");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de PostgreSQL en el classpath", e);
        }
    }

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
