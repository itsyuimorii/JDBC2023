# **JDBC2023**

## Section 02. 全新JDBC技术概述

###  2.1 jdbc技术概念和理解

- jdbc概念总结
  1. jdbc是(Java Database Connectivity)单词的缩写,翻译为java连接数据库
  2. jdbc是java程序连接数据库的**技术统称**
  3. jdbc由**java语言的规范(接口)\**和\**各个数据库厂商的实现驱动(jar)组成**
  4. **jdbc是一种典型的面向接口编程**
  5. jdbc优势
     1. 只需要学习jdbc规范接口的方法,即可操作**所有的数据库软件**
     2. 项目中期切换数据库软件,只需要更换对应的数据库驱动jar包,不需要更改代码

### 2.2 jdbc core api and usage routes

- jdbc技术组成

  1. jdk下jdbc规范接口, 存储在java.sql和javax.sql包中的api

     > 为了项目代码的可移植性，可维护性，SUN公司从最初就制定了Java程序连接各种数据库的统一**接口规范**。这样的话，不管是连接哪一种DBMS软件，Java代码可以保持一致性。

  2. 各个数据库厂商提供的驱动jar包

     > 因为各个数据库厂商的DBMS软件各有不同，那么内部如何通过sql实现增、删、改、查等管理数据，只有这个数据库厂商自己更清楚，因此把接口规范的实现交给各个数据库厂商自己实现。

     jar包是什么?

     > java程序打成的一种压缩包格式，你可以将这些jar包引入你的项目中，然后你可以使用这个java程序中类和方法以及属性了!

- **涉及具体核心类和接口**
  - **DriverManager**
    1. 将第三方数据库厂商的实现驱动jar注册到程序中
    2. 可以根据数据库连接信息获取connection
    
  - Connection
    - 和数据库建立的连接,在连接对象上,可以多次执行数据库crud动作
    - 可以获取statement和 preparedstatement,callablestatement对象
    
  - Statement | **PreparedStatement** | CallableStatement
    - 具体发送SQL语句到数据库管理软件的对象
    - 不同发送方式稍有不同! **preparedstatement **使用为重点!
    
  - Result
    - **面向对象思维的产物**(抽象成数据库的查询结果表)
    
    - 存储DQL查询数据库结果的对象
    
    - 需要我们进行解析,获取具体的数据库数据
    
      
    
  
- API 使用路線
  
  > 靜態SQL路線(沒有動態值語句)
  > DriverManager-> Connection->Statement->Result

  > Pre-compile SQL route( 有動態值語句) 
  >
  > 數據是從外部web傳入- (插入數據)
  >
  > DriverManager-> Connection->PreparedStatement->Result
  
  > 執行標準存儲過SQL路線
  > DriverManager-> Connection->CallableStatement->Result
  
- [mysql 8.0 version: 新しいドライバ API、タイムゾーンの自動選択とデフォルトの utf-8 エンコーディングの追加](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-whats-new.html)

## Section 03. 新しいJDBCコアAPI

### 3.1 Introducing the mysql-jdbc driver jar
### 3.2 jdbc basic usage steps analysis (6 steps)
1.  Load and register the driver class
2.  Create connection with Database
3.  Create platform to execute SQL queries
4.  Execute SQL queries
5.  Close costlv resources

### 3.3  Statement method

> 創建數據庫 yuimoriiJDBC

```sql
drop database if exists yuimoriiJDBC;
CREATE DATABASE yuimoriiJDBC;

USE yuimoriiJDBC;

CREATE TABLE t_user(
   id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'User_primary_key',
   account VARCHAR(20) NOT NULL UNIQUE COMMENT 'account',
   PASSWORD VARCHAR(64) NOT NULL COMMENT 'password',
   nickname VARCHAR(20) NOT NULL COMMENT 'username');
   
INSERT INTO t_user(account,PASSWORD,nickname) VALUES
  ('root','123456','manager'),('admin','666666','Administrators');
  
  
  select id,account, PASSWORD, nickname from t_user;
```

> Statement method - connect database

```java
/**
public class JdbcBasePart {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        /**
         * TODO: 注意
         *   Driver -> com.mysql.cj.jdbc.Driver
         */
        DriverManager.registerDriver(new Driver());

        //2.获取连接
        /**
         * TODO: 注意
         *   面向接口编程
         *   java.sql 接口 = 实现类
         *   connection 使用java.sql.Connection接口接收
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu",
                "root",
                "root");

        //3.创建小车
        Statement statement = connection.createStatement();

        //4.发送SQL语句
        String sql = "select id,account,password,nickname from t_user ;";
        ResultSet resultSet =  statement.executeQuery(sql);

        //5.结果集解析
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+"::"+account+"::"+password+"::"+nickname);
        }
        //6.关闭资源  【先开后关】
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

### 3.4  Statement-based questions

>  Simulated login based on statement

#### ![statement](/Users/yuimorii/Documents/GitHub/JDBC2023/img/statement.png)