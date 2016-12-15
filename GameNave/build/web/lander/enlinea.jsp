<%-- 
 Document   : index
 Created on : 20-nov-2016, 16:08:08
 Author     : OPEN
--%>

<%@page import="java.util.List"%>
<%@page import="modeloBD.Vinusuarios"%>
<%@page import="modeloBD.Vinusuarios"%>
<%@page import="services.VinusuariosJpaController"%>
<%@page import="services.VinusuariosJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
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
                <h2>En Línea</h2>
                <table align="center">
                    <tr>
                        <td><strong>Usuario</strong></td>
                        <td><strong>Inicio Partida</strong></td>
                        <td><strong>Fin Partida</strong></td>
                    </tr>
                    <%
                        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameNavePU");
            VinusuariosJpaController vjc = new VinusuariosJpaController(emf);
            List<Vinusuarios> ulist = vjc.findVinusuariosEntities();
            Vinusuarios vu = new Vinusuarios();
            String galleta;
            int idUsuario = 0;
            boolean comprobarUsuario = false, 
                    comprobarPass = false;
            
            Cookie galletas[] = request.getCookies();
            for (Cookie cookie : galletas) {
            galleta = cookie.getValue();
            }
            vu.getVinpartidasList();
                        %>
                   
                </table>
            </div>
        </div>
    </body>
</html>

