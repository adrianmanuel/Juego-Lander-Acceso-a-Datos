package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeloBD.Vinpartidas;
import modeloBD.Vinusuarios;
import services.VinpartidasJpaController;
import services.VinusuariosJpaController;

/**
 *
 * @author OPEN
 */
public class mispartidas extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameNavePU");
        EntityManager em = emf.createEntityManager();
        VinusuariosJpaController vjc = new VinusuariosJpaController(emf);
        VinpartidasJpaController vpjc = new VinpartidasJpaController(emf);
        List<Vinusuarios> ulist = vjc.findVinusuariosEntities();
        String galleta;
        int idUsuario = 0;

         /**
         * Saco el id de usuario a traves de las cookies y el nombre
         * para poder mostrarlo en la web
         */
        String nombre="";
        Cookie galletas[] = request.getCookies();
        for (Cookie cookie : galletas) {
            galleta = cookie.getValue();
            for (Vinusuarios vinusuarios :ulist) {
                if (galleta.contentEquals(vinusuarios.getNombre())) {
                   idUsuario = vinusuarios.getIdUsuario();
                   nombre = vinusuarios.getNombre();
                }

            }
        }
        
        /**
         * Creo una Query JPQL para poder manejar los datos de las partidas
         */
        Query query = em.createQuery("SELECT p FROM Vinpartidas p, Vinusuarios u WHERE u.idUsuario = :idUsuario and p.idUsuario = u ORDER by p.velocidad DESC ");
        query.setParameter("idUsuario", idUsuario);
        List<Vinpartidas> plist = (List<Vinpartidas>) query.getResultList();

       
        PrintWriter pantalla = response.getWriter();
        pantalla.println("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "    <head>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\">\n"
                + "        <title>Lunar Landing - Registro</title>\n"
                + "        <link rel=\"stylesheet\" href=\"lander/css/style4.css\">\n"
                + "    </head>\n"
                + "    <body>     \n"
                + "        <div id=\"registro\">\n"
                + "            <div class=\"containerregistro\">\n"
                + "                <h1>Lunar Landing</h1>\n"
                + "                <h2>Mis Partidas</h2>\n"
                + "                 <div id=\"tabla\">\n"
                + "                <table align=\"center\">\n"
                + "                    \n"
                + "                        <tr><th><strong>Velocidad</strong></th>"
                + "<th><strong>Inicio Partida</strong></th>\n"
                + "                        <th><strong>Fin Partida</strong></th>\n"
                + "                    </tr>");
        
        pantalla.println("<h1>"+nombre+"</h1>");
        pantalla.println("<form action=\"lander/juego.jsp\" method=\"post\">                  \n" +
"                    <input type=\"submit\" value=\"Jugar\">\n" +
"                </form>\n" +
"                    <form action=\"top10\" method=\"post\">                  \n" +
"                    <input type=\"submit\" value=\"Top 10\">\n" +
"                </form>\n" +
"                <form action=\"enlinea\" method=\"post\">                  \n" +
"                    <input type=\"submit\" value=\"En Linea\">\n" +
"                </form>"
                + "<form action=\"salir\" method=\"post\">                  \n" +
"                    <input type=\"submit\" value=\"Salir\">\n" +
"                </form>");
        
        /**
         * Hago un recorrido en la lista de partidas, para sacarlas por la web
         */
        for (Vinpartidas e : plist) {
            pantalla.println(" <tr><td>" + " " + e.getVelocidad() + "</td>" + " "
                    + "<td>" + " " + e.getInicio() + "</td>"
                    + " <td>" + " " + e.getFin() + "</td></tr>");

        }
        pantalla.println("</table>\n"
                + "            </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>");
        emf.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
