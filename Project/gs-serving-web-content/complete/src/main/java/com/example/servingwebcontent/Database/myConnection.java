package com.example.servingwebcontent.Database;

import java.sql.DriverManager;
import java.sql.Connection;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class myConnection {
    @Value("${spring.datasource.url}")
    private String urlString;
    @Value("${spring.datasource.driver}")
    private String appDriver;
    @Value("${spring.datasource.user}")
    private String appUser;
    @Value("${spring.datasource.password}")
    private String appPassword;
    
    public Connection getConnection() {
        System.out.println("DB URL: " + urlString);
        System.out.println("DB Driver: " + appDriver);
        System.out.println("DB User: " + appUser);
        try {
            Class.forName(appDriver);
            return DriverManager.getConnection(urlString, appUser, appPassword);
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}