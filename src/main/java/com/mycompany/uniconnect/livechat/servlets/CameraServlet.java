package com.mycompany.uniconnect.livechat.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author oneaz
 */
@WebServlet(name = "CameraServlet", urlPatterns = {"/CameraServlet"})
public class CameraServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieves the existing session and does not create a new one if it doesn't exist
        HttpSession session = request.getSession(false);
        
        // Checks if the ssession exists and contains a "user" attribbute
        if(session != null && session.getAttribute("user") != null) {
            // Forwards request to the secured camera.jsp page inside WEB-INF
            request.getRequestDispatcher("/WEB-INF/views/camera.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // redirect to login.jsp
        }
    }

   
}
