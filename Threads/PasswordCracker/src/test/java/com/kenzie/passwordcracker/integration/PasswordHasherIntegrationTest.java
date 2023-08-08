package com.kenzie.passwordcracker.integration;

import com.kenzie.passwordcracker.PasswordHasher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordHasherIntegrationTest {

    List<String> passwords;

    @Test
    void generateAllHashes_withPasswords_returnsHashWithAllPasswords() throws InterruptedException {
        //GIVEN
        passwords = Arrays.asList("password", "password123", "anotherpw", "securepassword");

        // WHEN
        Map<String, String> passwordHashMap = PasswordHasher.generateAllHashes(passwords);

        // THEN
        assertTrue(passwordHashMap.keySet().containsAll(passwords), "passwordHashMap does not include all passwords passed into generateAllHashes");
    }

}
