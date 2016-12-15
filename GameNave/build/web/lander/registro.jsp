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
        <title>Lunar Landing - Registro</title>
        <link rel="stylesheet" href="css/style3.css">
    </head>
    <body>     
        <div id="registro">
            <div class="containerregistro">
                <h1>Lunar Landing</h1>
                <h2>Registro</h2>
                <form action="../registrar" method="post">
                    <table align="center">
                        <tr>
                            <td> <p>Usuario:</p></td>
                            <td><input type="text" name="usuario" required></td>
                        </tr>
                        <tr>
                            <td><p>Contraseña:</p></td>
                            <td><input type="password" name="pass" required></td>
                        </tr>
                        <tr>
                            <td><p>E-mail:</p></td>
                            <td><input type="email" name="mail" patter="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required></td>
                        </tr>
                        <tr>
                            <td><p>Teléfono:</p></td>
                            <td><input type="text" name="tel" pattern="[0-9]{9}" required></td>
                        </tr>
                    </table> 
                        <input type="submit" value="Crear Cuenta">
                        </form>
                        </div>
                        </div>
                        </body>
                        </html>
