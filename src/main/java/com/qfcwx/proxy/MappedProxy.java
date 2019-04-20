package com.qfcwx.proxy;

import com.qfcwx.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @ClassName: MapperdProxy
 * @Author: 清风一阵吹我心
 * @Description: TODO
 * @Date: 2019/4/20 13:41
 * @Version 1.0
 **/
public class MappedProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MappedProxy(SqlSession sqlSession) {
        super();
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        //调用这个方法的class或接口 与 参数cls表示的类或接口相同，或者是参数cls表示的类或接口的父类，则返回tr。
        //判断返回值是否为Collection的子类
        if (Collection.class.isAssignableFrom(returnType)) {
            return sqlSession.selectList(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
        } else {
            return sqlSession.selectOne(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
        }
    }
}
