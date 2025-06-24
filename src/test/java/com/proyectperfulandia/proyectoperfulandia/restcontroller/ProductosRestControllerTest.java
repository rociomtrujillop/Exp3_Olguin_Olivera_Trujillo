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
import com.proyectperfulandia.proyectoperfulandia.entidades.Productos;
import com.proyectperfulandia.proyectoperfulandia.servicios.ProductosServiceslmpl;


@AutoConfigureMockMvc
@SpringBootTest
public class ProductosRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private ProductosServiceslmpl productosService;

    private List<Productos> productosLista;

    @Test
    public void verProductosTest() throws Exception{
        when(productosService.findByAll()).thenReturn(productosLista);
        mockMvc.perform(get("/api/productos")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void verunProductoTest() {
        Productos unProducto = new Productos(1L, "Goddess Burberry", "Perfume para mujer de Burberry", 50000, 10);
        try{
            when(productosService.findById(1L)).thenReturn(Optional.of(unProducto));
            mockMvc.perform(get("/api/productos/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error" + ex.getMessage());
        }
    }

    @Test
    public void productoNoExisteTest() throws Exception{
        when(productosService.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/productos/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearProductoTest() throws Exception{
        Productos unProducto = new Productos(null, "Invictus Victory Elixir Rabanne ", "Perfume para hombre de Rabanne", 60000, 15);
        Productos otroProducto = new Productos(null, "Tobacco Honey Guerlain", "Perfume unisex de Guerlain", 55000, 20);
        when(productosService.save(any(Productos.class))).thenReturn(otroProducto);
        mockMvc.perform(post("/api/productos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unProducto)))
        .andExpect(status().isCreated());
    }

    @Test
    public void modificarProductoTest() throws Exception {
        Long id = 1L;
        Productos productoExistente = new Productos(id, "Invictus Victory Elixir Rabanne ", "Perfume para hombre de Rabanne", 60000, 15);
        Productos productoModificado = new Productos(id, "Invictus Victory Elixir Rabanne Ultra", "Perfume para hombre de Rabanne", 70000, 10);
        when(productosService.findById(id)).thenReturn(Optional.of(productoExistente));
        when(productosService.save(any(Productos.class))).thenReturn(productoModificado);
        mockMvc.perform(put("/api/productos/" + id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productoModificado)))
        .andExpect(status().isOk());
    }

    @Test
    public void eliminarProductoTest() throws Exception {
        Long id = 1L;
        Productos productoAEliminar = new Productos(id, "Tobacco Honey Guerlain", "Perfume unisex de Guerlain", 55000, 20);
        when(productosService.delete(any(Productos.class))).thenReturn(Optional.of(productoAEliminar));
        mockMvc.perform(delete("/api/productos/" + id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}
