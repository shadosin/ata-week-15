package com.kenzie.passwordcracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to hash a batch of passwords in a separate thread.
 */
public class BatchPasswordHasher implements Runnable{

    private final List<String> passwords;
    private final Map<String, String> passwordToHashes;
    private final String salt;

    public BatchPasswordHasher(List<String> passwords, String salt) {
        this.passwords = passwords;
        this.salt = salt;
        passwordToHashes = new HashMap<>();
    }

    /**
     *  Hashes all of the passwords, and stores the hashes in the passwordToHashes Map.
     */
    public void hashPasswords() {
        try {
            for (String password : passwords) {
                final String hash = PasswordUtil.hash(password, salt);
                passwordToHashes.put(password, hash);
            }
            System.out.println(String.format("Completed hashing batch of %d passwords.", passwords.size()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns a map where the key is a plain text password and the key is the hashed version of the plaintext password
     * and the class' salt value.
     *
     * @return passwordToHashes - a map of passwords to their hash value.
     */
    public Map<String, String> getPasswordToHashes() {
        return passwordToHashes;
    }

    //TODO: Implement this method
    @Override
    public void run() {
        hashPasswords();
    }
}
