package com.xworkz.issuemanagement.util;

import org.apache.commons.lang3.RandomStringUtils;
import java.security.SecureRandom;

public class EmailPasswordGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+[]{}|;:,.<>?";
    private static final int PASSWORD_LENGTH = 10; // Adjust the length as needed
    private static final SecureRandom RANDOM = new SecureRandom();

    public EmailPasswordGenerator() {
        System.out.println("Created PasswordGenerator");
    }

    public static String generatePassword() {
        // Ensure at least one special character
        String specialCharacter = RandomStringUtils.random(1, SPECIAL_CHARACTERS);

        // Generate remaining characters from CHARACTERS
        String remainingCharacters = RandomStringUtils.random(PASSWORD_LENGTH - 1, CHARACTERS);

        // Combine both and shuffle to ensure the special character is not always at the beginning
        String combined = specialCharacter + remainingCharacters;
        return shuffleString(combined);
    }

    private static String shuffleString(String string) {
        char[] characters = string.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = RANDOM.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        System.out.println("Generated Password: " + generatePassword());
    }
}
