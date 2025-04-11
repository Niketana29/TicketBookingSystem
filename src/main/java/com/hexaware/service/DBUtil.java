package com.hexaware.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/TicketBookingSystem";
    private static final String USER = "root"; // 🔁 replace with your username
    private static final String PASSWORD = "Niki@MySQL25"; // 🔁 replace with your MySQL password

    public static Connection getDBConn() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

