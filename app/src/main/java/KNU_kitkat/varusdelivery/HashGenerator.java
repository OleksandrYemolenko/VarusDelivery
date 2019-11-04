package KNU_kitkat.varusdelivery;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    public static final String getHash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] byteHash = md.digest(text.getBytes(StandardCharsets.UTF_8));

            String answer = new String();
            for(byte b : byteHash) {
                answer += String.format("%02x", b);
            }

            return answer;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return Integer.toString((int)(Math.random() * 1000000000));
    }
}