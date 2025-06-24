package com.proyectperfulandia.proyectoperfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.repositorios.UsuariosRepository;

@Controller
public class UsuariosController {
    
    @Autowired
    private UsuariosRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        List<Usuarios> usuario = (List<Usuarios>) usuarioRepository.findAll();
        model.addAttribute("usuarios", usuario);
        return "listarusuarios";
    }
}
