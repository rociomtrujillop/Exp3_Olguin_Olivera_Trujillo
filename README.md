## Servicios REST y Endpoints

### Usuario Service

GET     /usuarios
Obtiene el listado completo de usuarios.

GET     /usuarios/{id}
Obtiene un usuario específico por su ID.

POST    /usuarios
Crea un nuevo usuario.

PUT     /usuarios/{id}
Actualiza los datos de un usuario existente.

DELETE  /usuarios/{id}
Elimina un usuario del sistema.

------------------------------------------------------

### Producto Service

GET     /productos
Lista todos los productos disponibles.

GET     /productos/{id}
Devuelve un producto específico.

POST    /productos
Agrega un nuevo producto.

PUT     /productos/{id}
Edita los datos de un producto.

DELETE  /productos/{id}
Elimina un producto por su ID.

------------------------------------------------------

### Proveedor Service
GET     /proveedores  
Lista todos los proveedores registrados.

GET     /proveedores/{id}  
Consulta un proveedor específico.

POST    /proveedores  
Registra un nuevo proveedor.

PUT     /proveedores/{id}  
Actualiza la información de un proveedor.

DELETE  /proveedores/{id}  
Elimina un proveedor por su ID.
