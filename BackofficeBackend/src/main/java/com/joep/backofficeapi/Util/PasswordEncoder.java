package com.joep.backofficeapi.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordEncoder {

    public PasswordEncoder() {
    }

    public String Encode(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest hasher = MessageDigest.getInstance("SHA1");
        hasher.update(pass.getBytes("UTF-8"));
        byte[] hash = hasher.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< hash.length ;i++)
        {
            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
