# Documentación de Endpoints REST – Proyecto Perfulandia
### IMPORTANTE!! Para las entidades que usan ID (Productos y Proveedores) NO ingresar ID en el JSON al momento de intentar registrar uno de ellos, la ID se autogenera entonces provoca un error

**Ruta base general:**  
`http://localhost:8080/swagger-ui/index.html`

## ENTIDAD: Usuarios

### 1. Listar todos los usuarios  
**Método:** `GET`  
**URL:** `/api/usuarios`  
**Descripción:** Devuelve la lista de todos los usuarios registrados.  
**Cuerpo:** No requiere  
**Respuesta esperada:** `200 OK`

### 2. Buscar un usuario por su RUT  
**Método:** `GET`  
**URL:** `/api/usuarios/{rut}`  
**Descripción:** Devuelve los datos de un producto específico.  
**Ejemplo:** `/api/usuarios/21837064-0`
**Respuesta esperada:**  
- `200 OK` si el usuario existe  
- `404 Not Found` si no existe

### 3. Crear un nuevo usuario  
**Método:** `POST`  
**URL:** `/api/usuarios`  
**Descripción:** Registra un nuevo usuario.  
**Cuerpo JSON ejemplo:**
```json
{
  "rutUsuario": "21837064-0",
  "nombreUsuario": "Rocío",
  "apellidoUsuario": "Trujillo",
  "correoUsuario": "rociotp@gmail.com",
  "edadUsuario": 20
}
```
**Respuesta esperada:**  
- `200 OK` si el usuario fue creado correctamente 

### 4. Modificar un usuario existente
**Método:** `PUT`  
**URL:** `/api/usuarios/{rut}`  
**Ejemplo:** `/api/usuarios/21837064-0`
**Descripción:** Modifica datos de un usuario.  
**Cuerpo JSON ejemplo:**
```json
{
  "correoUsuario": "rociotp12@gmail.com",
  "edadUsuario": 21
}
```
**Respuesta esperada:**  
- `200 OK` si los datos del usuario fueron actualizados correctamente
- `404 Not Found` si el rut ingresado no existe y no se pudieron actualizar los datos

### 5. Eliminar un usuario existente
**Método:** `DELETE`  
**URL:** `/api/usuarios/{rut}`  
**Ejemplo:** `/api/usuarios/21837064-0`
**Descripción:** Elimina un usuario existente de la base de datos.  
**Respuesta esperada:**  
- `200 OK` si el usuario fue eliminado correctamente
- `404 Not Found` si el rut ingresado no existe y no se pudo eliminar el usuario


## ENTIDAD: Productos

### 1. Listar todos los productos  
**Método:** `GET`  
**URL:** `/api/productos`  
**Descripción:** Devuelve la lista de todos los productos registrados. 
**Respuesta esperada:** `200 OK`

### 2. Buscar un producto por su ID  
**Método:** `GET`  
**URL:** `/api/productos/{id}`  
**Descripción:** Devuelve los datos de un producto específico.  
**Ejemplo:** `/api/productos/1`  
**Respuesta esperada:**  
- `200 OK` si el producto existe  
- `404 Not Found` si no se encuentra

### 3. Crear un nuevo producto  
**Método:** `POST`  
**URL:** `/api/productos`  
**Descripción:** Registra un nuevo producto.  
**Cuerpo JSON ejemplo:**
```json
{
  "nombre_Producto": "Goddess Burberry",
  "descripcion_Producto": "Perfume para mujer de Burberry",
  "precio": 50000,
  "cantidad": 10
}
```
### 4. Modificar un producto existente
**Método:** `PUT`  
**URL:** `/api/productos/{id}`  
**Ejemplo:** `/api/productos/1`
**Descripción:** Modifica datos de un producto. 
**Cuerpo JSON ejemplo:**
```json
{
  "descripcion_Producto": "Perfume unisex de Burberry",
  "cantidad": 8
}
```
**Respuesta esperada:**  
- `200 OK` si los datos del productos fueron actualizados correctamente
- `404 Not Found` si el id ingresado no existe y no se pudieron actualizar los datos

### 5. Eliminar un producto existente
**Método:** `DELETE`  
**URL:** `/api/productos/{id}`
**Ejemplo:** `/api/productos/1`
**Descripción:** Elimina un usuario existente de la base de datos.  
**Respuesta esperada:**  
- `200 OK` si el prodcuto fue eliminado correctamente
- `404 Not Found` si el id ingresado no existe y no se pudo eliminar el producto

## ENTIDAD: Proveedores

### 1. Listar todos los proveedores  
**Método:** `GET`  
**URL:** `/api/proveedores`  
**Descripción:** Devuelve la lista de todos los proveedores registrados.  
**Cuerpo:** No requiere  
**Respuesta esperada:** `200 OK`

### 2. Buscar un proveedor por su ID  
**Método:** `GET`  
**URL:** `/api/proveedores/{id}`  
**Descripción:** Devuelve los datos de un proveedor específico.  
**Ejemplo:** `/api/proveedor/1`
**Respuesta esperada:**  
- `200 OK` si el proveedor existe  
- `404 Not Found` si no existe

### 3. Crear un nuevo proveedores  
**Método:** `POST`  
**URL:** `/api/proveedores`  
**Descripción:** Registra un nuevo proveedor.  
**Cuerpo JSON ejemplo:**
```json
{
  "nombre_Proveedor": "Silk Perfumes",
  "ubicacion_Proveedor": "Santiago",
  "producto_Proveedor": "Perfumes Originales"
}
```
**Respuesta esperada:**  
- `200 OK` si el proveedor fue creado correctamente 

### 4. Modificar un usuario existente
**Método:** `PUT`  
**URL:** `/api/proveedores/{id}`  
**Ejemplo:** `/api/proveedores/1`
**Descripción:** Modifica datos de un proveedor.  
**Cuerpo JSON ejemplo:**
```json
{
  "ubicacion_Proveedor": "Maipú"
}
```
**Respuesta esperada:**  
- `200 OK` si los datos del proveedor fueron actualizados correctamente
- `404 Not Found` si el id ingresado no existe y no se pudieron actualizar los datos

### 5. Eliminar un proveedor existente
**Método:** `DELETE`  
**URL:** `/api/proveedores/{id}`  
**Ejemplo:** `/api/proveedores/1`
**Descripción:** Elimina un proveedores existente de la base de datos.  
**Respuesta esperada:**  
- `200 OK` si el proveedor fue eliminado correctamente
- `404 Not Found` si el id ingresado no existe y no se pudo eliminar el proveedor
