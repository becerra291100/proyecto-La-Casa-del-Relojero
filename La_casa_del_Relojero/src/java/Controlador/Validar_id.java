import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author diego
 */
@WebServlet(name = "Validar_id", urlPatterns = {"/Validar_id"})
public class Validar_id extends HttpServlet {

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
            out.println("<title>Servlet Validar_id</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar_id at " + request.getContextPath() + "</h1>");
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
    }

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
    ResultSet rs = null;

    try {
        // Cargar el driver de MariaDB
        Class.forName("org.mariadb.jdbc.Driver");

        // Conectar a la base de datos
        con = DriverManager.getConnection(url, usuario, contraseña);

        // Obtener el ID del evento del formulario como String y convertirlo a int
        String id_evento_str = request.getParameter("id_evento");
        int id_evento = Integer.parseInt(id_evento_str);  // Convertir de String a int

        // Consulta SQL para obtener solo las columnas necesarias
        String query = "SELECT nom_in, numero_mesa, parentesco FROM invitados WHERE id_evento = ? ORDER BY nom_in ASC";

        pstmt = con.prepareStatement(query);
        pstmt.setInt(1, id_evento);  // Usar setInt para pasar el valor entero

        // Ejecutar la consulta
        rs = pstmt.executeQuery();

        // Crear una lista para almacenar los resultados
        List<Invitado> listaInvitado = new ArrayList<>();

        // Procesar los resultados
        while (rs.next()) {
            Invitado invitado = new Invitado();
            invitado.setNombre(rs.getString("nom_in"));
            invitado.setNumeroMesa(rs.getInt("numero_mesa"));
            invitado.setParentesco(rs.getString("parentesco"));
            listaInvitado.add(invitado);
        }

        // Verificar si se encontraron invitados
        if (!listaInvitado.isEmpty()) {
            // Pasar la lista de invitados al JSP
            request.setAttribute("listaInvitado", listaInvitado);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Busqueda.jsp");
            dispatcher.forward(request, response);
        } else {
            out.println("<h1>No se encontraron invitados para el evento con ID: " + id_evento + "</h1>");
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public class Invitado {
        private int id;
        private String nombre;
        private int numeroMesa;
        private String parentesco;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getNumeroMesa() {
            return numeroMesa;
        }

        public void setNumeroMesa(int numeroMesa) {
            this.numeroMesa = numeroMesa;
        }

        public String getParentesco() {
            return parentesco;
        }

        public void setParentesco(String parentesco) {
            this.parentesco = parentesco;
        }
    }
}
