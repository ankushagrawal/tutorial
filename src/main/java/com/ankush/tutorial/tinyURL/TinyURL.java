package com.ankush.tutorial.tinyURL;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class TinyURL {

    private AtomicLong id = new AtomicLong();
    private Map<String,String> urlMap = new HashMap<>();
    private static final char[] corpus   = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private String createTinyURL(String inputURL) {
        if (urlMap.containsKey(inputURL)) return urlMap.get(inputURL);
        long dbID = id.incrementAndGet();
        String tinyURL = getBase62From10(dbID);

//        String hash = getHash(inputURL);

        urlMap.put(inputURL, tinyURL);
        return tinyURL;
    }

    private String getHash(String inputURL) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(inputURL.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Note if seed is unique then generated base62 number will be unique as well under load balance make sure this value is not same.
     */
    public static final String getBase62From10(final long seed) {
        String number = seed + "";
        char[] buf = new char[number.length()];
        int charPos = number.length() - 1;
        BigInteger bigIntegerNumber = new BigInteger(number);
        BigInteger radix = BigInteger.valueOf(62);

        while (bigIntegerNumber.compareTo(radix) >= 0) {
            buf[charPos--] = corpus[bigIntegerNumber.mod(radix).intValue()];
            bigIntegerNumber = bigIntegerNumber.divide(radix);
        }
        buf[charPos] = corpus[bigIntegerNumber.intValue()];
        return new String(buf, charPos, (number.length() - charPos));
    }


    /**
     * @param longNumber
     * a positive number in base 62
     * @return the same number, in base 10
     */
    public static final String getBase10From62(final long longNumber) {
        String number = longNumber + "";
        BigInteger value = BigInteger.ZERO;
        for (char c : number.toCharArray()) {
            value = value.multiply(BigInteger.valueOf(62));
            if ('0' <= c && c <= '9') {
                value = value.add(BigInteger.valueOf(c - '0'));
            }
            if ('a' <= c && c <= 'z') {
                value = value.add(BigInteger.valueOf(c - 'a' + 10));
            }
            if ('A' <= c && c <= 'Z') {
                value = value.add(BigInteger.valueOf(c - 'A' + 36));
            }
        }
        return value.toString();
    }

    private String getTinyURL(String inputURL) {
        return urlMap.get(inputURL);
    }

    public static void main(String[] args) {
        TinyURL tinyURL = new TinyURL();
        String url = tinyURL.createTinyURL("http://facebook.com");
        System.out.println(url);
        url = tinyURL.createTinyURL("http://facebook1.com");
        System.out.println(url);
        url = tinyURL.createTinyURL("http://facebook2.com");
        System.out.println(url);
    }
}
