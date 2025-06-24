package com.proyectperfulandia.proyectoperfulandia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    private String nombre_Producto;
    private String descripcion_Producto;
    private int precio;
    private int cantidad;
    public Productos(Long id_producto, String nombre_Producto, String descripcion_Producto, int precio, int cantidad) {
        this.id_producto = id_producto;
        this.nombre_Producto = nombre_Producto;
        this.descripcion_Producto = descripcion_Producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public Productos() {
    }
    public Long getId_producto() {
        return id_producto;
    }
    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }
    public String getNombre_Producto() {
        return nombre_Producto;
    }
    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }
    public String getDescripcion_Producto() {
        return descripcion_Producto;
    }
    public void setDescripcion_Producto(String descripcion_Producto) {
        this.descripcion_Producto = descripcion_Producto;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    @Override
    public String toString() {
        return "Productos [id_producto=" + id_producto + ", nombre_Producto=" + nombre_Producto
                + ", descripcion_Producto=" + descripcion_Producto + ", precio=" + precio + ", cantidad=" + cantidad
                + "]";
    }
    
    
 
}    