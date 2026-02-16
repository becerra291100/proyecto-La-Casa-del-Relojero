import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author diego
 */
@WebServlet(name = "Insertar", urlPatterns = {"/Insertar"})
public class Insertar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Insertar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Insertar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Información de la base de datos
        String usuario = "root";  // Usuario de la base de datos
        String contraseña = "3313130580";  // Contraseña de la base de datos
        String url = "jdbc:mariadb://localhost:3306/la_casa_del_relojero";  // URL de la base de datos

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Cargar el driver de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");

            // Conectar a la base de datos
            con = DriverManager.getConnection(url, usuario, contraseña);

            // Consulta SQL para insertar un nuevo usuario
            String query = "INSERT INTO usuario (nombre_completo, contrasena, fecha_evento, sexo, permiso) VALUES (?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            // Obtener los parámetros del formulario y asignarlos a la consulta
            pstmt.setString(1, request.getParameter("nombre_completo"));
            pstmt.setString(2, request.getParameter("contrasena"));
            pstmt.setString(3, request.getParameter("Fecha_evento"));
            pstmt.setString(4, request.getParameter("sexo"));

            // Asignar el permiso 2 (usuario normal)
            pstmt.setInt(5, 2);  // Los usuarios normales tienen permiso 2

            // Ejecutar la inserción
            int result = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (result > 0) {
    // Redirigir a la página principal con un parámetro para indicar éxito
                response.sendRedirect("http://localhost:8080/La_casa_del_Relojero/PaginaPrincipal.jsp?registro=exitoso");
            } else {
                out.println("<h1>Error al registrar el usuario.</h1>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h1>Error en la conexión o inserción: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

