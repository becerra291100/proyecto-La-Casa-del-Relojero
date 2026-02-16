package config;

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
@WebServlet(urlPatterns = {"/DBPG"})
public class DBPG extends HttpServlet {

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
            out.println("<title>Servlet DBPG</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DBPG at " + request.getContextPath() + "</h1>");
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

    String usuario = "root";
    String contraseña = "3313130580";
    String url = "jdbc:mariadb://localhost:3306/la_casa_del_relojero";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Class.forName("org.mariadb.jdbc.Driver"); // Corregido
        con = DriverManager.getConnection(url, usuario, contraseña);

        // Consulta para obtener el nombre y el estado del usuario
        String query = "SELECT permiso, nombre_completo, contrasena FROM usuario WHERE nombre_completo = ? AND contrasena = ?"; // Corregido
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, request.getParameter("nombre_completo")); // Corregido
        pstmt.setString(2, request.getParameter("contrasena")); // Corregido
        rs = pstmt.executeQuery();

        if (rs.next()) {
            // Obtener el nombre del usuario
            String nombre_completo = rs.getString("nombre_completo");

            // Establecer la CURP como atributo de sesión
            request.getSession().setAttribute("nombre_completo", nombre_completo);

            // Obtener el permiso del usuario
            int permiso = rs.getInt("permiso");
            // Guardar el permiso en la sesión después de validar al usuario
            request.getSession().setAttribute("permiso", rs.getInt("permiso"));
            switch (permiso) {
                case 1:
                    // Redireccionar al enlace para administradores
                    response.sendRedirect("http://localhost:8080/La_casa_del_Relojero/Admin.jsp");
                    break;
                case 2:
                    // Redireccionar al enlace para usuarios normales
                    response.sendRedirect("http://localhost:8080/La_casa_del_Relojero/Novios.jsp");
                    break;
                default:
                    out.println("<h1>Usuario desconocido</h1>");
                    break;
            }
        } else {
            out.println("<h1>Usuario o contraseña incorrecta</h1>");
        }

    } catch (ClassNotFoundException | SQLException e) {
    out.println("<h1>Error en la conexión o consulta: " + e.getMessage() + "</h1>");
    e.printStackTrace(); // Sigue imprimiendo el stack trace en la consola
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
