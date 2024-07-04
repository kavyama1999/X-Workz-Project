package com.xworkz.issuemanagement.util;

import org.apache.commons.lang3.RandomStringUtils;
import java.security.SecureRandom;

public class EmailPasswordGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10; // Adjust the length as needed
    private static final SecureRandom RANDOM = new SecureRandom();

    public EmailPasswordGenerator() {
        System.out.println("Created PasswordGenerator");
    }

    public static String generatePassword() {
        // Generate a password that includes letters and numbers
        return RandomStringUtils.random(PASSWORD_LENGTH, CHARACTERS);
    }

    public static void main(String[] args) {
        System.out.println("Generated Password: " + generatePassword());
    }
}
