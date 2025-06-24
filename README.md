API REST - Productos | Proyecto Perfulandia

Este documento describe los endpoints REST disponibles en el servicio ProductosRestController, indicando el método HTTP, la ruta, el propósito y los datos esperados para su ejecución o prueba.

URL Base:
http://localhost:8080/api/productos

1. Crear un producto
Método: POST
Ruta: /api/productos
Descripción: Crea un nuevo producto en el sistema.
Cuerpo esperado (formato JSON):

{
"nombre_Producto": "Invictus Victory Elixir",
"descripcion_Producto": "Perfume para hombre",
"precio": 60000,
"cantidad": 10
}

Respuesta esperada: 201 Created

2. Listar todos los productos
Método: GET
Ruta: /api/productos
Descripción: Devuelve una lista con todos los productos registrados.
Respuesta esperada: 200 OK con un arreglo de productos.

3. Ver detalle de un producto por ID
Método: GET
Ruta: /api/productos/{id}
Descripción: Devuelve los datos de un producto específico si existe.
Ejemplo de ruta: /api/productos/1
Respuesta esperada:

200 OK si el producto existe.

404 Not Found si no existe.

Además, esta respuesta incluye enlaces HATEOAS para navegar la API.

4. Modificar un producto existente
Método: PUT
Ruta: /api/productos/{id}
Descripción: Actualiza la información de un producto existente.
Ejemplo de ruta: /api/productos/1
Cuerpo esperado (formato JSON):

{
"nombre_Producto": "Perfume Actualizado",
"descripcion_Producto": "Nuevo aroma",
"precio": 65000,
"cantidad": 7
}

Respuesta esperada:

200 OK si fue actualizado correctamente.

404 Not Found si el producto no existe.

5. Eliminar un producto
Método: DELETE
Ruta: /api/productos/{id}
Descripción: Elimina un producto registrado.
Ejemplo de ruta: /api/productos/1
Respuesta esperada:

200 OK si fue eliminado exitosamente.

404 Not Found si el producto no existe.

Documentación de la API (Swagger UI)
La documentación completa e interactiva de los endpoints está disponible en la siguiente ruta:

http://localhost:8080/swagger-ui.html

Desde esta interfaz se pueden probar los métodos, visualizar las estructuras de entrada/salida y los posibles códigos de respuesta.
