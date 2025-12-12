package com.mycompany.uniconnect.livechat.dao;

import com.mycompany.uniconnect.livechat.model.User;
import com.mycompany.uniconnect.livechat.util.DBConnection;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author oneaz
 */
public class UserDAO {
    public boolean register(User user){
        // SQL statement to insert a new user into the users table
        String sql = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            // Hash password
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            
            // Set values for the placeholders in the SQL query
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, hashedPassword);
            
            // Execute the insert operation and check how many rows were affected
            int rows = stmt.executeUpdate();
            return rows > 0; // Return true if at least one row was inserted
        }catch (SQLException e){
            e.printStackTrace();
            return false; // Return false if insertion was false
        }
    }
    
    public String login(String username, String password) {
        // SQL statement to select a user with the given username and password
        String sql = "SELECT username, password FROM users WHERE username=?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            // Set values for the SQL placeholders
            stmt.setString(1, username);
            
            // Execute the query 
            ResultSet rs = stmt.executeQuery();
            
            // If a matching user is found, return the username
            if(rs.next()){
               String storedHash = rs.getString("password");
               
               // Comparing raw password with hashed password
               if (BCrypt.checkpw(password, storedHash)) {
                   return rs.getString("username");
               }
            }else {
                return null;
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
