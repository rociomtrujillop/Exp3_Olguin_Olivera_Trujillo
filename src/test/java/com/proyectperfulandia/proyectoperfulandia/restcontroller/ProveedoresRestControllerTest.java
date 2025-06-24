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
import com.proyectperfulandia.proyectoperfulandia.entidades.Proveedores;
import com.proyectperfulandia.proyectoperfulandia.servicios.ProveedoresServiceslmpl;

@AutoConfigureMockMvc
@SpringBootTest
public class ProveedoresRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProveedoresServiceslmpl proveedoresService;

    private List<Proveedores> proveedoresLista;

    @Test
    public void verProveedoresTest() throws Exception{
        when(proveedoresService.findByAll()).thenReturn(proveedoresLista);
        mockMvc.perform(get("/api/proveedores")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void verunProveedorTest() {
        Proveedores unProveedor = new Proveedores(1L, "Silk Perfumes", "Santiago", "Perfumes Imitaciones");
        try{
            when(proveedoresService.findById(1L)).thenReturn(Optional.of(unProveedor));
            mockMvc.perform(get("/api/proveedores/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error" + ex.getMessage());
        }
    }

    @Test
    public void proveedorNoExisteTest() throws Exception{
        when(proveedoresService.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/proveedores/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearProveedorTest() throws Exception{
        Proveedores unProveedor = new Proveedores(null, "Dreams Parfums", "Providencia", "Perfumes Originales");
        Proveedores otroProveedor = new Proveedores(null, "Elite Perfumes", "La Florida", "Perfumes Originales");
        when(proveedoresService.save(any(Proveedores.class))).thenReturn(otroProveedor);
        mockMvc.perform(post("/api/proveedores")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unProveedor)))
        .andExpect(status().isCreated());
    }

    @Test
    public void modificarProveedorTest() throws Exception {
        Long id = 1L;
        Proveedores proveedorExistente = new Proveedores(id, "Silk Perfumes", "Santiago", "Perfumes Imitaciones");
        Proveedores proveedorModificado = new Proveedores(id, "Silk Perfumes", "Maipú", "Perfumes Originales");
        when(proveedoresService.findById(id)).thenReturn(Optional.of(proveedorExistente));
        when(proveedoresService.save(any(Proveedores.class))).thenReturn(proveedorModificado);
        mockMvc.perform(put("/api/proveedores/" + id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(proveedorModificado)))
        .andExpect(status().isOk());
    }

    @Test
    public void eliminarProveedorTest() throws Exception {
        Long id = 1L;
        Proveedores proveedorAEliminar = new Proveedores(id, "Silk Perfumes", "Santiago", "Perfumes Imitaciones");
        when(proveedoresService.delete(any(Proveedores.class))).thenReturn(Optional.of(proveedorAEliminar));
        mockMvc.perform(delete("/api/proveedores/" + id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

}
