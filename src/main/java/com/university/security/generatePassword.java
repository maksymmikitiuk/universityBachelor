package com.university.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class generatePassword {
    public generatePassword() {
    }

    public String generatedSecuredPasswordHash(String originalPassword, String username){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(username.getBytes());
            byte[] bytes = md.digest(originalPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    public boolean comparePassword(String iPassword, String dbPassword){
        return iPassword.equals(dbPassword);
    }
}
