# Proyecto Librería Shallom

## Tabla de contenido
1. [Introducción](#Introducción)
2. [Requisitos del sistema](#Requisitos-del-sistema)
3. [Instalación](#Instalación)
4. [Estructura del proyecto](#Estructura-del-proyecto)
5. [API Endpoints](#API-Endpoints)
6. [Configuración de entorno](#Configuración-de-entorno)
7. [Despliegue](#Despliegue)
8. [Librerias](#Librerias)

## Introducción
Este proyecto corresponde a un aplicativo web para la librería Shallom, que posee funciones para los clientes y el administrador. La documentación técnica proporciona instrucciones detalladas para la implementación y desarrollo del sistema.


## Requisitos del sistema

- Java (JDK 11 o superior)
- Servidor de aplicaciones: Apache Tomcat (v9.x o superior)
- Base de datos: MySQL (v8.0)
- Framework CSS: Bootstrap (v5.x)
- Preprocesador CSS: LESS
- Motor de plantillas: Java Server Pages (JSP)
- IDE: IntelliJ IDEA

## Instalación

Estos son algunos pasos para poder configurar y probar adecuadamente la aplicación.

### 1. Configurar el entorno de desarrollo de Java:
   - Asegúrate de tener instalado Java JDK 11 o superior.
   - Instalar Apache Tomcat:
```bash
    sudo apt install tomcat9
```
### 2. Clonar el repositorio:
```bash
    git clone https://github.com/CBElmer28/Shallom.git
```
### 3. Configurar la base de datos:

Asegurarse de tener MYSQL instalado, crear la base de datos.
```sql
    CREATE DATABASE Shallom_db;
```

Ejecutar el script **init.sql** para creación de las tablas necesarias:
```sql
    mysql -u usuario -p libreria_db < db/init.sql
```

### 4. Desplegar el proyecto en Apache Tomcat:

Configurar el archivo server.xml de Tomcat.

Iniciar el servidor:   
```
    sudo systemctl start tomcat
```

### 5. Configurar el Frontend. 

Se le puede agregar Bootstrap directamente a sus archivos JSP usando CDN:
```html
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
```
En caso se quiera hacer configuración y uso por medio de SCSS, asegurese de tener un compilador instalado.

### 6. Probar aplicación.

Abra su navegador web y acceda a la aplicación por medio de:
```
http://localhost:8080/MiProyecto
```
Para asegurarse de que la aplicación funcione adecuadamente.

## Estructura del proyecto
```bash
C:.
|   .gitattributes
|   .gitignore
|   pom.xml
|   README.md
|   structure.txt
|   
+---.idea
|       .gitignore
|       compiler.xml
|       encodings.xml
|       jarRepositories.xml
|       misc.xml
|       uiDesigner.xml
|       webContexts.xml
|       workspace.xml
|       
\---src
    \---main
        +---java
        |   \---org
        |       \---cahuas
        |           \---webapp
        |               \---servelet
        |                   \---cabeceras
        |                       +---controlador
        |                       |       AgregarCarroServelet.java
        |                       |       AgregarProductoServelet.java
        |                       |       EliminarProductoServelet.java
        |                       |       ExportarBoletaServelet.java
        |                       |       LoginServlet.java
        |                       |       LogoutServlet.java
        |                       |       ProductoServelet.java
        |                       |       ProductosServelet.java
        |                       |       
        |                       \---models
        |                           +---filters
        |                           |       ConexionFilter.java
        |                           |       LoginAdminFilter.java
        |                           |       
        |                           +---listeners
        |                           |       AplicacionListener.java
        |                           |       
        |                           +---modelo
        |                           |       Carro.java
        |                           |       DatalleProducto.java
        |                           |       ItemCarro.java
        |                           |       Producto.java
        |                           |       Proveedor.java
        |                           |       Usuario.java
        |                           |       
        |                           +---repositories
        |                           |       ProductoRepositoryImpl.java
        |                           |       ProveedorRepositoryImpl.java
        |                           |       Repository.java
        |                           |       RepositoryUsuario.java
        |                           |       UsuarioRepositoryJdbcImpl.java
        |                           |       
        |                           +---services
        |                           |       LoginService.java
        |                           |       LoginServiceJdbcImpl.java
        |                           |       ProductoService.java
        |                           |       ProductoServiceJdbcImpl.java
        |                           |       ServiceJdbcException.java
        |                           |       
        |                           \---util
        |                                   ConexionBaseDatos.java
        |                                   
        +---resources
        \---webapp
            |   index.html
            |   
            +---admin
            |   |   boopracti.html
            |   |   index.jsp
            |   |   inventario.jsp
            |   |   
            |   \---assets
            |       +---css
            |       |       bopracti.css
            |       |       modal.css
            |       |       style.css
            |       |       tabla.css
            |       |       
            |       +---img
            |       |       cloth_1.jpg
            |       |       cloth_2.jpg
            |       |       cloth_3.jpg
            |       |       people.png
            |       |       
            |       +---imgs
            |       |       customer01.jpg
            |       |       customer02.jpg
            |       |       customer03.png
            |       |       icon.png
            |       |       
            |       \---js
            |               main.js
            |               modal.js
            |               script.js
            |               
            +---reportesJasper
            |   |   boletaIntento.jasper
            |   |   BoletaProuctos.jasper
            |   |   BoletaProuctos_2.jasper
            |   |   
            |   \---img
            |           logoo.png
            |           
            +---usuario
            |   |   .DS_Store
            |   |   about.html
            |   |   cart.html
            |   |   cart.jsp
            |   |   checkout.html
            |   |   checkout.jsp
            |   |   contact.html
            |   |   contact.jsp
            |   |   index.jsp
            |   |   login.jsp
            |   |   prepros-6.config
            |   |   shop-single.html
            |   |   shop-single.jsp
            |   |   shop.html
            |   |   shop.jsp
            |   |   thankyou.html
            |   |   thankyou.jsp
            |   |   
            |   +---css
            |   |   |   aos.css
            |   |   |   bootstrap.min.css
            |   |   |   jquery-ui.css
            |   |   |   magnific-popup.css
            |   |   |   main.css
            |   |   |   owl.carousel.min.css
            |   |   |   owl.theme.default.min.css
            |   |   |   style.css
            |   |   |   util.css
            |   |   |   
            |   |   \---bootstrap
            |   |           bootstrap-grid.css
            |   |           bootstrap-reboot.css
            |   |           
            |   +---fonts
            |   |   +---font-awesome-4.7.0
            |   |   |   |   HELP-US-OUT.txt
            |   |   |   |   
            |   |   |   +---css
            |   |   |   |       font-awesome.css
            |   |   |   |       font-awesome.min.css
            |   |   |   |       
            |   |   |   +---fonts
            |   |   |   |       fontawesome-webfont.eot
            |   |   |   |       fontawesome-webfont.svg
            |   |   |   |       fontawesome-webfont.ttf
            |   |   |   |       fontawesome-webfont.woff
            |   |   |   |       fontawesome-webfont.woff2
            |   |   |   |       FontAwesome.otf
            |   |   |   |       
            |   |   |   +---less
            |   |   |   |       animated.less
            |   |   |   |       bordered-pulled.less
            |   |   |   |       core.less
            |   |   |   |       fixed-width.less
            |   |   |   |       font-awesome.less
            |   |   |   |       icons.less
            |   |   |   |       larger.less
            |   |   |   |       list.less
            |   |   |   |       mixins.less
            |   |   |   |       path.less
            |   |   |   |       rotated-flipped.less
            |   |   |   |       screen-reader.less
            |   |   |   |       stacked.less
            |   |   |   |       variables.less
            |   |   |   |       
            |   |   |   \---scss
            |   |   |           font-awesome.scss
            |   |   |           _animated.scss
            |   |   |           _bordered-pulled.scss
            |   |   |           _core.scss
            |   |   |           _fixed-width.scss
            |   |   |           _icons.scss
            |   |   |           _larger.scss
            |   |   |           _list.scss
            |   |   |           _mixins.scss
            |   |   |           _path.scss
            |   |   |           _rotated-flipped.scss
            |   |   |           _screen-reader.scss
            |   |   |           _stacked.scss
            |   |   |           _variables.scss
            |   |   |           
            |   |   +---icomoon
            |   |   |   |   demo.html
            |   |   |   |   Read Me.txt
            |   |   |   |   selection.json
            |   |   |   |   style.css
            |   |   |   |   
            |   |   |   +---demo-files
            |   |   |   |       demo.css
            |   |   |   |       demo.js
            |   |   |   |       
            |   |   |   \---fonts
            |   |   |           icomoon.eot
            |   |   |           icomoon.svg
            |   |   |           icomoon.ttf
            |   |   |           icomoon.woff
            |   |   |           
            |   |   +---Linearicons-Free-v1.0.0
            |   |   |   |   icon-font.min.css
            |   |   |   |   
            |   |   |   \---WebFont
            |   |   |           Linearicons-Free.eot
            |   |   |           Linearicons-Free.svg
            |   |   |           Linearicons-Free.ttf
            |   |   |           Linearicons-Free.woff
            |   |   |           Linearicons-Free.woff2
            |   |   |           
            |   |   +---montserrat
            |   |   |       Montserrat-Black.ttf
            |   |   |       Montserrat-BlackItalic.ttf
            |   |   |       Montserrat-Bold.ttf
            |   |   |       Montserrat-BoldItalic.ttf
            |   |   |       Montserrat-ExtraBold.ttf
            |   |   |       Montserrat-ExtraBoldItalic.ttf
            |   |   |       Montserrat-ExtraLight.ttf
            |   |   |       Montserrat-ExtraLightItalic.ttf
            |   |   |       Montserrat-Italic.ttf
            |   |   |       Montserrat-Light.ttf
            |   |   |       Montserrat-LightItalic.ttf
            |   |   |       Montserrat-Medium.ttf
            |   |   |       Montserrat-MediumItalic.ttf
            |   |   |       Montserrat-Regular.ttf
            |   |   |       Montserrat-SemiBold.ttf
            |   |   |       Montserrat-SemiBoldItalic.ttf
            |   |   |       Montserrat-Thin.ttf
            |   |   |       Montserrat-ThinItalic.ttf
            |   |   |       OFL.txt
            |   |   |       
            |   |   +---poppins
            |   |   |       Poppins-Black.ttf
            |   |   |       Poppins-BlackItalic.ttf
            |   |   |       Poppins-Bold.ttf
            |   |   |       Poppins-BoldItalic.ttf
            |   |   |       Poppins-ExtraBold.ttf
            |   |   |       Poppins-ExtraBoldItalic.ttf
            |   |   |       Poppins-ExtraLight.ttf
            |   |   |       Poppins-ExtraLightItalic.ttf
            |   |   |       Poppins-Italic.ttf
            |   |   |       Poppins-Light.ttf
            |   |   |       Poppins-LightItalic.ttf
            |   |   |       Poppins-Medium.ttf
            |   |   |       Poppins-MediumItalic.ttf
            |   |   |       Poppins-Regular.ttf
            |   |   |       Poppins-SemiBold.ttf
            |   |   |       Poppins-SemiBoldItalic.ttf
            |   |   |       Poppins-Thin.ttf
            |   |   |       Poppins-ThinItalic.ttf
            |   |   |       
            |   |   \---ubuntu
            |   |           Ubuntu-Bold.ttf
            |   |           Ubuntu-BoldItalic.ttf
            |   |           Ubuntu-Italic.ttf
            |   |           Ubuntu-Light.ttf
            |   |           Ubuntu-LightItalic.ttf
            |   |           Ubuntu-Medium.ttf
            |   |           Ubuntu-MediumItalic.ttf
            |   |           Ubuntu-Regular.ttf
            |   |           UFL.txt
            |   |           
            |   +---images
            |   |   |   blank_avatar.png
            |   |   |   blog_1.jpg
            |   |   |   blog_12.png
            |   |   |   Children.jpg
            |   |   |   cloth_1.jpg
            |   |   |   cloth_111.jpg
            |   |   |   cloth_2.jpg
            |   |   |   cloth_3.jpg
            |   |   |   faber-castell-boligrafo-032-m-trilux-x-12-surt-343211-33748-default-1.jpg
            |   |   |   hero_1.jpg
            |   |   |   hero_1.png
            |   |   |   icon.png
            |   |   |   lapicero5.jpg
            |   |   |   logo.png
            |   |   |   Men.jpg
            |   |   |   person_1.jpg
            |   |   |   person_2.jpg
            |   |   |   person_3.jpg
            |   |   |   person_4.jpg
            |   |   |   shoe.png
            |   |   |   shoe_1.jpg
            |   |   |   tempera-x-250-ml-rosado-faber-castell-16704011-default-1.jpg
            |   |   |   Women.jpg
            |   |   |   
            |   |   \---icons
            |   |           favicon.ico
            |   |           icon-google.png
            |   |           map-marker.png
            |   |           symbol-01.png
            |   |           
            |   +---js
            |   |       aos.js
            |   |       bootstrap.min.js
            |   |       jquery-3.3.1.min.js
            |   |       jquery-ui.js
            |   |       jquery.magnific-popup.min.js
            |   |       main.js
            |   |       owl.carousel.min.js
            |   |       popper.min.js
            |   |       slick.min.js
            |   |       
            |   +---nav
            |   |       sinSession.jsp
            |   |       usuarioEncabezado.jsp
            |   |       
            |   +---scss
            |   |   |   .DS_Store
            |   |   |   style.scss
            |   |   |   _site-base.scss
            |   |   |   _site-blocks.scss
            |   |   |   _site-navbar.scss
            |   |   |   
            |   |   \---bootstrap
            |   |       +---css
            |   |       |       bootstrap-grid.css
            |   |       |       bootstrap-grid.css.map
            |   |       |       bootstrap-grid.min.css
            |   |       |       bootstrap-grid.min.css.map
            |   |       |       bootstrap-reboot.css
            |   |       |       bootstrap-reboot.css.map
            |   |       |       bootstrap-reboot.min.css
            |   |       |       bootstrap-reboot.min.css.map
            |   |       |       bootstrap.css
            |   |       |       bootstrap.css.map
            |   |       |       bootstrap.min.css
            |   |       |       bootstrap.min.css.map
            |   |       |       
            |   |       \---js
            |   |               bootstrap.bundle.js
            |   |               bootstrap.bundle.js.map
            |   |               bootstrap.bundle.min.js
            |   |               bootstrap.bundle.min.js.map
            |   |               bootstrap.js
            |   |               bootstrap.js.map
            |   |               bootstrap.min.js
            |   |               bootstrap.min.js.map
            |   |               
            |   \---vendor
            |       +---animate
            |       |       animate.css
            |       |       
            |       +---animsition
            |       |   +---css
            |       |   |       animsition.css
            |       |   |       animsition.min.css
            |       |   |       
            |       |   \---js
            |       |           animsition.js
            |       |           animsition.min.js
            |       |           
            |       +---bootstrap
            |       |   +---css
            |       |   |       bootstrap-grid.css
            |       |   |       bootstrap-grid.css.map
            |       |   |       bootstrap-grid.min.css
            |       |   |       bootstrap-grid.min.css.map
            |       |   |       bootstrap-reboot.css
            |       |   |       bootstrap-reboot.css.map
            |       |   |       bootstrap-reboot.min.css
            |       |   |       bootstrap-reboot.min.css.map
            |       |   |       bootstrap.css
            |       |   |       bootstrap.css.map
            |       |   |       bootstrap.min.css
            |       |   |       bootstrap.min.css.map
            |       |   |       
            |       |   \---js
            |       |           bootstrap.js
            |       |           bootstrap.min.js
            |       |           popper.js
            |       |           popper.min.js
            |       |           tooltip.js
            |       |           
            |       +---countdowntime
            |       |       countdowntime.js
            |       |       
            |       +---css-hamburgers
            |       |       hamburgers.css
            |       |       hamburgers.min.css
            |       |       
            |       +---daterangepicker
            |       |       daterangepicker.css
            |       |       daterangepicker.js
            |       |       moment.js
            |       |       moment.min.js
            |       |       
            |       +---jquery
            |       |       jquery-3.2.1.min.js
            |       |       
            |       +---perfect-scrollbar
            |       |       perfect-scrollbar.css
            |       |       perfect-scrollbar.min.js
            |       |       
            |       \---select2
            |               select2.css
            |               select2.js
            |               select2.min.css
            |               select2.min.js
            |               
            \---WEB-INF
                    web.xml
                    
```


## API Endpoints

- **POST /api/login**: Realiza el inicio de sesión de un usuario o administrador.
- **GET /api/logout**: Realiza la salida de sesión del usuario o administrador.
- **GET /api/productos**: Añade un producto al carrito de compras.
- **POST /agregarproducto**: Agrega un producto a la BD
- **POST /eliminarproducto** Elimina un producto de la BD
- **POST /actualizarproducto** Actualiza un producto de la BD

## Configuración de entorno

### Archivo `.env`
Crea un archivo llamado `.env` en la raíz de tu proyecto con el siguiente contenido:

```env
DB_HOST=localhost
DB_USER=mi_usuario
DB_PASSWORD=mi_contraseña
DB_NAME=Shallom_db
SECRET_KEY=clave_secreta
```


## Despliegue

### Preparativos Previos
- Asegurarse de que el servidor tenga instalado Java JDK, Apache Tomcat y MySQL.

### Configuración del Servidor
1. Asegurarse de que los puertos necesarios estén abiertos (puerto 8080 para Tomcat en este caso).

### Despliegue de la Aplicación
1. Compila la aplicación y crea el archivo WAR (si es necesario).
2. Copia el archivo WAR a la carpeta `webapps` de Tomcat:
   ```bash
   cp /ruta/a/tu_aplicacion.war /ruta/a/tomcat/webapps/
   ```

## Librerias

- JaserReports: Para la generación de boletas en formato PDF. 
- ApachePOI: Para la generación de documentos office como excel para la visualización de la tabla de productos.
- Jakarta Mail: Una API de Java para enviar, recibir y procesar correos electrónicos, compatible con protocolos como SMTP, POP3 y IMAP.
- Jakarta Activation: Proporciona una forma de manejar y procesar datos de diferentes formatos, generalmente utilizada junto con Jakarta Mail para adjuntos.
- Jakarta: Un proyecto de la Fundación Eclipse que proporciona un conjunto de APIs y herramientas de código abierto para el desarrollo de aplicaciones empresariales en Java.




