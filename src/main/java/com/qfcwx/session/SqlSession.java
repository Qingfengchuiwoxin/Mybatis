package com.qfcwx.session;

import java.util.List;

/**
 * @ClassName: SqlSession
 * @Author: 清风一阵吹我心
 * @Description: TODO  mybatis暴露给外部的接口，实现增删改查的能力
 * <span>
 *     1.对外提供数据访问的api
 *     2.对内将请求转发给executor
 *     3.
 * </span>
 * @Date: 2019/4/18 16:45
 * @Version 1.0
 **/
public interface SqlSession {

    /**
     *  //TODO 根据传入的条件查询单一结果
     * @param statement 方法对应的sql语句，namespace + id
     * @param parameter 要传入到sql语句中的查询参数
     * @return T
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * //TODO 查询集合
     * @param statement 方法对应的sql语句，namespace + id
     * @param parameter 要传入到sql语句中的查询参数
     * @return java.util.List<E>
     */
    <E> List<E> selectList(String statement, Object parameter);


    /**
     * //TODO 获取mapper对象
     * @param type 对象的类型
     * @return T
     */
    <T> T getMapper(Class<T> type);


}
