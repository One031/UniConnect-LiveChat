package com.mycompany.uniconnect.livechat.servlets;

import com.mycompany.uniconnect.livechat.dao.UserDAO;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve username and password from login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Create UserDAO instance to interact with the database
        UserDAO userDAO = new UserDAO();
        // Validate login credentials (returns username if correct, null if invalid)
        String user = userDAO.login(username.trim(), password.trim());
        
        if (user != null){
            // Save user in session
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // store in session
            
            // Redirect to the HomeServlet
            response.sendRedirect("HomeServlet");
        } else{
            // Login falied
            request.setAttribute("errorMessage", "Invalid email or password.");
            // Redirect back to login page
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

}
