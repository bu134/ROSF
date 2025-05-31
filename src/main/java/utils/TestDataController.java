package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TestDataController {

    private final String csvFilePath;

    public TestDataController(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    /**
     * Fetch login credentials for a given user type
     * @param userType the key like "admin" or "qa"
     * @return HashMap with keys "username" and "password"
     */
    public HashMap<String, String> getCredentialsByUserType(String userType) {
        HashMap<String, String> credentials = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // skip header
                }

                String[] data = line.split(",");

                if (data.length >= 3 && data[0].equalsIgnoreCase(userType)) {
                    credentials.put("username", data[1]);
                    credentials.put("password", data[2]);
                    return credentials;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }

        return null;
    }
}
