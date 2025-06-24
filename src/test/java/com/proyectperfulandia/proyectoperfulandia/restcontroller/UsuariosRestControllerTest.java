package com.proyectperfulandia.proyectoperfulandia.restcontroller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.servicios.UsuariosServiceslmpl;

@AutoConfigureMockMvc
@SpringBootTest
public class UsuariosRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuariosServiceslmpl usuariosService;

    private List<Usuarios> usuariosLista;

    @Test
    public void verUsuariosTest() throws Exception{
        when(usuariosService.findByAll()).thenReturn(usuariosLista);
        mockMvc.perform(get("/api/usuarios")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void verunUsuarioTest() {
        Usuarios unUsuario = new Usuarios("21837064-0", "Rocío", "Trujillo", "rociotp@gmail.com", 20);
        try{
            when(usuariosService.findById("21837064-0")).thenReturn(Optional.of(unUsuario));
            mockMvc.perform(get("/api/usuarios/21837064-0")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error" + ex.getMessage());
        }
    }

    @Test
    public void usuarioNoExisteTest() throws Exception{
        when(usuariosService.findById("54365867-7")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/usuarios/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception{
        Usuarios unUsuario = new Usuarios("1234567-8", "Gabriel", "Olguin", "gabrielo@gmail.com", 30);
        Usuarios otroUsuario = new Usuarios("9876543-2", "Matias", "Olivera", "matiaso@gmail.com", 40);
        when(usuariosService.save(any(Usuarios.class))).thenReturn(otroUsuario);
        mockMvc.perform(post("/api/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unUsuario)))
        .andExpect(status().isOk());
    }

    @Test
    public void modificarUsuarioTest() throws Exception {
        String rut = "21837064-0";
        Usuarios usuarioExistente = new Usuarios(rut, "Rocío", "Trujillo", "rociotp@gmail.com", 20);
        Usuarios usuarioModificado = new Usuarios(rut, "Rocío", "Trujillo", "rocio20@gmail.com", 22);
        when(usuariosService.findById(rut)).thenReturn(Optional.of(usuarioExistente));
        when(usuariosService.save(any(Usuarios.class))).thenReturn(usuarioModificado);
        mockMvc.perform(put("/api/usuarios/" + rut)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioModificado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        String rut = "21837064-0";
        Usuarios usuarioAEliminar = new Usuarios(rut, "Rocío", "Trujillo", "rociotp@gmail.com", 20);
        when(usuariosService.delete(rut)).thenReturn(Optional.of(usuarioAEliminar));
        mockMvc.perform(delete("/api/usuarios/" + rut)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
