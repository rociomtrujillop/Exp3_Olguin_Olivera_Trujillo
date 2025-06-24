package com.proyectperfulandia.proyectoperfulandia.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectperfulandia.proyectoperfulandia.entidades.Proveedores;
import com.proyectperfulandia.proyectoperfulandia.servicios.ProveedoresServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Proveedores", description = "Operaciones del servicio de proveedores")
@RestController
@RequestMapping("api/proveedores")
public class ProveedoresRestController {

    @Autowired
    private ProveedoresServices proveedoresServices;

    @Operation(summary = "Lista todos los proveedores", description = "Devuelve una lista completa de los proveedores registrados.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "[{\"id_proveedor\":1,\"nombre_Proveedor\":\"Silk Perfumes\",\"ubicacion_Proveedor\":\"Santiago\",\"producto_Proveedor\":\"Perfumes Imitaciones\"}]")))
    @GetMapping
    public List<Proveedores> listar() {
        return proveedoresServices.findByAll();
    }

    @Operation(summary = "Buscar un proveedor por ID", description = "Busca un proveedor por su identificador único (ID).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"id_proveedor\":1,\"nombre_Proveedor\":\"Silk Perfumes\",\"ubicacion_Proveedor\":\"Santiago\",\"producto_Proveedor\":\"Perfumes Imitaciones\"}"))),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Proveedores>> verDetalle(@PathVariable Long id) {
        Optional<Proveedores> proveedoresOptional = proveedoresServices.findById(id);
        if (proveedoresOptional.isPresent()) {
            Proveedores proveedor = proveedoresOptional.get();
            EntityModel<Proveedores> recurso = EntityModel.of(proveedor);
            recurso.add(linkTo(methodOn(ProveedoresRestController.class).verDetalle(id)).withSelfRel());
            recurso.add(linkTo(methodOn(ProveedoresRestController.class).listar()).withRel("todos_proveedores"));
            recurso.add(linkTo(methodOn(ProveedoresRestController.class).eliminarProveedor(id)).withRel("eliminar_proveedor"));
            return ResponseEntity.ok(recurso);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo proveedor", description = "Agrega un nuevo proveedor al sistema.")
    @ApiResponse(responseCode = "201", description = "Proveedor creado correctamente")
    @PostMapping
    public ResponseEntity<Proveedores> crear(@RequestBody Proveedores unProveedores) {
        Proveedores nuevoProveedor = proveedoresServices.save(unProveedores);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }

    @Operation(summary = "Actualizar un proveedor", description = "Modifica los datos de un proveedor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProvedor(@PathVariable Long id, @RequestBody Proveedores unProveedores) {
        Optional<Proveedores> proveedoresOptional = proveedoresServices.findById(id);
        if (proveedoresOptional.isPresent()) {
            Proveedores proveedoresExiste = proveedoresOptional.get();
            proveedoresExiste.setNombre_Proveedor(unProveedores.getNombre_Proveedor());
            proveedoresExiste.setProducto_Proveedor(unProveedores.getProducto_Proveedor());
            proveedoresExiste.setUbicacion_Proveedor(unProveedores.getUbicacion_Proveedor());
            Proveedores provedoresModificado = proveedoresServices.save(proveedoresExiste);
            return ResponseEntity.ok(provedoresModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un proveedor", description = "Elimina un proveedor del sistema por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id) {
        Proveedores unProveedor = new Proveedores();
        unProveedor.setId_proveedor(id);
        Optional<Proveedores> proveedorOptional = proveedoresServices.delete(unProveedor);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
