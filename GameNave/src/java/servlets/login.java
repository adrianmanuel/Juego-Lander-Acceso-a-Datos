package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeloBD.Vinusuarios;
import services.VinusuariosJpaController;

/**
 *
 * @author OPEN
 */
public class login extends HttpServlet {

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
            String juego ="lander/menu.jsp";
            String error ="lander/errorsesion.jsp";
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameNavePU");
            VinusuariosJpaController vjc = new VinusuariosJpaController(emf);
            List<Vinusuarios> ulist = vjc.findVinusuariosEntities();
            Vinusuarios vu = new Vinusuarios();
            String usuario = request.getParameter("usuario").toLowerCase();
            String pass = request.getParameter("pass");
            int idUsuario = 0;
            boolean comprobarUsuario = false, 
                    comprobarPass = false;
            /**
             * Comprobamos que el usuario y la pass recogidas en el formulario,
             * estan dentro de la base de datos
             */
            if (ulist != null && !ulist.isEmpty()) {
                for (Vinusuarios vinusuarios : ulist) {
                    if (usuario.contentEquals(vinusuarios.getNombre()) && pass.contentEquals(vinusuarios.getContraseña())) {
                        comprobarUsuario = true;
                        idUsuario = vinusuarios.getIdUsuario();
                        comprobarPass = true;
                    }
                }
                /**
                 * Si el resultado de la comprobacion del usuario y la pass ha sido correcta.
                 * Genera dos cookies que caducan en 2 horas y envia al menu del juego
                 * si la comprobacion del usuario y la pass no es correcta, envia error
                 */
                if (comprobarUsuario == true && comprobarPass == true) {
                    Cookie galletaUser = new Cookie("usuario", usuario);
                    Cookie galletaPass = new Cookie("pass", pass);
                    galletaUser.setMaxAge(60 * 120);
                    galletaPass.setMaxAge(60 * 120);
                    galletaUser.setPath("/");
                    galletaPass.setPath("/");
                    response.addCookie(galletaUser);
                    response.addCookie(galletaPass);
                    //Pagina principal de usuario.
                    response.sendRedirect(juego);
                } else {
                    response.sendRedirect(error);
                }
            }else{
               response.sendRedirect(error);
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
