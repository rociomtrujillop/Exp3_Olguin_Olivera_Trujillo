package com.proyectperfulandia.proyectoperfulandia.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectperfulandia.proyectoperfulandia.entidades.Productos;
import com.proyectperfulandia.proyectoperfulandia.servicios.ProductosServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
@RestController
@RequestMapping("api/productos")
public class ProductosRestController {

    @Autowired
    private ProductosServices productosServices;

    @Operation(summary = "Listar todos los productos", description = "Devuelve una lista de todos los productos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de productos",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "[{\"id_producto\":1,\"nombre_Producto\":\"Perfume A\",\"descripcion_Producto\":\"Aroma fresco\",\"precio\":10000,\"cantidad\":5}]")))
    @GetMapping
    public List<Productos> listar(){
        return productosServices.findByAll();
    }

    @Operation(summary = "Obtener un producto por ID", description = "Devuelve los detalles de un producto específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"id_producto\":1,\"nombre_Producto\":\"Perfume A\",\"descripcion_Producto\":\"Aroma fresco\",\"precio\":10000,\"cantidad\":5}"))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Productos>> verDetalle(@PathVariable Long id){
        Optional<Productos> productosOptional = productosServices.findById(id);
        if(productosOptional.isPresent()){
            Productos producto = productosOptional.get();
            EntityModel<Productos> recurso = EntityModel.of(producto);
            recurso.add(linkTo(methodOn(ProductosRestController.class).verDetalle(id)).withSelfRel());
            recurso.add(linkTo(methodOn(ProductosRestController.class).listar()).withRel("todos_productos"));
            recurso.add(linkTo(methodOn(ProductosRestController.class).eliminarProducto(id)).withRel("eliminar_producto"));
            return ResponseEntity.ok(recurso);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto en el sistema")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @PostMapping
    public ResponseEntity<Productos> crear(@RequestBody Productos unProductos){
        return ResponseEntity.status(HttpStatus.CREATED).body(productosServices.save(unProductos));
    }

    @Operation(summary = "Modificar un producto", description = "Actualiza los datos de un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto modificado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Productos unProductos){
        Optional <Productos> productosOptional = productosServices.findById(id);
        if(productosOptional.isPresent()){
            Productos productoExiste = productosOptional.get();
            productoExiste.setNombre_Producto(unProductos.getNombre_Producto());
            productoExiste.setDescripcion_Producto(unProductos.getDescripcion_Producto());
            productoExiste.setCantidad(unProductos.getCantidad());
            productoExiste.setPrecio(unProductos.getPrecio());
            Productos productoModificado = productosServices.save(productoExiste);
            return ResponseEntity.ok(productoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Productos unProductos = new Productos();
        unProductos.setId_producto(id);
        Optional<Productos> productosOptional = productosServices.delete(unProductos);
        if(productosOptional.isPresent()){
            return ResponseEntity.ok(productosOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}

