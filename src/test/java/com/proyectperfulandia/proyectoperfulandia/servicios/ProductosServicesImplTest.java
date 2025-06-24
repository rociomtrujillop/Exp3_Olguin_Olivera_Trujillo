package com.proyectperfulandia.proyectoperfulandia.servicios;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyectperfulandia.proyectoperfulandia.entidades.Productos;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProductosRepository;

public class ProductosServicesImplTest {

    @InjectMocks
    private ProductosServiceslmpl service;

    @Mock
    private ProductosRepository repository;

    List<Productos> list = new ArrayList<Productos>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargeProductos();
    }

    @Test
    public void findbyAllTest() {
        when(repository.findAll()).thenReturn(list);

        List<Productos> response = service.findByAll();

        // esperamos que traiga los 3 productos que tenemos en la lista que simula la base de datos
        assertEquals(3, response.size());

        // verificamos que se llame al método findall una vez
        verify(repository, times(1)).findAll();
    }
    

    public void chargeProductos() {
        Productos prod1 = new Productos(Long.valueOf(1), "Goddess Burberry", "Perfume para mujer de Burberry", 50000, 10);
        Productos prod2 = new Productos(Long.valueOf(2), "Invictus Victory Elixir Rabanne ", "Perfume para hombre de Rabanne", 60000, 15);
        Productos prod3 = new Productos(Long.valueOf(3), "Tobacco Honey Guerlain", "Perfume unisex de Guerlain", 55000, 20);

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }
}
