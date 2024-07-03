package Managers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasherManager {


    public String hashPassword(String password){
        try {
            MessageDigest mg = MessageDigest.getInstance("SHA-224");
            byte[] pswd = mg.digest(password.getBytes("UTF-8"));
            BigInteger bg = new BigInteger(1, pswd);

            return bg.toString(16);



        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
