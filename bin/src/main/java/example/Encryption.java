package example;

import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

class Encryption {

    private final String password = "abcd1234";

    DESKeySpec key = null;
    SecretKeyFactory keyFactory = null;
    SecretKey secretKey = null;

    public Encryption() {
      try {

        key = new DESKeySpec(password.getBytes());
        keyFactory = SecretKeyFactory.getInstance("DES");
        secretKey = keyFactory.generateSecret(key);

      } catch(Exception e) {

      }
    }

    private Cipher setupCipher(int optMode) throws Exception {
      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(optMode, secretKey);
      return cipher;
    }

    public String encryptPasswordBased(String plainText) throws Exception
    {
        Cipher cipher = setupCipher(Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    public String decryptPasswordBased(String cipherText) throws Exception{
      Cipher cipher = setupCipher(Cipher.DECRYPT_MODE);
      return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
    }

}
