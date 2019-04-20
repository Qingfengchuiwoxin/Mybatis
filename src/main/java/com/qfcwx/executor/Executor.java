package com.qfcwx.executor;

import com.qfcwx.config.MappedStatement;

import java.util.List;

/**
 * @ClassName: Executor
 * @Author: 清风一阵吹我心
 * @Description: TODO Mybatis的核心接口之一，定义了数据库操作最基本的方法，SqlSession的功能都基于它来实现
 * @Date: 2019/4/19 10:22
 * @Version 1.0
 **/
public interface Executor {

    /**
     *  //TODO
     * @param statement
     * @param parameter
     * @return java.util.List<E>
     **/
    <E> List<E> query(MappedStatement statement,Object parameter);
}
