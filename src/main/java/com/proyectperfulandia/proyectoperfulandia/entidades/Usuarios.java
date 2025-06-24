package com.proyectperfulandia.proyectoperfulandia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Usuarios {

    @Id
    @Column(name = "rut_usuario") // nombre variable corregido
    private String rutUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "apellido_usuario")
    private String apellidoUsuario;

    @Column(name = "correo_usuario")
    private String correoUsuario;

    @Column(name = "edad_usuario")
    private int edadUsuario;

    public Usuarios() {}

    public Usuarios(String rutUsuario, String nombreUsuario, String apellidoUsuario,
                    String correoUsuario, int edadUsuario) {
        this.rutUsuario = rutUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.correoUsuario = correoUsuario;
        this.edadUsuario = edadUsuario;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public int getEdadUsuario() {
        return edadUsuario;
    }

    public void setEdadUsuario(int edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

    @Override
    public String toString() {
        return "Usuarios [rutUsuario=" + rutUsuario + ", nombreUsuario=" + nombreUsuario +
               ", apellidoUsuario=" + apellidoUsuario + ", correoUsuario=" + correoUsuario +
               ", edadUsuario=" + edadUsuario + "]";
    }
}
