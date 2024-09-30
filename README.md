<<<<<<< HEAD
# Proyecto Librería Shallom

## Tabla de contenido
1. Introducción
2. Requisitos del sistema
3. Instalación
4. Estructura del proyecto
5. API Endpoints
6. Configuración de entorno
7. Despliegue
8. Pruebas

## Introducción
Este proyecto corresponde a un aplicativo web para la librería Shallom, que posee funciones para los clientes y el administrador. La documentación técnica proporciona instrucciones detalladas para la implementación y desarrollo del sistema.


## Requisitos del sistema

- Java (JDK 11 o superior)
- Servidor de aplicaciones: Apache Tomcat (v9.x o superior)
- Base de datos: MySQL (v8.0)
- Framework CSS: Bootstrap (v5.x)
- Preprocesador CSS: SCSS
- Motor de plantillas: Java Server Pages (JSP)

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
Aplicativo:.
│   .gitignore
│   estructura.txt
│   pom.xml
│   
├───.idea
│       .gitignore
│       compiler.xml
│       encodings.xml
│       jarRepositories.xml
│       misc.xml
│       uiDesigner.xml
│       workspace.xml
│       
├───src
│   └───main
│       ├───java
│       │   └───org
│       │       └───cahuas
│       │           └───webapp
│       │               └───servelet
│       │                   └───cabeceras
│       │                       ├───controlador
│       │                       │       LoginServlet.java
│       │                       │       LogoutServlet.java
│       │                       │       ProductosServelet.java
│       │                       │       
│       │                       └───models
│       │                           ├───filters
│       │                           │       ConexionFilter.java
│       │                           │       LoginAdminFilter.java
│       │                           │       
│       │                           ├───listeners
│       │                           │       AplicacionListener.java
│       │                           │       
│       │                           ├───modelo
│       │                           │       Carro.java
│       │                           │       ItemCarro.java
│       │                           │       Producto.java
│       │                           │       Proveedor.java
│       │                           │       Usuario.java
│       │                           │       
│       │                           ├───repositories
│       │                           │       ProductoRepositoryImpl.java
│       │                           │       ProveedorRepositoryImpl.java
│       │                           │       Repository.java
│       │                           │       RepositoryUsuario.java
│       │                           │       UsuarioRepositoryJdbcImpl.java
│       │                           │       
│       │                           ├───services
│       │                           │       LoginService.java
│       │                           │       LoginServiceJdbcImpl.java
│       │                           │       ProductoService.java
│       │                           │       ProductoServiceJdbcImpl.java
│       │                           │       ServiceJdbcException.java
│       │                           │       
│       │                           └───util
│       │                                   ConexionBaseDatos.java
│       │                                   
│       ├───resources
│       └───webapp
│           │   index.html
│           │   
│           ├───admin
│           │   │   index.jsp
│           │   │   inventario.jsp
│           │   │   
│           │   └───assets
│           │       ├───css
│           │       │       style.css
│           │       │       
│           │       ├───imgs
│           │       │       customer01.jpg
│           │       │       customer02.jpg
│           │       │       customer03.png
│           │       │       icon.png
│           │       │       
│           │       └───js
│           │               main.js
│           │               modal.js
│           │               
│           ├───usuario
│           │   │   .DS_Store
│           │   │   about.html
│           │   │   cart.html
│           │   │   checkout.html
│           │   │   contact.html
│           │   │   index.jsp
│           │   │   login.jsp
│           │   │   prepros-6.config
│           │   │   shop-single.html
│           │   │   shop.html
│           │   │   thankyou.html
│           │   │   
│           │   ├───css
│           │   │   │   aos.css
│           │   │   │   bootstrap.min.css
│           │   │   │   jquery-ui.css
│           │   │   │   magnific-popup.css
│           │   │   │   main.css
│           │   │   │   owl.carousel.min.css
│           │   │   │   owl.theme.default.min.css
│           │   │   │   style.css
│           │   │   │   util.css
│           │   │   │   
│           │   │   └───bootstrap
│           │   │           bootstrap-grid.css
│           │   │           bootstrap-reboot.css
│           │   │           
│           │   ├───fonts
│           │   │   ├───font-awesome-4.7.0
│           │   │   │   │   HELP-US-OUT.txt
│           │   │   │   │   
│           │   │   │   ├───css
│           │   │   │   │       font-awesome.css
│           │   │   │   │       font-awesome.min.css
│           │   │   │   │       
│           │   │   │   ├───fonts
│           │   │   │   │       fontawesome-webfont.eot
│           │   │   │   │       fontawesome-webfont.svg
│           │   │   │   │       fontawesome-webfont.ttf
│           │   │   │   │       fontawesome-webfont.woff
│           │   │   │   │       fontawesome-webfont.woff2
│           │   │   │   │       FontAwesome.otf
│           │   │   │   │       
│           │   │   │   ├───less
│           │   │   │   │       animated.less
│           │   │   │   │       bordered-pulled.less
│           │   │   │   │       core.less
│           │   │   │   │       fixed-width.less
│           │   │   │   │       font-awesome.less
│           │   │   │   │       icons.less
│           │   │   │   │       larger.less
│           │   │   │   │       list.less
│           │   │   │   │       mixins.less
│           │   │   │   │       path.less
│           │   │   │   │       rotated-flipped.less
│           │   │   │   │       screen-reader.less
│           │   │   │   │       stacked.less
│           │   │   │   │       variables.less
│           │   │   │   │       
│           │   │   │   └───scss
│           │   │   │           font-awesome.scss
│           │   │   │           _animated.scss
│           │   │   │           _bordered-pulled.scss
│           │   │   │           _core.scss
│           │   │   │           _fixed-width.scss
│           │   │   │           _icons.scss
│           │   │   │           _larger.scss
│           │   │   │           _list.scss
│           │   │   │           _mixins.scss
│           │   │   │           _path.scss
│           │   │   │           _rotated-flipped.scss
│           │   │   │           _screen-reader.scss
│           │   │   │           _stacked.scss
│           │   │   │           _variables.scss
│           │   │   │           
│           │   │   ├───icomoon
│           │   │   │   │   demo.html
│           │   │   │   │   Read Me.txt
│           │   │   │   │   selection.json
│           │   │   │   │   style.css
│           │   │   │   │   
│           │   │   │   ├───demo-files
│           │   │   │   │       demo.css
│           │   │   │   │       demo.js
│           │   │   │   │       
│           │   │   │   └───fonts
│           │   │   │           icomoon.eot
│           │   │   │           icomoon.svg
│           │   │   │           icomoon.ttf
│           │   │   │           icomoon.woff
│           │   │   │           
│           │   │   ├───Linearicons-Free-v1.0.0
│           │   │   │   │   icon-font.min.css
│           │   │   │   │   
│           │   │   │   └───WebFont
│           │   │   │           Linearicons-Free.eot
│           │   │   │           Linearicons-Free.svg
│           │   │   │           Linearicons-Free.ttf
│           │   │   │           Linearicons-Free.woff
│           │   │   │           Linearicons-Free.woff2
│           │   │   │           
│           │   │   ├───montserrat
│           │   │   │       Montserrat-Black.ttf
│           │   │   │       Montserrat-BlackItalic.ttf
│           │   │   │       Montserrat-Bold.ttf
│           │   │   │       Montserrat-BoldItalic.ttf
│           │   │   │       Montserrat-ExtraBold.ttf
│           │   │   │       Montserrat-ExtraBoldItalic.ttf
│           │   │   │       Montserrat-ExtraLight.ttf
│           │   │   │       Montserrat-ExtraLightItalic.ttf
│           │   │   │       Montserrat-Italic.ttf
│           │   │   │       Montserrat-Light.ttf
│           │   │   │       Montserrat-LightItalic.ttf
│           │   │   │       Montserrat-Medium.ttf
│           │   │   │       Montserrat-MediumItalic.ttf
│           │   │   │       Montserrat-Regular.ttf
│           │   │   │       Montserrat-SemiBold.ttf
│           │   │   │       Montserrat-SemiBoldItalic.ttf
│           │   │   │       Montserrat-Thin.ttf
│           │   │   │       Montserrat-ThinItalic.ttf
│           │   │   │       OFL.txt
│           │   │   │       
│           │   │   ├───poppins
│           │   │   │       Poppins-Black.ttf
│           │   │   │       Poppins-BlackItalic.ttf
│           │   │   │       Poppins-Bold.ttf
│           │   │   │       Poppins-BoldItalic.ttf
│           │   │   │       Poppins-ExtraBold.ttf
│           │   │   │       Poppins-ExtraBoldItalic.ttf
│           │   │   │       Poppins-ExtraLight.ttf
│           │   │   │       Poppins-ExtraLightItalic.ttf
│           │   │   │       Poppins-Italic.ttf
│           │   │   │       Poppins-Light.ttf
│           │   │   │       Poppins-LightItalic.ttf
│           │   │   │       Poppins-Medium.ttf
│           │   │   │       Poppins-MediumItalic.ttf
│           │   │   │       Poppins-Regular.ttf
│           │   │   │       Poppins-SemiBold.ttf
│           │   │   │       Poppins-SemiBoldItalic.ttf
│           │   │   │       Poppins-Thin.ttf
│           │   │   │       Poppins-ThinItalic.ttf
│           │   │   │       
│           │   │   └───ubuntu
│           │   │           Ubuntu-Bold.ttf
│           │   │           Ubuntu-BoldItalic.ttf
│           │   │           Ubuntu-Italic.ttf
│           │   │           Ubuntu-Light.ttf
│           │   │           Ubuntu-LightItalic.ttf
│           │   │           Ubuntu-Medium.ttf
│           │   │           Ubuntu-MediumItalic.ttf
│           │   │           Ubuntu-Regular.ttf
│           │   │           UFL.txt
│           │   │           
│           │   ├───images
│           │   │   │   blank_avatar.png
│           │   │   │   blog_1.jpg
│           │   │   │   Children.jpg
│           │   │   │   cloth_1.jpg
│           │   │   │   cloth_2.jpg
│           │   │   │   cloth_3.jpg
│           │   │   │   hero_1.jpg
│           │   │   │   hero_1.png
│           │   │   │   lapicero5.jpg
│           │   │   │   logo.png
│           │   │   │   Men.jpg
│           │   │   │   person_1.jpg
│           │   │   │   person_2.jpg
│           │   │   │   person_3.jpg
│           │   │   │   person_4.jpg
│           │   │   │   shoe.png
│           │   │   │   shoe_1.jpg
│           │   │   │   Women.jpg
│           │   │   │   
│           │   │   └───icons
│           │   │           favicon.ico
│           │   │           icon-google.png
│           │   │           map-marker.png
│           │   │           symbol-01.png
│           │   │           
│           │   ├───js
│           │   │       aos.js
│           │   │       bootstrap.min.js
│           │   │       jquery-3.3.1.min.js
│           │   │       jquery-ui.js
│           │   │       jquery.magnific-popup.min.js
│           │   │       main.js
│           │   │       owl.carousel.min.js
│           │   │       popper.min.js
│           │   │       slick.min.js
│           │   │       
│           │   ├───nav
│           │   │       sinSession.jsp
│           │   │       usuarioEncabezado.jsp
│           │   │       
│           │   ├───scss
│           │   │   │   .DS_Store
│           │   │   │   style.scss
│           │   │   │   _site-base.scss
│           │   │   │   _site-blocks.scss
│           │   │   │   _site-navbar.scss
│           │   │   │   
│           │   │   └───bootstrap
│           │   │       ├───css
│           │   │       │       bootstrap-grid.css
│           │   │       │       bootstrap-grid.css.map
│           │   │       │       bootstrap-grid.min.css
│           │   │       │       bootstrap-grid.min.css.map
│           │   │       │       bootstrap-reboot.css
│           │   │       │       bootstrap-reboot.css.map
│           │   │       │       bootstrap-reboot.min.css
│           │   │       │       bootstrap-reboot.min.css.map
│           │   │       │       bootstrap.css
│           │   │       │       bootstrap.css.map
│           │   │       │       bootstrap.min.css
│           │   │       │       bootstrap.min.css.map
│           │   │       │       
│           │   │       └───js
│           │   │               bootstrap.bundle.js
│           │   │               bootstrap.bundle.js.map
│           │   │               bootstrap.bundle.min.js
│           │   │               bootstrap.bundle.min.js.map
│           │   │               bootstrap.js
│           │   │               bootstrap.js.map
│           │   │               bootstrap.min.js
│           │   │               bootstrap.min.js.map
│           │   │               
│           │   └───vendor
│           │       ├───animate
│           │       │       animate.css
│           │       │       
│           │       ├───animsition
│           │       │   ├───css
│           │       │   │       animsition.css
│           │       │   │       animsition.min.css
│           │       │   │       
│           │       │   └───js
│           │       │           animsition.js
│           │       │           animsition.min.js
│           │       │           
│           │       ├───bootstrap
│           │       │   ├───css
│           │       │   │       bootstrap-grid.css
│           │       │   │       bootstrap-grid.css.map
│           │       │   │       bootstrap-grid.min.css
│           │       │   │       bootstrap-grid.min.css.map
│           │       │   │       bootstrap-reboot.css
│           │       │   │       bootstrap-reboot.css.map
│           │       │   │       bootstrap-reboot.min.css
│           │       │   │       bootstrap-reboot.min.css.map
│           │       │   │       bootstrap.css
│           │       │   │       bootstrap.css.map
│           │       │   │       bootstrap.min.css
│           │       │   │       bootstrap.min.css.map
│           │       │   │       
│           │       │   └───js
│           │       │           bootstrap.js
│           │       │           bootstrap.min.js
│           │       │           popper.js
│           │       │           popper.min.js
│           │       │           tooltip.js
│           │       │           
│           │       ├───countdowntime
│           │       │       countdowntime.js
│           │       │       
│           │       ├───css-hamburgers
│           │       │       hamburgers.css
│           │       │       hamburgers.min.css
│           │       │       
│           │       ├───daterangepicker
│           │       │       daterangepicker.css
│           │       │       daterangepicker.js
│           │       │       moment.js
│           │       │       moment.min.js
│           │       │       
│           │       ├───jquery
│           │       │       jquery-3.2.1.min.js
│           │       │       
│           │       ├───perfect-scrollbar
│           │       │       perfect-scrollbar.css
│           │       │       perfect-scrollbar.min.js
│           │       │       
│           │       └───select2
│           │               select2.css
│           │               select2.js
│           │               select2.min.css
│           │               select2.min.js
│           │               
│           └───WEB-INF
│                   web.xml
│                   
└───target
    │   webapp-form.war
    │   
    ├───classes
    │   └───org
    │       └───cahuas
    │           └───webapp
    │               └───servelet
    │                   └───cabeceras
    │                       ├───controlador
    │                       │       LoginServlet.class
    │                       │       LogoutServlet.class
    │                       │       ProductosServelet.class
    │                       │       
    │                       └───models
    │                           ├───filters
    │                           │       ConexionFilter.class
    │                           │       LoginAdminFilter.class
    │                           │       
    │                           ├───listeners
    │                           │       AplicacionListener.class
    │                           │       
    │                           ├───modelo
    │                           │       Carro.class
    │                           │       ItemCarro.class
    │                           │       Producto.class
    │                           │       Proveedor.class
    │                           │       Usuario.class
    │                           │       
    │                           ├───repositories
    │                           │       ProductoRepositoryImpl.class
    │                           │       ProveedorRepositoryImpl.class
    │                           │       Repository.class
    │                           │       RepositoryUsuario.class
    │                           │       UsuarioRepositoryJdbcImpl.class
    │                           │       
    │                           ├───services
    │                           │       LoginService.class
    │                           │       LoginServiceJdbcImpl.class
    │                           │       ProductoService.class
    │                           │       ProductoServiceJdbcImpl.class
    │                           │       ServiceJdbcException.class
    │                           │       
    │                           └───util
    │                                   ConexionBaseDatos.class
    │                                   
    ├───generated-sources
    │   └───annotations
    ├───maven-archiver
    │       pom.properties
    │       
    ├───maven-status
    │   └───maven-compiler-plugin
    │       └───compile
    │           └───default-compile
    │                   createdFiles.lst
    │                   inputFiles.lst
    │                   
    └───webapp-form
        │   index.html
        │   
        ├───admin
        │   │   index.jsp
        │   │   inventario.jsp
        │   │   
        │   └───assets
        │       ├───css
        │       │       style.css
        │       │       
        │       ├───imgs
        │       │       customer01.jpg
        │       │       customer02.jpg
        │       │       customer03.png
        │       │       icon.png
        │       │       
        │       └───js
        │               main.js
        │               modal.js
        │               
        ├───META-INF
        ├───usuario
        │   │   about.html
        │   │   cart.html
        │   │   checkout.html
        │   │   contact.html
        │   │   index.jsp
        │   │   login.jsp
        │   │   prepros-6.config
        │   │   shop-single.html
        │   │   shop.html
        │   │   thankyou.html
        │   │   
        │   ├───css
        │   │   │   aos.css
        │   │   │   bootstrap.min.css
        │   │   │   jquery-ui.css
        │   │   │   magnific-popup.css
        │   │   │   main.css
        │   │   │   owl.carousel.min.css
        │   │   │   owl.theme.default.min.css
        │   │   │   style.css
        │   │   │   util.css
        │   │   │   
        │   │   └───bootstrap
        │   │           bootstrap-grid.css
        │   │           bootstrap-reboot.css
        │   │           
        │   ├───fonts
        │   │   ├───font-awesome-4.7.0
        │   │   │   │   HELP-US-OUT.txt
        │   │   │   │   
        │   │   │   ├───css
        │   │   │   │       font-awesome.css
        │   │   │   │       font-awesome.min.css
        │   │   │   │       
        │   │   │   ├───fonts
        │   │   │   │       fontawesome-webfont.eot
        │   │   │   │       fontawesome-webfont.svg
        │   │   │   │       fontawesome-webfont.ttf
        │   │   │   │       fontawesome-webfont.woff
        │   │   │   │       fontawesome-webfont.woff2
        │   │   │   │       FontAwesome.otf
        │   │   │   │       
        │   │   │   ├───less
        │   │   │   │       animated.less
        │   │   │   │       bordered-pulled.less
        │   │   │   │       core.less
        │   │   │   │       fixed-width.less
        │   │   │   │       font-awesome.less
        │   │   │   │       icons.less
        │   │   │   │       larger.less
        │   │   │   │       list.less
        │   │   │   │       mixins.less
        │   │   │   │       path.less
        │   │   │   │       rotated-flipped.less
        │   │   │   │       screen-reader.less
        │   │   │   │       stacked.less
        │   │   │   │       variables.less
        │   │   │   │       
        │   │   │   └───scss
        │   │   │           font-awesome.scss
        │   │   │           _animated.scss
        │   │   │           _bordered-pulled.scss
        │   │   │           _core.scss
        │   │   │           _fixed-width.scss
        │   │   │           _icons.scss
        │   │   │           _larger.scss
        │   │   │           _list.scss
        │   │   │           _mixins.scss
        │   │   │           _path.scss
        │   │   │           _rotated-flipped.scss
        │   │   │           _screen-reader.scss
        │   │   │           _stacked.scss
        │   │   │           _variables.scss
        │   │   │           
        │   │   ├───icomoon
        │   │   │   │   demo.html
        │   │   │   │   Read Me.txt
        │   │   │   │   selection.json
        │   │   │   │   style.css
        │   │   │   │   
        │   │   │   ├───demo-files
        │   │   │   │       demo.css
        │   │   │   │       demo.js
        │   │   │   │       
        │   │   │   └───fonts
        │   │   │           icomoon.eot
        │   │   │           icomoon.svg
        │   │   │           icomoon.ttf
        │   │   │           icomoon.woff
        │   │   │           
        │   │   ├───Linearicons-Free-v1.0.0
        │   │   │   │   icon-font.min.css
        │   │   │   │   
        │   │   │   └───WebFont
        │   │   │           Linearicons-Free.eot
        │   │   │           Linearicons-Free.svg
        │   │   │           Linearicons-Free.ttf
        │   │   │           Linearicons-Free.woff
        │   │   │           Linearicons-Free.woff2
        │   │   │           
        │   │   ├───montserrat
        │   │   │       Montserrat-Black.ttf
        │   │   │       Montserrat-BlackItalic.ttf
        │   │   │       Montserrat-Bold.ttf
        │   │   │       Montserrat-BoldItalic.ttf
        │   │   │       Montserrat-ExtraBold.ttf
        │   │   │       Montserrat-ExtraBoldItalic.ttf
        │   │   │       Montserrat-ExtraLight.ttf
        │   │   │       Montserrat-ExtraLightItalic.ttf
        │   │   │       Montserrat-Italic.ttf
        │   │   │       Montserrat-Light.ttf
        │   │   │       Montserrat-LightItalic.ttf
        │   │   │       Montserrat-Medium.ttf
        │   │   │       Montserrat-MediumItalic.ttf
        │   │   │       Montserrat-Regular.ttf
        │   │   │       Montserrat-SemiBold.ttf
        │   │   │       Montserrat-SemiBoldItalic.ttf
        │   │   │       Montserrat-Thin.ttf
        │   │   │       Montserrat-ThinItalic.ttf
        │   │   │       OFL.txt
        │   │   │       
        │   │   ├───poppins
        │   │   │       Poppins-Black.ttf
        │   │   │       Poppins-BlackItalic.ttf
        │   │   │       Poppins-Bold.ttf
        │   │   │       Poppins-BoldItalic.ttf
        │   │   │       Poppins-ExtraBold.ttf
        │   │   │       Poppins-ExtraBoldItalic.ttf
        │   │   │       Poppins-ExtraLight.ttf
        │   │   │       Poppins-ExtraLightItalic.ttf
        │   │   │       Poppins-Italic.ttf
        │   │   │       Poppins-Light.ttf
        │   │   │       Poppins-LightItalic.ttf
        │   │   │       Poppins-Medium.ttf
        │   │   │       Poppins-MediumItalic.ttf
        │   │   │       Poppins-Regular.ttf
        │   │   │       Poppins-SemiBold.ttf
        │   │   │       Poppins-SemiBoldItalic.ttf
        │   │   │       Poppins-Thin.ttf
        │   │   │       Poppins-ThinItalic.ttf
        │   │   │       
        │   │   └───ubuntu
        │   │           Ubuntu-Bold.ttf
        │   │           Ubuntu-BoldItalic.ttf
        │   │           Ubuntu-Italic.ttf
        │   │           Ubuntu-Light.ttf
        │   │           Ubuntu-LightItalic.ttf
        │   │           Ubuntu-Medium.ttf
        │   │           Ubuntu-MediumItalic.ttf
        │   │           Ubuntu-Regular.ttf
        │   │           UFL.txt
        │   │           
        │   ├───images
        │   │   │   blank_avatar.png
        │   │   │   blog_1.jpg
        │   │   │   Children.jpg
        │   │   │   cloth_1.jpg
        │   │   │   cloth_2.jpg
        │   │   │   cloth_3.jpg
        │   │   │   hero_1.jpg
        │   │   │   hero_1.png
        │   │   │   lapicero5.jpg
        │   │   │   logo.png
        │   │   │   Men.jpg
        │   │   │   person_1.jpg
        │   │   │   person_2.jpg
        │   │   │   person_3.jpg
        │   │   │   person_4.jpg
        │   │   │   shoe.png
        │   │   │   shoe_1.jpg
        │   │   │   Women.jpg
        │   │   │   
        │   │   └───icons
        │   │           favicon.ico
        │   │           icon-google.png
        │   │           map-marker.png
        │   │           symbol-01.png
        │   │           
        │   ├───js
        │   │       aos.js
        │   │       bootstrap.min.js
        │   │       jquery-3.3.1.min.js
        │   │       jquery-ui.js
        │   │       jquery.magnific-popup.min.js
        │   │       main.js
        │   │       owl.carousel.min.js
        │   │       popper.min.js
        │   │       slick.min.js
        │   │       
        │   ├───nav
        │   │       sinSession.jsp
        │   │       usuarioEncabezado.jsp
        │   │       
        │   ├───scss
        │   │   │   style.scss
        │   │   │   _site-base.scss
        │   │   │   _site-blocks.scss
        │   │   │   _site-navbar.scss
        │   │   │   
        │   │   └───bootstrap
        │   │       ├───css
        │   │       │       bootstrap-grid.css
        │   │       │       bootstrap-grid.css.map
        │   │       │       bootstrap-grid.min.css
        │   │       │       bootstrap-grid.min.css.map
        │   │       │       bootstrap-reboot.css
        │   │       │       bootstrap-reboot.css.map
        │   │       │       bootstrap-reboot.min.css
        │   │       │       bootstrap-reboot.min.css.map
        │   │       │       bootstrap.css
        │   │       │       bootstrap.css.map
        │   │       │       bootstrap.min.css
        │   │       │       bootstrap.min.css.map
        │   │       │       
        │   │       └───js
        │   │               bootstrap.bundle.js
        │   │               bootstrap.bundle.js.map
        │   │               bootstrap.bundle.min.js
        │   │               bootstrap.bundle.min.js.map
        │   │               bootstrap.js
        │   │               bootstrap.js.map
        │   │               bootstrap.min.js
        │   │               bootstrap.min.js.map
        │   │               
        │   └───vendor
        │       ├───animate
        │       │       animate.css
        │       │       
        │       ├───animsition
        │       │   ├───css
        │       │   │       animsition.css
        │       │   │       animsition.min.css
        │       │   │       
        │       │   └───js
        │       │           animsition.js
        │       │           animsition.min.js
        │       │           
        │       ├───bootstrap
        │       │   ├───css
        │       │   │       bootstrap-grid.css
        │       │   │       bootstrap-grid.css.map
        │       │   │       bootstrap-grid.min.css
        │       │   │       bootstrap-grid.min.css.map
        │       │   │       bootstrap-reboot.css
        │       │   │       bootstrap-reboot.css.map
        │       │   │       bootstrap-reboot.min.css
        │       │   │       bootstrap-reboot.min.css.map
        │       │   │       bootstrap.css
        │       │   │       bootstrap.css.map
        │       │   │       bootstrap.min.css
        │       │   │       bootstrap.min.css.map
        │       │   │       
        │       │   └───js
        │       │           bootstrap.js
        │       │           bootstrap.min.js
        │       │           popper.js
        │       │           popper.min.js
        │       │           tooltip.js
        │       │           
        │       ├───countdowntime
        │       │       countdowntime.js
        │       │       
        │       ├───css-hamburgers
        │       │       hamburgers.css
        │       │       hamburgers.min.css
        │       │       
        │       ├───daterangepicker
        │       │       daterangepicker.css
        │       │       daterangepicker.js
        │       │       moment.js
        │       │       moment.min.js
        │       │       
        │       ├───jquery
        │       │       jquery-3.2.1.min.js
        │       │       
        │       ├───perfect-scrollbar
        │       │       perfect-scrollbar.css
        │       │       perfect-scrollbar.min.js
        │       │       
        │       └───select2
        │               select2.css
        │               select2.js
        │               select2.min.css
        │               select2.min.js
        │               
        └───WEB-INF
            │   web.xml
            │   
            ├───classes
            │   └───org
            │       └───cahuas
            │           └───webapp
            │               └───servelet
            │                   └───cabeceras
            │                       ├───controlador
            │                       │       LoginServlet.class
            │                       │       LogoutServlet.class
            │                       │       ProductosServelet.class
            │                       │       
            │                       └───models
            │                           ├───filters
            │                           │       ConexionFilter.class
            │                           │       LoginAdminFilter.class
            │                           │       
            │                           ├───listeners
            │                           │       AplicacionListener.class
            │                           │       
            │                           ├───modelo
            │                           │       Carro.class
            │                           │       ItemCarro.class
            │                           │       Producto.class
            │                           │       Proveedor.class
            │                           │       Usuario.class
            │                           │       
            │                           ├───repositories
            │                           │       ProductoRepositoryImpl.class
            │                           │       ProveedorRepositoryImpl.class
            │                           │       Repository.class
            │                           │       RepositoryUsuario.class
            │                           │       UsuarioRepositoryJdbcImpl.class
            │                           │       
            │                           ├───services
            │                           │       LoginService.class
            │                           │       LoginServiceJdbcImpl.class
            │                           │       ProductoService.class
            │                           │       ProductoServiceJdbcImpl.class
            │                           │       ServiceJdbcException.class
            │                           │       
            │                           └───util
            │                                   ConexionBaseDatos.class
            │                                   
            └───lib
                    aether-api-1.0.0.v20140518.jar
                    aether-util-1.0.0.v20140518.jar
                    jackson-annotations-2.14.1.jar
                    jackson-core-2.14.1.jar
                    jackson-databind-2.14.1.jar
                    plexus-utils-3.5.0.jar
                    
```


## API Endpoints

- **POST /api/login**: Realiza el inicio de sesión de un usuario o administrador.
- **GET /api/logout**: Realiza la salida de sesión del usuario o administrador.
- **GET /api/productos**: Añade un producto al carrito de compras.

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
## Pruebas

### Tipos de Pruebas
- **Pruebas Unitarias**: Validan el comportamiento de métodos individuales.
- **Pruebas de Integración**: Aseguran que los diferentes módulos funcionen juntos correctamente.

### Herramientas Utilizadas
- **JUnit**: Para pruebas unitarias.
- **Mockito**: Para pruebas de simulación.

### Ejecutar Pruebas
Para ejecutar las pruebas unitarias, usa Maven:
```bash
mvn test
=======
"# Shallom" 
>>>>>>> 3e18fddc8493ee94321bf490b1c997fdb47b3a73
