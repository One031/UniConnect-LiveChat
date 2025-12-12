package com.mycompany.uniconnect.livechat.util;

/**
 *
 * @author oneaz
 */
public class PasswordValidator {
    
    public static boolean isValid(String password) {
        if (password == null) return false;
        
        // Minimum length
        if (password.length() < 8) return false;
        
        // At least one uppercase
        if (!password.matches(".*[A-Z].*")) return false;
        
        // At least one lowercase
        if (!password.matches(".*[a-z].*")) return false;
        
        // At least one digit
        if (!password.matches(".*[0-9].*")) return false;
        
        // At least one special symbol
        if (!password.matches(".*[!@#$%^&*(),.?\\\":{}|<>].*")) return false;
        
        return true;
    }
}
