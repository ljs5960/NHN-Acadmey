//package com.nhnacademy.resident.utils;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//
//public class PasswordEncrypt {
//    /*
//    SHA-256 Encrypt Function
//    @param plainText 평문
//    @param salt 솔트값
//    @param iterationNumber 반복횟수
//     */
//    public static String sha256(String plainText, int iterationNumber) {
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//            String salt = generateSalt();
//
//            messageDigest.reset();
//
//            byte[] input = plainText.getBytes(StandardCharsets.UTF_8);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//    Generating Salt Value
//     */
//    public static String generateSalt() {
//        SecureRandom random = new SecureRandom();
//
//        byte[] salt = new byte[8];
//        random.nextBytes(salt);
//
//        // byte to hex
//        StringBuffer stringBuffer = new StringBuffer();
//
//        for (int i = 0; i < salt.length; i++) {
//            stringBuffer.append(String.format("%02x", salt[i]));
//        }
//
//        return stringBuffer.toString();
//    }
//}
