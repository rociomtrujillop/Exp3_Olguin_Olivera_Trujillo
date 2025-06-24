package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Proveedores;



public interface ProveedoresServices {

    List<Proveedores> findByAll();

    Optional<Proveedores> findById(Long id);

    Proveedores save(Proveedores unProveedor);
    
    Optional<Proveedores> delete(Proveedores unProveedor);
}
