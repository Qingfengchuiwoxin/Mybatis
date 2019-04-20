package com.qfcwx.config;

/**
 * @ClassName: MappedStatement
 * @Author: 清风一阵吹我心
 * @Description: TODO 映射mapper.xml的实体类namespace,id,resultType,sql...等，对应了一条sql语句
 * @Date: 2019/4/18 11:14
 * @Version 1.0
 **/
public class MappedStatement {

    private String namespace;
    private String sourceId;
    private String resultType;
    private String sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
