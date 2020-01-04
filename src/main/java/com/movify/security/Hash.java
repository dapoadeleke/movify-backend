package com.movify.security;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {

    private static int workload = 12;

    public static String hashPassword(String passwordPlainText){
        String salt = BCrypt.gensalt(Hash.workload);
        String hashedPassword = BCrypt.hashpw(passwordPlainText, salt);
        return hashedPassword;
    }

    public static boolean checkPassword(String passwordPlainText, String hashedPassword){
        return BCrypt.checkpw(passwordPlainText, hashedPassword);
    }
    
    

}
