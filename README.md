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

## Capturas de pantalla

# login
<img width="1364" height="721" alt="image" src="https://github.com/user-attachments/assets/f45d5376-0643-4109-a4bf-aacc69de607c" />

# Menú principal para Administrador
<img width="1365" height="720" alt="image" src="https://github.com/user-attachments/assets/73ab21bc-39da-4780-8e54-e3aef5fe3bad" />

# Menú principal para Empleado
<img width="1365" height="719" alt="image" src="https://github.com/user-attachments/assets/88531b3f-2f54-4910-a2b9-2796c924cda9" />

# Gestión de productos
<img width="1365" height="714" alt="image" src="https://github.com/user-attachments/assets/88c8fb3e-1e51-45e2-a4cf-3a78141c4f04" />

# Gestión de Clientes
<img width="1365" height="715" alt="image" src="https://github.com/user-attachments/assets/3d5342e5-64eb-47ad-bc9c-40c71c8c4b5a" />

# Gestión de Proveedores
<img width="1362" height="719" alt="image" src="https://github.com/user-attachments/assets/f7fe617e-37b2-46ff-be15-879145ec003c" />

# Gestión de Compras
<img width="1363" height="717" alt="image" src="https://github.com/user-attachments/assets/ed743031-c2ab-4595-be6e-0ab89e576033" />

# Gestión de Ventas
<img width="1365" height="719" alt="image" src="https://github.com/user-attachments/assets/e371f63c-760e-4a08-b201-ad1c5e7aa9bc" />

# Factura generada
<img width="1360" height="719" alt="image" src="https://github.com/user-attachments/assets/fd511003-1c82-428f-b5e8-1e2438b70e7f" />

# Reporte en excel COMPRAS
<img width="1361" height="688" alt="image" src="https://github.com/user-attachments/assets/47d7d169-68f7-4e41-8e71-a39484781566" />


## Autor

Desarrollado por Adonis Fajardo
