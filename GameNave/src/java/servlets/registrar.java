package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modeloBD.Vinusuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.VinusuariosJpaController;


/**
 *
 * @author OPEN
 */
public class registrar extends HttpServlet {


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
            String inicio ="lander/iniciosesion.jsp";
            String error ="lander/errorregistro.jsp";
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameNavePU");
            boolean userExists = false;
            VinusuariosJpaController vjc = new VinusuariosJpaController(emf);
            List<Vinusuarios> ulist = vjc.findVinusuariosEntities();
            Vinusuarios vu = new Vinusuarios();
            /**
             * Recojo los datos del formulario del JSP registro
             */
            String usuario = request.getParameter("usuario").toLowerCase();
            String pass = request.getParameter("pass");
            String email = request.getParameter("mail");
            String tel = request.getParameter("tel");
            int telefono = Integer.parseInt(tel);
            
            //Comprobación de que el usuario no se repite
            if (ulist != null && !ulist.isEmpty()) {
                for (Vinusuarios vinusuarios : ulist) {
                    if (usuario.contentEquals(vinusuarios.getNombre())) {
                        userExists = true;
                    }
                }
            }
            /**
             * En el caso de que el usuario se repita, cargará el JSP errorregistro
             * sino directamente cargará el menu del juego
             */
            if (userExists == false) {
                vu.setNombre(usuario);
                vu.setContraseña(pass);
                vu.setMail(email);
                vu.setTelefono(telefono);
                vjc.create(vu);
                response.sendRedirect(inicio);
            } else {
                if (userExists == true) {
                    response.sendRedirect(error);                   
                }
            }
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
