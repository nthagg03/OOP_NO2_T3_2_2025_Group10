package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myConnection {
    
    // Database configuration
    private static final String H2_URL = "jdbc:h2:mem:testdb";
    private static final String H2_USERNAME = "sa";
    private static final String H2_PASSWORD = "";
    
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/user_management";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "password";
    
    private static Connection connection;
    
    // Get H2 connection (default for development)
    public static Connection getH2Connection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(H2_URL, H2_USERNAME, H2_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("H2 Driver not found", e);
        }
    }
    
    // Get MySQL connection (for production)
    public static Connection getMySQLConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }
    
    // Get default connection (H2)
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = getH2Connection();
        }
        return connection;
    }
    
    // Close connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // Test connection
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
    
    // Initialize database schema
    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    ma_khach_hang VARCHAR(20) UNIQUE NOT NULL,
                    ten_khach_hang VARCHAR(100) NOT NULL,
                    so_dien_thoai VARCHAR(20),
                    email VARCHAR(255),
                    dia_chi TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            """;
            
            conn.createStatement().execute(createTableSQL);
            System.out.println("Database initialized successfully");
            
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }
}
