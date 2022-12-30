package com.yuimorii.jdbc;

import java.util.Scanner;

/**
 * @program: JDBC2023
 * @description:
 * @author: yuimorii
 * @create: 2022-12-29 12:33
 **/
public class TestMain {
    public static void main(String[] args) {
        //获取用户输入
        Scanner sc = new Scanner(System.in);
        System.out.println("please input num");
        int num = Integer.parseInt(sc.nextLine());
        //1把数组转换为2进制
        String intStr = Integer.toBinaryString(num);
//        System.out.println(intStr);
        intStr = intStr.replace("1", "#1#");
        //去掉二进制中的1
        String[] strArray = intStr.split("1");

        //for-loop 遍历二进制数组
        for (int i = 0; i < strArray.length; i++) {

            String str = strArray[i];


        }


    }
}
