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

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.repositorios.UsuariosRepository;

public class UsuariosServicesImplTest {

    @InjectMocks
    private UsuariosServiceslmpl service;

    @Mock
    private UsuariosRepository repository;

    List<Usuarios> list = new ArrayList<Usuarios>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargeUsuarios();
    }

    @Test
    public void findbyAllTest() {
        when(repository.findAll()).thenReturn(list);

        List<Usuarios> response = service.findByAll();

        assertEquals(3, response.size());

        verify(repository, times(1)).findAll();
    }
    

    public void chargeUsuarios() {
        Usuarios prod1 = new Usuarios("21837064-0", "Rocío", "Trujillo", "rociotp@gmail.com", 20);
        Usuarios prod2 = new Usuarios("1234567-8", "Gabriel", "Olguin", "gabrielo@gmail.com", 30);
        Usuarios prod3 = new Usuarios("9876543-2", "Matias", "Olivera", "matiaso@gmail.com", 40);
        
        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }
}
