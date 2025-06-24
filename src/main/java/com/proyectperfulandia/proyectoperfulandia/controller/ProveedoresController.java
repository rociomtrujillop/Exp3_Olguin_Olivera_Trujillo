package com.proyectperfulandia.proyectoperfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectperfulandia.proyectoperfulandia.entidades.Proveedores;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProveedoresRepository;

@Controller
public class ProveedoresController {

    @Autowired
    private ProveedoresRepository proveedoresRepository;
    @GetMapping("/proveedores")
    public String proveedores(Model model){
        List<Proveedores> proveedor = (List<Proveedores>) proveedoresRepository.findAll();
        model.addAttribute("proveedores", proveedor);
        return "listarproveedores";
    }
}
