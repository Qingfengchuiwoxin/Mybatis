package com.qfcwx.executor.impl;

import com.qfcwx.config.Configuration;
import com.qfcwx.config.MappedStatement;
import com.qfcwx.executor.Executor;
import com.qfcwx.jdbc.Connections;
import com.qfcwx.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DefaultExecutor
 * @Author: 清风一阵吹我心
 * @Description: TODO  操作数据库的操作，实则mybatis底层封装的连接池
 * @Date: 2019/4/19 11:10
 * @Version 1.0
 **/
public class DefaultExecutor implements Executor {

    private Configuration configuration;

    public DefaultExecutor(Configuration configuration) {
        super();
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> query(MappedStatement statement, Object parameter) {
        List<E> list = new ArrayList<E>();
        //获取连接
        Connection connection = Connections.getConnection(configuration);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //创建预编译PreparedStatement对象，从MappedStatement获取sql语句
            preparedStatement = connection.prepareStatement(statement.getSql());
            //处理sql中的占位符
            parameterSize(preparedStatement, parameter);
            //执行查询操作获取resultSet
            resultSet = preparedStatement.executeQuery();
            //将结果集通过反射技术，填充到list中
            handleResult(resultSet, list, statement.getResultType());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * //TODO 对PreparedStatement中的占位符进行处理
     *
     * @param statement
     * @param parameter
     * @return void
     */
    private void parameterSize(PreparedStatement statement, Object parameter) throws SQLException {
        if (parameter instanceof Integer) {
            statement.setInt(1, (Integer) parameter);
        } else if (parameter instanceof Long) {
            statement.setLong(1, (Long) parameter);
        } else if (parameter instanceof String) {
            statement.setString(1, (String) parameter);
        }
    }

    /**
     * //TODO 读取ResultSet中的数据,并 转换成目标对象
     *
     * @param resultSet
     * @param ret
     * @param className
     * @return void
     */
    private <E> void handleResult(ResultSet resultSet, List<E> ret, String className) {
        Class<E> clazz = null;
        //通过反射获取类的对象
        try {
            clazz = (Class<E>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                //通过反射实例化对象
                Object model = clazz.newInstance();
                //使用反射工具将ResultSet中的数据填充到entity中
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = (User) model;
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                ret.add((E) user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
