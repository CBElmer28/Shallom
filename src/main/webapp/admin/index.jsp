<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
    <!-- ======= Styles ====== -->
    <link rel="icon" href="assets/imgs/icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/style.css">
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
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="home"></ion-icon>
                        </span>
                        <span class="title">Dashboard</span>
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
                    <a href="/webbs/productos">
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
                        <span class="title">Configuracion</span>
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
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers">1,504</div>
                        <div class="cardName">Vistas diarias</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="eye-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">100</div>
                        <div class="cardName">Transacciones</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="bag-handle-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">54</div>
                        <div class="cardName">Pedidos</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="clipboard-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">S/ 1,842</div>
                        <div class="cardName">Venta del día</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cash-outline"></ion-icon>
                    </div>
                </div>
            </div>

            <!-- ================ Order Details List ================= -->
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
                                <td>Estado</td>
                            </tr>
                        </thead>

                        <tbody>


                            <tr>
                                <td>CASIO FX-350</td>
                                <td>S/40.00</td>
                                <td>5</td>
                                <td>S/200.00</td>
                                <td><span class="status inProgress">En ruta</span></td>
                            </tr>

                            <tr>
                                <td>Borrador Artesco</td>
                                <td>S/ 0.80</td>
                                <td>50</td>
                                <td>S/ 40.00</p></td>
                                <td><span class="status return">Cancelado</span></td>
                            </tr>

                            <tr>
                                <td>Cuaderno anillado Surco</td>
                                <td>S/6.00</td>
                                <td>10</td>
                                <td>S/60.00</td>
                                <td><span class="status delivered">Entregado</span></td>
                            </tr>

                            <tr>
                                <td>Grapador Ova</td>
                                <td>S/12.00</td>
                                <td>4</td>
                                <td>S/48.00</td>
                                <td><span class="status delivered">Entregado</span></td>
                            </tr>

                            <tr>
                                <td>Archivador acordeón beautone A4</td>
                                <td>S/34.00</td>
                                <td>2</td>
                                <td>S/68.00</td>
                                <td><span class="status delivered">Entregado</span></td>
                            </tr>

                            <tr>
                                <td>Plumón Faber-Castell Fiesta 45 Estuche Cartón</td>
                                <td>S/20.00</td>
                                <td>2</td>
                                <td>S/40.00</td>
                                <td><span class="status inProgress">En ruta</span></td>
                            </tr>

                            <tr>
                                <td>Papel Fotografico OFFICE</td>
                                <td>S/14.00</td>
                                <td>7</td>
                                <td>S/84.00</td>
                                <td><span class="status pending">Pendiente</span></td>
                            </tr>

                            <tr>
                                <td>Goma en barra x 50ml</td>
                                <td>S/0.80</td>
                                <td>36</td>
                                <td>S/28.80</td>
                                <td><span class="status inProgress">En ruta</span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <!-- ================= New Customers ================ -->
                <div class="recentCustomers">
                    <div class="cardHeader">
                        <h2>Clientes Recientes</h2>
                    </div>

                    <table>
                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer02.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>Gilberto <br> <span>Frecuente</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer01.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>Amit <br> <span>Nuevo</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer02.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>David <br> <span>Frecuente</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer01.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>Felipe <br> <span>Frecuente</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer02.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>Jesús <br> <span>Nuevo</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer01.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>Luis <br> <span>Frecuente</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer01.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>Pedro <br> <span>Nuevo</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer02.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>Alberto <br> <span>Nuevo</span></h4>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>