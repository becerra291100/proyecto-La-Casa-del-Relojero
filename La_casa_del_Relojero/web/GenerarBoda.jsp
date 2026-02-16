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
            border-radius: 6px;
            padding: 80px;
            width: 100%;
            max-width: 500px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            text-align: center;
            transition: all 0.3s ease-in-out;
        }
        
        .container:hover {
            transform: translateY(-5px);
        }

        table {
            width: 100%;
            margin-top: 6px;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: rgba(255, 255, 255, 0.8);
        }

        .login-form input[type="text"],
        .login-form input[type="number"],
        .login-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: none;
            border-radius:15px;
            background-color: rgba(255, 255, 255, 0.8);
            color: #333;
            font-size: 14px;
        }

        .login-form input[type="number"] {
            -moz-appearance: textfield; /* Elimina los controles de incremento/decremento en Firefox */
        }

        .login-form input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .login-form input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .button-link {
            background-color: rgba(255, 255, 255, 0.8);
            color: #ffffff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            text-decoration: none;
            display: inline-block;
            margin: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .button-link:hover {
            background-color: #0056b3;
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
        <h2>Generar Boda</h2>
        <form class="login-form" action="http://localhost:8080/La_casa_del_Relojero/Generar_Boda" method="POST">
            <table>
                <tr>
                    <th>Campo</th>
                    <th>Información</th>
                </tr>
                <tr>
                    <td>Nombre del Evento:</td>
                    <td><input type="text" name="nom_evento" required></td>
                </tr>
                <tr>
                    <td>Información:</td>
                    <td><input type="text" name="informacion" required></td>
                </tr>
                <tr>
                    <td>Tipo de Evento:</td>
                    <td><input type="text" name="tipo_evento" required></td>
                </tr>
                <tr>
                    <td>Cantidad de Invitados:</td>
                    <td><input type="number" name="cant_invitados" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Agregar Invitado">
                    </td>
                </tr>
            </table>
        </form>
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
</body>
</html>