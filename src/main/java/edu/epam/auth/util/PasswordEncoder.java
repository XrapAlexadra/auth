package edu.epam.auth.util;

import edu.epam.auth.exception.EncodeServiceException;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordEncoder {

    private static final Logger logger = LogManager.getLogger(PasswordEncoder.class);

    private static final int SALT_LENGTH = 32;
    private static final int ITERATIONS = 65536;
    private static final int ENCODE_KEY_LENGTH = 128;

    private static final PasswordEncoder instance = new PasswordEncoder();

    private PasswordEncoder() {
    }

    public static PasswordEncoder getInstance() {
        return instance;
    }

    public String encodePassword(String password) throws EncodeServiceException {
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(SALT_LENGTH);
        String encodePassword = null;
        try {
            encodePassword = Base64.encodeBase64String(salt) + "$" + hash(password, salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error(e);
            throw new EncodeServiceException(e);
        }
        return encodePassword;
    }

    public boolean check(String password, String encodedPassword) throws EncodeServiceException {
        String[] saltAndHash = encodedPassword.split("\\$");
        if (saltAndHash.length != 2) {
            throw new EncodeServiceException("The stored password must have the form 'salt$hash'");
        }
        String hashOfInput;
        try {
            hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error(e);
            throw new EncodeServiceException(e);
        }
        return hashOfInput.equals(saltAndHash[1]);
    }

    private String hash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, ITERATIONS, ENCODE_KEY_LENGTH));
        return Base64.encodeBase64String(key.getEncoded());
    }
    
}
