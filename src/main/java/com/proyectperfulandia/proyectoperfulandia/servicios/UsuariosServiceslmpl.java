package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.repositorios.UsuariosRepository;

@Service
public class UsuariosServiceslmpl implements UsuariosServices {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findByAll() {
        return (List<Usuarios>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuarios> findById(String rutUsuario) {
        return usuarioRepository.findById(rutUsuario);
    }

    @Override
    public Usuarios save(Usuarios unUsuario) {
        return usuarioRepository.save(unUsuario);
    }

    @Override
    public Optional<Usuarios> delete(String rut) {
        Optional<Usuarios> usuariosOptional = usuarioRepository.findById(rut);
        usuariosOptional.ifPresent(usuarioRepository::delete);
        return usuariosOptional;
    }
}
