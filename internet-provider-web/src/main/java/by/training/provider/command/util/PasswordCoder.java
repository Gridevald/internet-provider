package by.training.provider.command.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordCoder {

    /**
     * Codes given password to sha1.
     */
    public static String hashSha(String password) {
        return DigestUtils.sha1Hex(password);
    }
}
