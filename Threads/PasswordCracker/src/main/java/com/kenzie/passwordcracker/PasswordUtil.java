package com.kenzie.passwordcracker;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * A helper class for hashing a password and checking if it's valid.
 */
public class PasswordUtil {

    // 20,000 times
    private static final int ITERATIONS = 20 * 1000;
    private static final int KEY_LENGTH = 256;
    private static final String HACKED_DATABASE_FILE = "Threads/PasswordCracker/src/main/resources/hackedDatabases/hackedDatabase100x10x190.csv";
    private static final String COMMON_PASSWORD_FILE = "Threads/PasswordCracker/src/main/resources/commonPasswords/commonPasswords100.csv";

    /**
     * Checks whether given plaintext password corresponds to a stored salted hash of the password.
     * @param password password
     * @param stored stored salted hash
     * @return true if the password is the same as what was stored.
     * @throws Exception if hashing fails
     */
    public static boolean check(String password, String stored) throws Exception {
        final String[] saltAndPassword = stored.split("\\$");
        if (saltAndPassword.length != 2) {
            throw new IllegalStateException("The stored password have the form 'salt$hash'");
        }
        final String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPassword[0]));
        return hashOfInput.equals(saltAndPassword[1]);
    }

    /**
     * Computes a salted PBKDF2 hash of given plaintext password.
     * @param password plaintext password
     * @param salt salt
     * @return the salted PBKDF2 hash
     * @throws Exception if hashing or encoding fails
     */
    public static String hash(String password, String salt) throws Exception {
        final byte[] saltBytes = salt.getBytes();
        return Base64.getEncoder().encodeToString(saltBytes) + "$" + hash(password, saltBytes);
    }

    private static String hash(String password, byte[] salt) throws Exception {
        final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        final SecretKey key = secretKeyFactory.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, ITERATIONS, KEY_LENGTH));
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }


    /**
     * Read pairs of hashed password and user ids using that password.
     *
     * @return a multimap of hashed password and user ids suing that password.
     *          Key is the hashed password and value is all user ids suing that password
     */
    static Multimap<String, String> readHackedDatabase() {
        final Multimap<String, String> hackedHashToUserIds = ArrayListMultimap.create();

        try (Reader reader = Files.newBufferedReader(getPath(HACKED_DATABASE_FILE));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                final String userId = csvRecord.get(0);
                final String hash = csvRecord.get(1);
                hackedHashToUserIds.put(hash, userId);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return hackedHashToUserIds;
    }

    /**
     * Reads raw passwords from a file.
     * @return common passwords
     */
    static List<String> readCommonPasswords() {
        final List<String> passwords = Lists.newArrayList();

        try (Reader reader = Files.newBufferedReader(getPath(COMMON_PASSWORD_FILE));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                passwords.add(csvRecord.get(0));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return passwords;
    }

    /**
     * Get resource's path.
     */
    private static Path getPath(String fileName) {
        try {
            return Paths.get(fileName);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Could not access password file in resources! Please check with an instructor.");
        }
    }
}
