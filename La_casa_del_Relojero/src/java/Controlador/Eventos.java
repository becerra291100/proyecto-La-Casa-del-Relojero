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
@WebServlet(name = "Eventos", urlPatterns = {"/Eventos"})
public class Eventos extends HttpServlet {

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
            out.println("<title>Servlet Eventos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Eventos at " + request.getContextPath() + "</h1>");
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

    String usuario = "root";  
    String contraseña = "3313130580";  
    String url = "jdbc:mariadb://localhost:3306/la_casa_del_relojero";  

    // Inicializar las variables de conexión.
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Evento> listaEventos = new ArrayList<>(); // Lista para almacenar los eventos

        try {
            // Cargar el driver de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            // Establecer conexión con la base de datos
            con = DriverManager.getConnection(url, usuario, contraseña);

            // Consulta para seleccionar las bodas
            String sql = "SELECT id_evento, nom_evento, informacion, tipo_evento, cant_invitados FROM evento WHERE tipo_evento = 'Boda' ORDER BY nom_evento ASC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Procesar el resultado de la consulta
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setIdEvento(rs.getInt("id_evento"));
                evento.setNomEvento(rs.getString("nom_evento"));
                evento.setInformacion(rs.getString("informacion"));
                evento.setTipoEvento(rs.getString("tipo_evento"));
                evento.setCantInv(rs.getInt("cant_invitados"));
                listaEventos.add(evento); // Agregar cada evento a la lista
            }

            // Pasar la lista de eventos al JSP
            request.setAttribute("listaEvento", listaEventos);
            
            // Redirigir al JSP que mostrará las bodas
            RequestDispatcher dispatcher = request.getRequestDispatcher("RevisarEventos.jsp");
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Manejar errores de conexión y SQL
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al acceder a la base de datos");
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
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
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    String usuario = "root";  
    String contraseña = "3313130580";  
    String url = "jdbc:mariadb://localhost:3306/la_casa_del_relojero";  

    // Inicializar las variables de conexión.
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Evento> listaEventos = new ArrayList<>(); // Lista para almacenar los eventos

        try {
            // Cargar el driver de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            // Establecer conexión con la base de datos
            con = DriverManager.getConnection(url, usuario, contraseña);

            // Consulta para seleccionar las bodas
            String sql = "SELECT id_evento, nom_evento, informacion, tipo_evento, cant_invitados FROM evento WHERE tipo_evento = 'Boda' ORDER BY nom_evento ASC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Procesar el resultado de la consulta
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setIdEvento(rs.getInt("id_evento"));
                evento.setNomEvento(rs.getString("nom_evento"));
                evento.setInformacion(rs.getString("informacion"));
                evento.setTipoEvento(rs.getString("tipo_evento"));
                evento.setCantInv(rs.getInt("cant_invitados"));
                listaEventos.add(evento); // Agregar cada evento a la lista
            }

            // Pasar la lista de eventos al JSP
            request.setAttribute("listaEvento", listaEventos);
            
            // Redirigir al JSP que mostrará las bodas
            RequestDispatcher dispatcher = request.getRequestDispatcher("/RevisarEventos.jsp");
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Manejar errores de conexión y SQL
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al acceder a la base de datos");
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public class Evento {
        private int idEvento;
        private String nomEvento;
        private String informacion;
        private String tipoEvento;
        private int cantInv;

        // Getters y Setters
        public int getIdEvento() {
            return idEvento;
        }

        public void setIdEvento(int idEvento) {
            this.idEvento = idEvento;
        }

        public String getNomEvento() {
            return nomEvento;
        }

        public void setNomEvento(String nomEvento) {
            this.nomEvento = nomEvento;
        }

        public String getInformacion() {
            return informacion;
        }

        public void setInformacion(String informacion) {
            this.informacion = informacion;
        }

        public String getTipoEvento() {
            return tipoEvento;
        }

        public void setTipoEvento(String tipoEvento) {
            this.tipoEvento = tipoEvento;
        }

        public int getCantInv() {
            return cantInv;
        }

        public void setCantInv(int cantInv) {
            this.cantInv = cantInv;
        }
    }
}