package com.yuimorii.jdbc;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
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


//STEP:
//输入账号和没密码
//进行数据库信息查询(t_user)
//反馈登录成功还是登录失败
public class jdbc02_JdbcStatementLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//      0. 获取用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your account: ");
        String account = scanner.nextLine();
        System.out.println("Please enter your password");
        String password = scanner.nextLine();
        scanner.close();

//        1.  Load and register the driver class
        //通过反射触发(在反射里: 有一个加载类, 获取类的模版信息注册一次驱动)
       Class.forName("com.mysql.cj.jdbc.Driver");

//        2.  Create connection with Database

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yuimoriiJDBC", "root", "yuimorii");
//        3.  Create platform to execute SQL queries
        Statement statement = connection.createStatement();


//        4.  Execute SQL queries
        String sql = "select * from t_user where account = '"+account+"' and password = '"+password+"' ;";
        ResultSet resultSet = statement.executeQuery(sql);
//        5.  Close resources
        //进行结果集对象解析
        if (resultSet.next()){
            //只要向下移动，就是有数据 就是登录成功！
            System.out.println("登录成功！");
        }else{
            System.out.println("登录失败！");
        }

        //关闭资源
        resultSet.close();
        statement.close();
        connection.close();

    }
}

