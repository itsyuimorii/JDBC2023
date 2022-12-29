package com.yuimorii.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * @program: JDBC2023
 * @description: Using statement to 查詢t——user 表下， 全部的數據
 * @author: yuimorii
 * @create: 2022-12-28 22:27
 **/
public class jdbc01_connection {
    public static void main(String[] args) throws SQLException {
//        1.  Load and register the driver class
        //static method, call by class name.
        DriverManager.registerDriver(new Driver());

//        2.  Create connection with Database
       //connection 使用java.sql.Connection接口接收(polymorphic)
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yuimoriiJDBC","root","yuimorii");
//        3.  Create platform to execute SQL queries
        Statement statement = connection.createStatement();
//        4.  Execute SQL queries
        String sql = "select*from t_user";
        ResultSet resultSet = statement.executeQuery(sql);
//        5.  Close costlv resources
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+"::"+account+"::"+password+"::"+nickname);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}

