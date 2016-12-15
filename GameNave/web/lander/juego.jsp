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
        <link rel="stylesheet" href="css/juego.css">
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/lunar.js"></script>
    </head>
    <body>
        <div id="state">
            <div class="container">
                <h1></h1>
                <h2></h2>
                    <a href="juego.jsp">Jugar</a>
                    <form action="../mispartidas" method="post">                  
                    <input type="submit" value="Mis Partidas">
                </form>
                <form action="../top10" method="post">                  
                    <input type="submit" value="Top 10">
                </form>
                    <a href="enlinea.jsp">En línea</a>
            </div>
        </div>
        <div id="game">
            <div id="gauge"><div></div></div>
            <div id="ship"></div>
            <div id="explode"></div>
            <div id="moon">
                <div id="landing-pad"><div id="ms">-</div></div>
            </div>
        </div>
    </body>
</html>