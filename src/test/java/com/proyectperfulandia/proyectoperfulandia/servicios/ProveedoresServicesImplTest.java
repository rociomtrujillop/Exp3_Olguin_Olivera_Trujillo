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

import com.proyectperfulandia.proyectoperfulandia.entidades.Proveedores;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProveedoresRepository;

public class ProveedoresServicesImplTest {

    @InjectMocks
    private ProveedoresServiceslmpl service;

    @Mock
    private ProveedoresRepository repository;

    List<Proveedores> list = new ArrayList<Proveedores>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargeProveedores();
    }

    @Test
    public void findbyAllTest() {
        when(repository.findAll()).thenReturn(list);

        List<Proveedores> response = service.findByAll();

        assertEquals(3, response.size());

        verify(repository, times(1)).findAll();
    }
    

    public void chargeProveedores() {
        Proveedores prod1 = new Proveedores(Long.valueOf(1), "Silk Perfumes", "Santiago", "Perfumes Imitaciones");
        Proveedores prod2 = new Proveedores(Long.valueOf(2), "Dreams Parfums", "Providencia", "Perfumes Originales");
        Proveedores prod3 = new Proveedores(Long.valueOf(3), "Elite Perfumes", "La Florida", "Perfumes Originales");

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }
}
