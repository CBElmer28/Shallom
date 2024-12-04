@WebServlet("/config")
public class configUsuarioServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String username = req.getParameter("nombre");
        String user = req.getParameter("user");
        String correo = req.getParameter("correo");
        int telefono;
        try {
            telefono = Integer.valueOf(req.getParameter("telefono"));
        } catch (NumberFormatException e) {
            telefono = 0;  // En caso de que no se ingrese un número válido
        }
        int dni = Integer.parseInt(req.getParameter("DNI"));
        try {
            dni = Integer.valueOf(req.getParameter("DNI"));
        } catch (NumberFormatException e) {
            dni = 0;  // En caso de que no se ingrese un número válido
        }
        String password = req.getParameter("password");

        // Validar los datos
        if (username == null || user == null || correo == null || password == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
            return;
        }

        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConnection();  // Establecer la conexión a la base de datos
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Iniciar sesión y establecer el auto-commit en falso para transacciones
            HttpSession session = req.getSession();
            conn.setAutoCommit(false);

            ClienteServiceJdbcImpl pro = new ClienteServiceJdbcImpl(conn);
            LoginServiceJdbcImpl usuario = new LoginServiceJdbcImpl(conn);

            // Obtener el usuario de la sesión
            Usuario nuevo = (Usuario) session.getAttribute("usuario");
            String t = "usu";  // Tipo de usuario

            // Editar la cuenta de usuario en la base de datos
            usuario.editarCuenta(nuevo.getId(), dni, user, password, t);

            // Confirmar la transacción de la cuenta de usuario
            conn.commit();

            // Obtener el cliente de la sesión
            Cliente c = (Cliente) session.getAttribute("cliente");

            // Editar la información del cliente en la base de datos
            pro.editarCliente(c.getId(), correo, telefono, nuevo.getId(), username);

            // Confirmar la transacción del cliente
            conn.commit();

            // Redirigir a la página principal del usuario
            resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");

        } catch (SQLException e) {
            try {
                conn.rollback();  // En caso de error, deshacer la transacción
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            throw new ServletException("Error al registrar usuario y cliente", e);
        } finally {
            try {
                conn.setAutoCommit(true);  // Restaurar el auto-commit por defecto
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                conn.close();  // Cerrar la conexión para liberar recursos
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
