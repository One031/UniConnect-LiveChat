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
@WebServlet(name = "ConversationsServlet", urlPatterns = {"/ConversationsServlet"})
public class ConversationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve the existing session, do not create a new one if it doesn't exist
        HttpSession session = request.getSession(false);
        
        // Check if session exists and user is logged in
        if(session != null && session.getAttribute("user") != null) {
            // Forward the request to conversations.jsp
            request.getRequestDispatcher("/WEB-INF/views/conversations.jsp").forward(request, response);
        } else {
            // If no valid session, redirect user to the login page
            response.sendRedirect("login.jsp");
        }
    }
}
