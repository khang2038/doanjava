package com.company.khang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
    private static final String dbURL="jdbc:sqlserver://localhost;databaseName=quancafe;user=sa;password=01658205896";
    public static Connection getconection() throws SQLException{
        return  DriverManager.getConnection(dbURL);
    }
}
