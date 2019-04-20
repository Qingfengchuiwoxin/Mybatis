package com.qfcwx.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Configuration
 * @Author: 清风一阵吹我心
 * @Description: TODO 对应mybatis-config.xml。读取所有配置文件，包括db.properties,mapper.xml
 * @Date: 2019/4/18 11:20
 * @Version 1.0
 **/
public class Configuration {

    private String driver;
    private String url;
    private String userName;
    private String passWord;

    private Map<String, MappedStatement> statementMap = new HashMap<String, MappedStatement>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Map<String, MappedStatement> getStatementMap() {
        return statementMap;
    }

    public void setStatementMap(Map<String, MappedStatement> statementMap) {
        this.statementMap = statementMap;
    }
}
