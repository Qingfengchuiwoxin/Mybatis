package com.qfcwx.test;

import com.qfcwx.mapper.UserMapper;
import com.qfcwx.pojo.User;
import com.qfcwx.session.SqlSession;
import com.qfcwx.session.SqlSessionFactory;

import java.util.List;

/**
 * @ClassName: Test
 * @Author: 清风一阵吹我心
 * @Description: TODO
 * @Date: 2019/4/18 16:57
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);


        User user = mapper.selectUserById(10L);
        System.out.println(user);
        List<User> userList = mapper.selectList();
        System.out.println(userList);


    }
}
