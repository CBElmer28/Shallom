<%@page contentType="text/html" pageEncoding="UTF-8" import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto"%>
<%@page import="java.util.List"%>
<%List<Producto> productos = (List<Producto>) request.getAttribute("productos");%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="icon" href="assets/imgs/icon.png" type="image/x-icon">
    <link rel="stylesheet" href="/webbs/admin/assets/css/style.css">
    <link rel="stylesheet" href="/webbs/admin/assets/css/modal.css">
</head>

<body>
    <!-- =============== Navigation ================ -->
    <div class="container">
        <div class="navigation">
            <ul>
                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-ico><img src="assets/imgs/icon.png"></ion-icon>
                        </span>
                        <span class="namsp">SHALOM</span>
                    </a>
                </li>

                <li>
                    <a href="/webbs/admin/index.jsp">
                        <span class="icon">
                            <ion-icon name="home"></ion-icon>
                        </span>
                        <span class="title">Dashboard </span>
                    </a>
                </li>

                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="people"></ion-icon>
                        </span>
                        <span class="title">Clientes</span>
                    </a>
                </li>

                <li>
                    <a href="/webbs/admin/inventario.jsp">
                        <span class="icon">
                            <ion-icon name="archive"></ion-icon>
                        </span>
                        <span class="title">Inventario</span>
                    </a>
                </li>

                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="document-text"></ion-icon>
                        </span>
                        <span class="title">Documentos</span>
                    </a>
                </li>

                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="settings"></ion-icon>
                        </span>
                        <span class="title">Configuración</span>
                    </a>
                </li>

                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="help-circle"></ion-icon>
                        </span>
                        <span class="title">Ayuda</span>
                    </a>
                </li>

                <li>
                    <a href="/webbs/logout">
                        <span class="icon">
                            <ion-icon name="log-out"></ion-icon>
                        </span>
                        <span class="title">Salir</span>
                    </a>
                </li>
            </ul>
        </div>

        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="user">
                    <img src="assets/imgs/customer03.png" alt="">
                </div>
            </div>

            <!-- ======================= Cards ================== -->
            <div class="details">

                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Pedidos Recientes</h2>
                       <button class="btn" onclick="openAddModal()">Crear Producto</button>


                    </div>

                    <table>
                        <thead>
                            <tr>
                                <td>Nombres</td>
                                <td>Precio</td>
                                <td>Cantidad</td>
                                <td>Total</td>
                                <td>imagen</td>
                                <td>Acciones</td>
                            </tr>
                        </thead>

                        <tbody>
                          <%for(Producto p: productos){%>
                            <tr>
                                <td><%=p.getNom()%></td>
                                <td>S/<%=p.getPrecio()%></td>
                                <td>5</td>
                                <td>20</td>
                                <td><img src="<%= getServletContext().getRealPath("/") + "imagenes/" + p.getRuta_imagen() %>" alt=""></td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModifyModal('<%=p.getNom()%>', '<%=p.getPrecio()%>', '<%=p.getStock()%>')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>
                             <%}%>
                        </tbody>
                    </table>

        <!----------------------------- MODALES ----------------------------->

                    <!-- Modal para modificar producto -->
                       <div id="modifyModal" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeModifyModal()">&times;</span>
                                <h2>Modificar Producto</h2>
                                <form>
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" id="nombre" name="nombre">
                                    <label for="precio">Precio:</label>
                                    <input type="text" id="precio" name="precio">
                                    <label for="cantidad">Cantidad:</label>
                                    <input type="number" id="cantidad" name="cantidad">
                                    <button type="submit" class="btn guardar">Guardar Cambios</button>
                                </form>
                            </div>
                        </div>

                        <!-- Modal para agregar producto -->
                        <div id="addModal" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeAddModal()">&times;</span>
                                <h2>Crear Producto</h2>
                                <form action="/webbs/agregar-producto" method="POST" enctype="multipart/form-data">
                                     Nombre: <input type="text" name="nombre" i required><br>
                                        Categoría: <input type="text" name="cat"  required><br>
                                        Precio: <input type="text" name="precio"  required><br>
                                        Stock: <input type="number" name="stock"  required><br>
                                        Proveedor ID: <input type="number" name="id_proveedor"  required><br>
                                        Imagen: <input type="file" name="imagen"  accept="image/*" required><br>
                                        <input type="submit" value="Subir Producto">
                                </form>
                            </div>
                        </div>

        <!---------------------------------------------------------------------->

                </div>
            </div>



            <!-- =========== Scripts =========  -->
            <script src="/webbs/admin/assets/js/main.js"></script>
            <script src="/webbs/admin/assets/js/modal.js"></script>

            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
            <!-- ====== ionicons ======= -->
            <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>


</body>

</html>