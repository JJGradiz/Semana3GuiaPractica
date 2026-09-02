# Documentación del repositorio

Este repositorio contiene tres proyectos desarrollados con Java, JavaFX y Maven para practicar la creación de aplicaciones de escritorio con interfaz gráfica.

Los proyectos son:

- Recepcion_de_cafe
- RegistroPulperia
- TiendaDeArtesanias

Cada uno se encuentra en una carpeta independiente, con su propio `pom.xml`, su estructura `src/main/java` y `src/main/resources`, y su propia interfaz de usuario en FXML.

## Proyecto 1: Recepcion_de_cafe

### ¿Qué hace?
Es una aplicación para registrar lotes de café recibidos desde distintos productores.

### Funcionalidades principales
- Ingreso del nombre del productor.
- Ingreso del peso del lote en kilos.
- Ingreso de la variedad del café.
- Generación automática de un ID para cada lote.
- Visualización en tabla.
- Edición del peso de un lote seleccionado.
- Eliminación con confirmación.
- Validación de campos vacíos y datos inválidos.

### Archivos principales
- `Lote.java`: modelo de los datos del lote.
- `HelloController.java`: lógica de la pantalla.
- `hello-view.fxml`: diseño de la interfaz.
- `HelloApplication.java`: punto de entrada de la aplicación.

## Proyecto 2: RegistroPulperia

### ¿Qué hace?
Es un sistema sencillo para registrar productos de una pulpería o tienda pequeña. Permite ingresar datos del producto, validarlos, guardarlos y mostrarlos en una tabla para consultar los artículos cargados.

### Funcionalidades principales
- Registro de código, nombre, categoría, precio y cantidad.
- Validación de campos vacíos.
- Validación de que precio y cantidad sean valores numéricos válidos.
- Validación de que precio y cantidad sean mayores a cero.
- Visualización de todos los productos en un `TableView`.
- Búsqueda por código usando la tecla Enter.
- Muestra un mensaje de resultado o detalles del producto seleccionado.
- Botón para limpiar el formulario.

### Archivos principales
- `PulperiaApplication.java`: lanza la ventana principal.
- `controller/InventarioController.java`: controla la lógica del formulario, validaciones y tabla.
- `modelos/Producto.java`: modelo del producto.
- `inventario-view.fxml`: vista principal con formulario y tabla.
- `Launcher.java`: arranque alternativo de la aplicación.

### Flujo habitual
1. El usuario completa los campos del formulario.
2. Presiona "Guardar Producto".
3. Si faltan campos o los datos no son válidos, aparece un aviso.
4. Si todo está correcto, el producto se agrega a la tabla.
5. Luego puede buscarlo por código desde el campo de búsqueda.

### Caso de uso
Es útil para una pulpería, minimercado o tienda local que necesite llevar un registro rápido de productos disponibles con su código, nombre, precio y cantidad.

## Proyecto 3: TiendaDeArtesanias

### ¿Qué hace?
Es una pequeña aplicación tipo catálogo para vender artesanías. Muestra productos con imagen, nombre, categoría, precio y código.

### Funcionalidades principales
- Carga de productos de ejemplo.
- Tabla con inventario visual.
- Muestra imágenes asociadas a cada producto.
- Búsqueda por nombre o categoría.
- Menú de opciones con acciones de catálogo.

### Archivos principales
- `CatalogoController.java`: maneja la lógica del catálogo.
- `Productos.java`: representa cada artesanía.
- `hello-view.fxml`: vista principal del catálogo.
- `src/main/resources/imagenes/`: archivos de imagen de los productos.

## Estructura general del repositorio

```text
EjerciciosSemana3/
├── Recepcion_de_cafe/
│   ├── src/main/java/
│   ├── src/main/resources/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
├── RegistroPulperia/
│   ├── src/main/java/
│   ├── src/main/resources/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
├── TiendaDeArtesanias/
│   ├── src/main/java/
│   ├── src/main/resources/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
├── README.md
├── .gitignore
└── .idea/
```

## Cómo ejecutar un proyecto

### Requisitos
- JDK 21 o superior.
- Variables de entorno configuradas correctamente.
- Maven wrapper incluido en cada proyecto.


## Arquitectura de los proyectos

Los tres proyectos aplican una estructura muy similar:

- JavaFX para la interfaz gráfica.
- FXML para definir la vista.
- MVC: vista, controlador y modelo.
- Maven para compilar y ejecutar.
- Clases modelo para representar los datos del negocio.

## Resumen

- Recepcion_de_cafe: control y gestión de lotes de café.
- RegistroPulperia: registro y consulta de productos con validaciones.
- TiendaDeArtesanias: catálogo visual de productos artesanales.

Juntos forman un conjunto de ejercicios prácticos para aprender Java, JavaFX, FXML, manejo de eventos y estructura de proyectos en Maven.
