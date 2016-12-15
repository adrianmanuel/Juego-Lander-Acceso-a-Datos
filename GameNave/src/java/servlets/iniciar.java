package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(urlPatterns = {"/iniciar"})
public class iniciar extends HttpServlet {

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
        try {
            String juego="lander/juego.jsp";
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameNavePU");
            VinusuariosJpaController vjc = new VinusuariosJpaController(emf);
            VinpartidasJpaController vpjc  = new VinpartidasJpaController(emf);
            List<Vinusuarios> ulist = vjc.findVinusuariosEntities();
            Vinusuarios vu = new Vinusuarios();
            Vinpartidas vp = new Vinpartidas();
            int idUsuario=0;
            /**
             *Saco el id de usuario a traves de las cookies
             */
            Cookie galletas[] = request.getCookies();
            for (Cookie cookie : galletas) {
                String galleta = cookie.getValue();
                for (Vinusuarios vinusuarios : ulist) {
                    if (galleta.contentEquals(vinusuarios.getNombre())) {
                        idUsuario = vinusuarios.getIdUsuario();
                    }
                }
            }
            /**
             * Recojo los datos necesarios para la creacion del objeto partida
             */
            
            String fechaI = request.getParameter("fechaInicio");
            String fechaF = request.getParameter("fechaFinal");
            DateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
            Date fechaInicio = sdf.parse(fechaI);
            Date fechaFinal = sdf.parse(fechaF);
            Float velocidad = Float.parseFloat(request.getParameter("velocidad"));
            System.out.println(velocidad);
            
            /**
             * Relleno las variables de la tabla partida
             */
            vu.setIdUsuario(idUsuario);
            vp.setIdUsuario(vu);
            vp.setInicio(fechaInicio);
            vp.setFin(fechaFinal);
            vp.setVelocidad(velocidad);
            vpjc.create(vp);            
            emf.close();  
        } catch (ParseException ex) {
            Logger.getLogger(iniciar.class.getName()).log(Level.SEVERE, null, ex);
        }
		
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
