package com.proyectperfulandia.proyectoperfulandia.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.servicios.UsuariosServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Usuarios", description = "Operaciones del servicio de usuarios")
@RestController
@RequestMapping("api/usuarios")
public class UsuariosRestController {

    @Autowired
    private UsuariosServices usuarioServices;

    @Operation(summary = "Lista todos los usuarios", description = "Devuelve una lista completa de los usuarios registrados.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "[{\"rutUsuario\":\"21837064-0\",\"nombreUsuario\":\"Rocío\",\"apellidoUsuario\":\"Trujillo\",\"correoUsuario\":\"rociotp@gmail.com\",\"edadUsuario\":20}]")))
    @GetMapping
    public List<Usuarios> listar(){
        return usuarioServices.findByAll();
    }

    @Operation(summary = "Buscar un usuario por RUT", description = "Busca un usuario por su identificador único (RUT).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"rutUsuario\":\"21837064-0\",\"nombreUsuario\":\"Rocío\",\"apellidoUsuario\":\"Trujillo\",\"correoUsuario\":\"rociotp@gmail.com\",\"edadUsuario\":20}"))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{rut}")
    public ResponseEntity<EntityModel<Usuarios>> verDetalle(@PathVariable String rut) {
        Optional<Usuarios> usuariosOptional = usuarioServices.findById(rut);
        if (usuariosOptional.isPresent()) {
            Usuarios usuario = usuariosOptional.get();
            EntityModel<Usuarios> recurso = EntityModel.of(usuario);
            recurso.add(linkTo(methodOn(UsuariosRestController.class).verDetalle(rut)).withSelfRel());
            recurso.add(linkTo(methodOn(UsuariosRestController.class).listar()).withRel("todos_usuarios"));
            recurso.add(linkTo(methodOn(UsuariosRestController.class).eliminarUsuario(rut)).withRel("eliminar_usuario"));
            return ResponseEntity.ok(recurso);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar un usuario", description = "Modifica los datos de un usuario existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{rut}")
    public ResponseEntity<?> modificarUsuarios(@PathVariable String rut, @RequestBody Usuarios unUsuarios) {
        Optional<Usuarios> usuariosOptional = usuarioServices.findById(rut);
        if (usuariosOptional.isPresent()) {
            Usuarios usuariosExiste = usuariosOptional.get();
            usuariosExiste.setNombreUsuario(unUsuarios.getNombreUsuario());
            usuariosExiste.setApellidoUsuario(unUsuarios.getApellidoUsuario());
            usuariosExiste.setCorreoUsuario(unUsuarios.getCorreoUsuario());
            usuariosExiste.setEdadUsuario(unUsuarios.getEdadUsuario());
            Usuarios usuarioModificado = usuarioServices.save(usuariosExiste);
            return ResponseEntity.ok(usuarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Agrega un nuevo usuario al sistema.")
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
    @PostMapping
    public ResponseEntity<Usuarios> crear(@RequestBody Usuarios unUsuarios) {
        Usuarios nuevoUsuario = usuarioServices.save(unUsuarios);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema por su RUT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{rut}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String rut) {
        Optional<Usuarios> usuariosOptional = usuarioServices.delete(rut);
        if (usuariosOptional.isPresent()) {
            return ResponseEntity.ok(usuariosOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}

