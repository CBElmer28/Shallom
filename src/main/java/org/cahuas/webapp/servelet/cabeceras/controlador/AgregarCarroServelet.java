@WebServlet("/usuario/carro/agregar")
public class AgregarCarroServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperar el id del producto y la cantidad desde los parámetros de la solicitud
        Long id = Long.parseLong(req.getParameter("id")); // Convertir el id del producto a Long
        int cantidad = Integer.parseInt(req.getParameter("cantidad")); // Convertir la cantidad a int

        // Convertir el id de Long a int (puede perder precisión si el id es muy grande, pero en general es seguro aquí)
        int idd = Math.toIntExact(id);

        // Conexión a la base de datos
        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConnection();  // Obtener conexión a la base de datos
        } catch (SQLException e) {
            throw new RuntimeException(e); // Manejo de errores en caso de que no se pueda conectar a la base de datos
        }

        // Crear servicio de productos utilizando la conexión a la base de datos
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        
        // Recuperar el producto desde la base de datos mediante su id
        Optional<Producto> producto = service.porId(idd); 
        if (producto.isPresent()) {  // Verificar si el producto existe
            // Recuperar la sesión actual del usuario

            HttpSession session = req.getSession();
            
            // Recuperar el carrito de compras de la sesión
            Carro carro = (Carro) session.getAttribute("carro");


            // Buscar si el producto ya está en el carrito
            Optional<ItemCarro> optionalItem = carro.getItems().stream()
                    .filter(i -> i.getProducto().getId() == idd)
                    .findFirst(); // Filtra y encuentra el primer item que tiene el mismo id del producto

            if (optionalItem.isPresent()) {  // Si el producto ya existe en el carrito
                ItemCarro itemExistente = optionalItem.get();
                itemExistente.setCantidad(itemExistente.getCantidad() + cantidad);  // Aumentar la cantidad
            } else {  // Si el producto no existe en el carrito
                ItemCarro nuevoItem = new ItemCarro(cantidad, producto.get());  // Crear un nuevo item
                carro.addItemCarro(nuevoItem);  // Añadir el nuevo item al carrito
            }
        } else {
            // En caso de que no exista el producto en la base de datos, enviar un error HTTP 400 (Bad Request)

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO EN LA BASE DE DATOS");
        }

        // Redirigir al usuario a la página del carrito
        resp.sendRedirect(req.getContextPath() + "/usuario/cart.jsp");
    }
}
