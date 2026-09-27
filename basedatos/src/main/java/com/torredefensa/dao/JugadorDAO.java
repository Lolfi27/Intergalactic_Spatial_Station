package com.torredefensa.dao;

import com.torredefensa.db.DBConnection;
import com.torredefensa.model.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Operaciones CRUD sobre la tabla jugador usando JDBC puro
 * (PreparedStatement + try-with-resources en cada método, para que
 * la conexión y los statements se cierren aunque ocurra una excepción).
 */
public class JugadorDAO {

    // ---------- CREATE ----------
    public int crear(Jugador j) throws SQLException {
        String sql = "INSERT INTO jugador (nombre_usuario, nombre_mostrar, correo) " +
                     "VALUES (?, ?, ?) RETURNING jugador_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getNombreUsuario());
            ps.setString(2, j.getNombreMostrar());
            ps.setString(3, j.getCorreo());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("jugador_id");
            }
        }
    }

    // ---------- READ (uno) ----------
    public Optional<Jugador> leerPorId(int jugadorId) throws SQLException {
        String sql = "SELECT * FROM jugador WHERE jugador_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jugadorId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        }
    }

    // ---------- READ (todos) ----------
    public List<Jugador> leerTodos() throws SQLException {
        String sql = "SELECT * FROM jugador ORDER BY jugador_id";
        List<Jugador> lista = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    // ---------- UPDATE ----------
    public boolean actualizar(Jugador j) throws SQLException {
        String sql = "UPDATE jugador SET nombre_mostrar = ?, correo = ? WHERE jugador_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getNombreMostrar());
            ps.setString(2, j.getCorreo());
            ps.setInt(3, j.getJugadorId());

            return ps.executeUpdate() > 0;
        }
    }

    // ---------- DELETE ----------
    public boolean eliminar(int jugadorId) throws SQLException {
        String sql = "DELETE FROM jugador WHERE jugador_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jugadorId);
            return ps.executeUpdate() > 0;
        }
    }

    private Jugador mapRow(ResultSet rs) throws SQLException {
        Jugador j = new Jugador();
        j.setJugadorId(rs.getInt("jugador_id"));
        j.setNombreUsuario(rs.getString("nombre_usuario"));
        j.setNombreMostrar(rs.getString("nombre_mostrar"));
        j.setCorreo(rs.getString("correo"));
        j.setCreadoEn(rs.getTimestamp("creado_en"));
        return j;
    }
}
