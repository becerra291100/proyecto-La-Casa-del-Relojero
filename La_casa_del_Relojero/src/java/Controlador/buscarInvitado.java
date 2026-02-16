package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(name = "buscarInvitado", urlPatterns = {"/buscarInvitado"})
public class buscarInvitado extends HttpServlet {

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
            out.println("<title>Servlet buscarInvitado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet buscarInvitado at " + request.getContextPath() + "</h1>");
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
         response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    // Información de la base de datos
    String usuario = "root";  // Usuario de la base de datos
    String contraseña = "3313130580";  // Contraseña de la base de datos
    String url = "jdbc:mariadb://localhost:3306/la_casa_del_relojero";  // URL de la base de datos

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Cargar el driver de MariaDB
        Class.forName("org.mariadb.jdbc.Driver");

        // Conectar a la base de datos
        con = DriverManager.getConnection(url, usuario, contraseña);

        // Obtener el nombre del invitado desde la solicitud AJAX
        String nombre = request.getParameter("nom_in");

        // Consulta SQL para obtener invitados cuyo nombre coincida con la búsqueda
        String query = "SELECT id_in, nom_in, numero_mesa, parentesco FROM invitados WHERE nom_in LIKE ?";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, "%" + nombre + "%");  // Buscar coincidencias parciales con el nombre

        // Ejecutar la consulta
        rs = pstmt.executeQuery();

        // Generar la respuesta HTML para actualizar la tabla de resultados
        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getInt("id_in") + "</td>");
            out.println("<td>" + rs.getString("nom_in") + "</td>");
            out.println("<td>" + rs.getInt("numero_mesa") + "</td>");
            out.println("<td>" + rs.getString("parentesco") + "</td>");
            out.println("</tr>");
        }

    } catch (ClassNotFoundException | SQLException e) {
        out.println("<h1>Error en la conexión o consulta: " + e.getMessage() + "</h1>");
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
