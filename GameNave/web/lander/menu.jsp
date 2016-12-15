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
        <title>Lunar Landing in HTML5</title>
        <link rel="stylesheet" href="css/style_1.css">
    </head>
    <body>
        <div id="inicio">
            <div class="container2">
                <h1>Lunar Landing</h1>
                <h2>Menu</h2>
                <a href="juego.jsp">Jugar</a>
                <form action="../mispartidas" method="post">                  
                    <input type="submit" value="Mis Partidas">
                </form>
                <form action="../top10" method="post">                  
                    <input type="submit" value="Top 10">
                </form>
                <a href="enlinea.jsp">En línea</a>
                <br>
            </div>
        </div>
                        </body>
                        </html>
