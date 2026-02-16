<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Invitados</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            background-image: url('foto/Principal.webp');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            border-radius: 12px;
            padding: 40px;
            width: 90%;
            max-width: 1200px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            text-align: center;
            transition: all 0.3s ease-in-out;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #fff;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: rgba(255, 255, 255, 0.8);
            color: #000;
        }

        td {
            background-color: rgba(255, 255, 255, 0.3);
        }

        .bottom-bar {
            background-color: rgba(255, 255, 255, 0.1);
            width: 100%;
            height: 40px;
            display: flex;
            justify-content: space-around;
            align-items: center;
            position: fixed;
            bottom: 0;
            left: 0;
            color: white;
        }

        .bottom-bar a {
            color: #ffffff;
            font-size: 14px;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .bottom-bar a:hover {
            color: #e1e1e1;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Búsqueda de Invitados</h2>
        
        <!-- Formulario de búsqueda en tiempo real -->
        <form>
            <input type="text" id="searchInput" placeholder="Buscar por nombre..." onkeyup="buscarInvitado()" />
        </form>

        <!-- Tabla donde se mostrarán los resultados -->
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Mesa</th>
                    <th>Parentesco</th>
                </tr>
            </thead>
            <tbody id="resultados">
                <!-- Iteración sobre la lista de invitados usando JSTL -->
                <c:forEach var="invitado" items="${listaInvitado}">
                    <tr>
                        <td>${invitado.nombre}</td>
                        <td>${invitado.numeroMesa}</td>
                        <td>${invitado.parentesco}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="bottom-bar">
        <a href="PaginaPrincipal.jsp">Cerrar Sesión</a>
        <%
            Integer permiso = (Integer) session.getAttribute("permiso");
            if (permiso != null) {
                if (permiso == 1) {
        %>
                    <a href="Admin.jsp">Regresar al menú de Administrador</a>
        <%
                } else if (permiso == 2) {
        %>
                    <a href="Novios.jsp">Regresar al menú de Usuario</a>
        <%
                }
            }
        %>
    </div>

    <!-- Script para la búsqueda en tiempo real -->
    <script>
        function buscarInvitado() {
            const nombre = document.getElementById('searchInput').value;

            // Crear la petición AJAX
            const xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    // Actualizar la tabla con los resultados de la búsqueda
                    document.getElementById('resultados').innerHTML = this.responseText;
                }
            };

            // Enviar la petición al servidor con el nombre a buscar
            xhttp.open("GET", "buscarInvitado?nom_in=" + nombre, true);
            xhttp.send();
        }
    </script>
</body>
</html>

