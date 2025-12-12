package com.mycompany.uniconnect.livechat.servlets;

import com.mycompany.uniconnect.livechat.dao.UserDAO;
import com.mycompany.uniconnect.livechat.model.User;
import com.mycompany.uniconnect.livechat.util.PasswordValidator;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author oneaz
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        // Get form data
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();
        
        // Validate password complexity
        if (!PasswordValidator.isValid(password)) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters and include upper/lowercase letters, a number and a special character.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return; 
        }
        
        // User object
        User user = new User(username, email, password);
        
        // Call DAO
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.register(user);
        
        if (success){
            response.sendRedirect("login.jsp"); // Redirect to login.html
        }else{
            request.setAttribute("errorMessage", "Registration failed. Try again.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }

}
