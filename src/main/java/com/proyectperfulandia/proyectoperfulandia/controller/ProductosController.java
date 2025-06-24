package com.proyectperfulandia.proyectoperfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectperfulandia.proyectoperfulandia.entidades.Productos;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProductosRepository;


@Controller
public class ProductosController {

    @Autowired
    private ProductosRepository productoRepository;

    @GetMapping("/productos")
    public String productos(Model model){
        List<Productos> producto = (List<Productos>) productoRepository.findAll();
        model.addAttribute("productos", producto);
        return "listarproductos";
    }

    
}
