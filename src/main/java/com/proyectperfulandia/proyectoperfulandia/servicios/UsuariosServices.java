package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;

public interface UsuariosServices {
    List<Usuarios> findByAll();

    Optional<Usuarios> findById(String rut);

    Usuarios save(Usuarios unUsuario);

    Optional<Usuarios> delete(String rut); // IMPORTANTE corregido, en vez de usar un objeto completo innecesariamente solo se necesita el rut, es mejor (también está corregido en impl)
}

