package com.yuimorii.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @program: JDBC2023
 * @description: Enter your account password to simulate user login!
 * @author: yuimorii
 * @create: 2022-12-28 23:00
 **/


// TODOLIST
// 1. clarify the use of jdbc flow and detailed analysis of the internal design api method
// 2. find the problem, lead to the preparedStatement method

public class jdbc02_JdbcStatementLoginPart {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String account = scanner.nextLine();
        String password = scanner.nextLine();
        scanner.close();

//        1.  Load and register the driver class
        DriverManager.registerDriver(new Driver());



//        2.  Create connection with Database
       Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/yuimoriiJDBC","root", "yuimorii");
//        3.  Create platform to execute SQL queries

//        4.  Execute SQL queries
//        5.  Close resources


    }
}

