package com.qfcwx.jdbc;

import com.qfcwx.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @ClassName: Connections
 * @Author: 清风一阵吹我心
 * @Description: TODO
 * @Date: 2019/4/20 15:29
 * @Version 1.0
 **/
public class Connections {

    public static Connection getConnection(Configuration configuration) {
        Connection connection = null;
        try {
            //加载驱动
            Class.forName(configuration.getDriver());
            connection = DriverManager.getConnection(configuration.getUrl(), configuration.getUserName(), configuration.getPassWord());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;

    }
}
