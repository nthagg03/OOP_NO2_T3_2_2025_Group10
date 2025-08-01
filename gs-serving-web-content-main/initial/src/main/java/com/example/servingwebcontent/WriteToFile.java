package com.example.servingwebcontent;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class để ghi dữ liệu ra file
 */
public class WriteToFile {
    
    private static final String LOG_FILE = "application.log";
    
    /**
     * Ghi thông tin user vào file log
     */
    public static void writeUserInfo(String username, String action) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String logEntry = String.format("[%s] User: %s, Action: %s%n", timestamp, username, action);
            writer.write(logEntry);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /**
     * Ghi log hệ thống
     */
    public static void writeSystemLog(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String logEntry = String.format("[%s] SYSTEM: %s%n", timestamp, message);
            writer.write(logEntry);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing system log: " + e.getMessage());
        }
    }
    
    /**
     * Ghi dữ liệu user vào file CSV
     */
    public static void writeUserToCSV(String username, String email, String phone) {
        try (FileWriter writer = new FileWriter("users.csv", true)) {
            String csvLine = String.format("%s,%s,%s,%s%n", 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                username, email, phone);
            writer.write(csvLine);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
    
    /**
     * Tạo file CSV header nếu chưa tồn tại
     */
    public static void initializeCSVFile() {
        try (FileWriter writer = new FileWriter("users.csv")) {
            writer.write("timestamp,username,email,phone\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error initializing CSV file: " + e.getMessage());
        }
    }
}
