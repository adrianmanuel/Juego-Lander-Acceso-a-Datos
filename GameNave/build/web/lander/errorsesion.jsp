<%-- 
    Document   : index
    Created on : 20-nov-2016, 16:08:08
    Author     : OPEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
        <title>Lunar Landing - Inicio Sesión</title>
        <link rel="stylesheet" href="css/style2.css">
    </head>
    <body>     
        <div id="iniciosesion">
            <div class="containersesion">
                <h1>Lunar Landing</h1>
                <h2>Error al iniciar sesión</h2>
                <form action="../login" method="post">
                    <table align="center">
                        <tr>
                            <td> <p>Usuario:</p></td>
                            <td><input type="text" name="usuario"></td>
                        </tr>
                        <tr>
                            <td>Contraseña:</td>
                            <td><input type="password" name="pass"></td>
                        </tr>
                    </table>
                    <input type="submit" value="Entrar">
                </form>
            </div>
        </div>
                        </body>
                        </html>
