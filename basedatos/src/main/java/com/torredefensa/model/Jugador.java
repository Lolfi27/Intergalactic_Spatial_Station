package com.torredefensa.model;

import java.sql.Timestamp;

public class Jugador {

    private Integer jugadorId;
    private String nombreUsuario;
    private String nombreMostrar;
    private String correo;
    private Timestamp creadoEn;

    public Jugador() {
    }

    public Jugador(String nombreUsuario, String nombreMostrar, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.nombreMostrar = nombreMostrar;
        this.correo = correo;
    }

    public Integer getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Integer jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }

    public void setNombreMostrar(String nombreMostrar) {
        this.nombreMostrar = nombreMostrar;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Timestamp creadoEn) {
        this.creadoEn = creadoEn;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "jugadorId=" + jugadorId +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", nombreMostrar='" + nombreMostrar + '\'' +
                ", correo='" + correo + '\'' +
                ", creadoEn=" + creadoEn +
                '}';
    }
}
