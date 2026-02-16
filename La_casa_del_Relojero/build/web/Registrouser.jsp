<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Registro</title>
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
            width: 100%;
            max-width: 400px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            text-align: center;
            transition: all 0.3s ease-in-out;
        }

        .container:hover {
            transform: translateY(-5px);
        }

        h1.title {
            font-size: 32px;
            margin-bottom: 10px;
            color: #ffffff;
            font-weight: 300;
        }

        .login-box h3 {
            font-size: 18px;
            margin-bottom: 20px;
            font-weight: 400;
            color: #f2f2f2;
        }
        .login-form input[type="date"],
        .login-form select {
            width: 100%;
            padding: 15px;
            margin-bottom: 20px;
            border: none;
            border-radius: 8px;
            background-color: rgba(255, 255, 255, 0.8);
            color: #333;
            font-size: 14px;
        }

        .login-form input[type="date"]::placeholder,
        .login-form select {
            color: #333;
        }

        .login-form select {
            appearance: none; /* Remover el estilo por defecto del select */
            -webkit-appearance: none;
            -moz-appearance: none;
            background-image: url('data:image/svg+xml;base64,...'); /* Ícono de flecha para el select */
            background-repeat: no-repeat;
            background-position: right 15px center;
            background-size: 12px;
        }

        .login-form select:focus,
        .login-form input[type="date"]:focus {
            outline: none;
            border: 2px solid #4ca1af;
        }

        .login-form input[type="text"],
        .login-form input[type="password"] {
            width: 100%;
            padding: 15px;
            margin-bottom: 20px;
            border: none;
            border-radius: 8px;
            background-color: rgba(255, 255, 255, 0.8);
            color: #333;
            font-size: 14px;
        }

        .login-form input[type="submit"] {
            width: 100%;
            padding: 15px;
            background-color: #4ca1af;
            color: #fff;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .login-form input[type="submit"]:hover {
            background-color: #2c3e50;
        }

        .login-form a {
            color: #ffffff;
            text-decoration: underline;
        }

        .login-form a:hover {
            color: #e1e1e1;
        }

        p.copyright {
            margin-top: 20px;
            font-size: 12px;
            color: #ffffff;
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

        @media screen and (max-width: 768px) {
            .container {
                max-width: 90%;
                padding: 20px;
            }

            .login-form input[type="submit"],
            .login-form input[type="text"],
            .login-form input[type="password"] {
                padding: 12px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Registro de Usuario</h2>
    <form class="login-form" action="http://localhost:8080/La_casa_del_Relojero/Insertar" method="post">
        Nombre Completo:<br>
        <input type="text" id="nombre_completo" name="nombre_completo" required><br>

        Contraseña:<br>
        <input type="password" id="contrasena" name="contrasena" required><br>
        
        Fecha de boda:<br>
        <input type="date" id="Fecha_evento" name="Fecha_evento" required><br>
        
       <label for="sexo">Sexo:</label><br>
            <select id="sexo" name="sexo" required>
            <option value="Hombre">Hombre</option>
            <option value="Mujer">Mujer</option>
            <option value="Prefiero no decirlo">Prefiero no decirlo</option>
        </select><br><br>

        <input type="submit" value="Registrar">
    </form>
    <p class="copyright">Todos los derechos reservados &copy; 2024</p>
</div>
    <div class="bottom-bar">
        <a href="#">Acerca de nosotros</a>
        <a href="#">Contacto</a>
        <a href="AvisoPrivacidad.jsp">Aviso de privacidad</a>
    </div>
</body>
</html>
