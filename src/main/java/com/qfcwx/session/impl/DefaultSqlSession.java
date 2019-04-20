package com.qfcwx.session.impl;

import com.qfcwx.config.Configuration;
import com.qfcwx.config.MappedStatement;
import com.qfcwx.executor.Executor;
import com.qfcwx.executor.impl.DefaultExecutor;
import com.qfcwx.proxy.MappedProxy;
import com.qfcwx.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @ClassName: DefaultSqlSession
 * @Author: 清风一阵吹我心
 * @Description: TODO
 * @Date: 2019/4/18 16:53
 * @Version 1.0
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private final Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        super();
        this.configuration = configuration;
        this.executor = new DefaultExecutor(configuration);
    }


    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.selectList(statement, parameter);
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new RuntimeException("too man result");
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        MappedStatement smt = this.configuration.getStatementMap().get(statement);
        return executor.query(smt, parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        MappedProxy mappedProxy = new MappedProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, mappedProxy);
    }

}
