package com.proyectperfulandia.proyectoperfulandia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proveedores {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_proveedor;
private String nombre_Proveedor;
private String ubicacion_Proveedor;
private String producto_Proveedor;

public Proveedores() {
}

public Proveedores(Long id_proveedor, String nombre_Proveedor, String ubicacion_Proveedor, String producto_Proveedor) {
    this.id_proveedor = id_proveedor;
    this.nombre_Proveedor = nombre_Proveedor;
    this.ubicacion_Proveedor = ubicacion_Proveedor;
    this.producto_Proveedor = producto_Proveedor;






}

public Long getId_proveedor() {
    return id_proveedor;
}

public void setId_proveedor(Long id_proveedor) {
    this.id_proveedor = id_proveedor;
}

public String getNombre_Proveedor() {
    return nombre_Proveedor;
}

public void setNombre_Proveedor(String nombre_Proveedor) {
    this.nombre_Proveedor = nombre_Proveedor;
}

public String getUbicacion_Proveedor() {
    return ubicacion_Proveedor;
}

public void setUbicacion_Proveedor(String ubicacion_Proveedor) {
    this.ubicacion_Proveedor = ubicacion_Proveedor;
}

public String getProducto_Proveedor() {
    return producto_Proveedor;
}

public void setProducto_Proveedor(String producto_Proveedor) {
    this.producto_Proveedor = producto_Proveedor;
}

@Override
public String toString() {
    return "Proveedores{" +
            "id_proveedor=" + id_proveedor +
            ", nombre_Proveedor='" + nombre_Proveedor + '\'' +
            ", ubicacion_Proveedor='" + ubicacion_Proveedor + '\'' +
            ", producto_Proveedor='" + producto_Proveedor + '\'' +
            '}';
        
        }

}