package com.mycompany.uniconnect.livechat.servlets;

import java.io.IOException;
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
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve the current HTTP session (creates one if not already present)
        HttpSession session = request.getSession(false);
        
        
        if(session != null && session.getAttribute("user") == null){
            response.sendRedirect("login.jsp"); // not logged in
            return;
        } else {
            String username = (String) session.getAttribute("user");
            
            request.setAttribute("username", username);
            // Forward internally to home.jsp
            request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
        }
        
    }

}
