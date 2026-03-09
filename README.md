# MiniSuper Zona Azul
## Descripción

MiniSuper Zona Azul es una aplicación de escritorio para la gestión integral de un minisúper. Permite administrar usuarios, productos, clientes, proveedores, compras, ventas y reportes de manera eficiente.
El sistema distingue entre roles de usuario, ofreciendo diferentes niveles de acceso según permisos, garantizando control sobre inventario y transacciones.

## Funcionalidades
1. Login y Roles

Autenticación mediante usuario y contraseña.

Roles de usuario:

Administrador: acceso completo a todos los módulos, incluyendo la gestión de usuarios.

Empleado: acceso restringido; no puede gestionar usuarios.

### 2. Gestión de Productos

Registrar, actualizar y eliminar productos.

Datos: código, nombre, precio y stock disponible.

### 3. Gestión de Clientes

Registrar, actualizar y eliminar clientes.

Datos: cédula, nombre, dirección y número de celular.

### 4. Gestión de Proveedores

Registrar, actualizar y eliminar proveedores.

Datos: nombre, cédula, empresa y teléfono.

### 5. Compras

Registro de compras con ID automático y fecha registrada automáticamente.

Datos: producto, cantidad, precio y proveedor.

### 6. Ventas

Ingreso de productos mediante código y cantidad.

Validación de stock disponible con mensajes de advertencia en caso de cantidad insuficiente.

Tabla temporal para agregar varios productos antes de confirmar la venta.

Confirmación de venta con opción de generar factura en PDF.

Prevención de pérdida de datos al volver al menú desde la pantalla de ventas.

### 7. Reportes

Generación de reportes en Excel de:

Productos

Proveedores

Clientes

Compras

Ventas

## Tecnologías utilizadas

Lenguaje: Java

Interfaz gráfica: Swing

Almacenamiento de datos: (SQL SERVER)

Generación de PDF: Librería Java PDF

Exportación a Excel: Librería Java Excel (Apache POI)

Control de versiones: Git y GitHub

## Instalación y Uso

1. Clonar el repositorio:

git clone https://github.com/AdonisFajardo005/Sistema-de-MiniSuper.git

2. Ejecutar la aplicación directamente desde el archivo .jar.

Ingresar con un usuario registrado:

Administrador: acceso completo.

Empleado: acceso limitado a la gestión de usuarios.

3. Navegar por el menú principal:

Usuarios, Productos, Clientes, Proveedores, Compras, Ventas, Reportes.

Realizar acciones según el módulo:

Guardar, actualizar y eliminar registros.

Confirmar ventas y generar facturas.

Exportar reportes a Excel.

Capturas de pantalla


# Contribuciones

Si deseas contribuir:

Haz un fork del repositorio.

Realiza tus cambios.



## Autor

Desarrollado por Adonis Fajardo
