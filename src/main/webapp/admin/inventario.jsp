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
    <style>
        .btn {
            padding: 5px;
            background-color: transparent;
            border: none;
            cursor: pointer;
        }

        .btn.modificar i {
            color: green;
            font-size: 18px;
        }

        .btn.eliminar i {
            color: red;
            font-size: 18px;
        }

        /* Hover para cambiar color */
        .btn.modificar:hover i {
            color: darkgreen;
        }

        .btn.eliminar:hover i {
            color: darkred;
        }

        /* Modal */
        .modal {
            display: none;
            /* Oculto por defecto */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            background-color: white;
            margin: 15% auto;
            padding: 20px;
            width: 80%;
            max-width: 500px;
            border-radius: 10px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover,
        .close:focus {
            color: black;
        }

        .modal-content form label,
        .modal-content form input {
            display: block;
            margin-bottom: 10px;
            width: 100%;
        }

        .modal-content form input {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .btn.guardar {
            background-color: var(--blue);
        }
    </style>
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
                        <span class="title"> </span>
                    </a>
                </li>
                <% for(Producto p: productos){%>
                    <tr>
                        <td><%=p.getId()%></td>
                        <td><%=p.getNombre()%></td>
                        <% } %>
                    </tr>
                    <%}%>

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
                        <a href="#" class="btn">Ver todo</a>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <td>Nombre</td>
                                <td>Precio</td>
                                <td>Cantidad</td>
                                <td>Total</td>
                                <td>Acciones</td>
                            </tr>
                        </thead>

                        <tbody>

                            <tr>
                                <td>Lapiz 2B Faber-Castell</td>
                                <td>S/ 0.50</td>
                                <td>40</td>
                                <td>S/20.00</td>

                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <td>Regla 1 metro Genérico</td>
                                <td>S/ 1.00</td>
                                <td>15</td>
                                <td>S/15.00</td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>Lapiz 2B Faber-Castell</td>
                                <td>S/ 0.50</td>
                                <td>40</td>
                                <td>S/20.00</td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <td>Regla 1 metro Genérico</td>
                                <td>S/ 1.00</td>
                                <td>15</td>
                                <td>S/15.00</td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <td>Cartulina Duplex</td>
                                <td>S/2.00</td>
                                <td>5</td>
                                <td>S/10.00</td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <td>CASIO FX-350</td>
                                <td>S/40.00</td>
                                <td>5</td>
                                <td>S/200.00</td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <td>Borrador Artesco</td>
                                <td>S/ 0.80</td>
                                <td>50</td>
                                <td>S/40.00</td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <td>Cuaderno anillado Surco</td>
                                <td>S/6.00</td>
                                <td>10</td>
                                <td>S/60.00</td>
                                <td>
                                    <button class="btn modificar"
                                        onclick="openModal('Lapiz 2B Faber-Castell', '0.50', '40')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn eliminar">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                    <!-- Modal -->
                    <div id="modal" class="modal">
                        <div class="modal-content">
                            <span class="close" onclick="closeModal()">&times;</span>
                            <h2>Modificar Producto</h2><br>
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

                        <!-- ================= New Customers ================ -->
                    </div>
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