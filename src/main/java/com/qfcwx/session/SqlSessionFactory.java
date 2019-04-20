package com.qfcwx.session;

import com.qfcwx.config.Configuration;
import com.qfcwx.config.MappedStatement;
import com.qfcwx.session.impl.DefaultSqlSession;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName: SqlSessionFactory
 * @Author: 清风一阵吹我心
 * @Description: TODO  1.读取配置文件，解析信息，填充到configuration中。2.生产SqlSession
 * @Date: 2019/4/18 15:31
 * @Version 1.0
 **/
public class SqlSessionFactory {

    private final Configuration configuration = new Configuration();

    /**
     * 记录mapper.xml存放的位置
     **/
    private static final String MAPPER_CONFIG_LOCATION = "mapper";
    /**
     * 记录数据路连接信息存放的文件
     **/
    private static final String DB_CONFIG_FILE = "db.properties";

    public SqlSessionFactory() {
        loadDBInfo();
        loadMappersInfo();
    }
    
    /**
     *  //TODO 读取数据库配置文件信息
     * @param 
     * @return void
     **/
    private void loadDBInfo() {
        //加载数据库信息配置文件
        InputStream stream = SqlSessionFactory.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将数据库配置信息写入configuration对象中
        configuration.setDriver(properties.get("driver").toString());
        configuration.setUrl(properties.get("url").toString());
        configuration.setUserName(properties.get("username").toString());
        configuration.setPassWord(properties.get("password").toString());
    }
    
    /**
     *  //TODO 获取指定文件下的所有mapper.xml文件
     * @param 
     * @return void
     **/
    private void loadMappersInfo() {
        URL resource = null;
        resource = SqlSessionFactory.class.getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
        //获取指定文件夹信息
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] mappers = file.listFiles();
            //遍历文件夹下所有的mapper.xml文件，解析后，注册到configuration中
            for (File mapper : mappers) {
                loadMapper(mapper);
            }
        }
    }
    
    /**
     *  //TODO 对mapper.xml文件解析
     * @param mapper
     * @return void
     **/
    private void loadMapper(File mapper) {
        //创建SAXReader对象
        SAXReader saxReader = new SAXReader();
        //通过read方法读取一个文件，转换成Document对象
        Document document = null;
        try {
            document = saxReader.read(mapper);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取根节点元素对象<mapper>
        Element rootElement = document.getRootElement();
        //获取命名空间
        String namespace = rootElement.attribute("namespace").getData().toString();
        //获取子节点<select>标签
        List<Element> selects = rootElement.elements("select");
        //遍历select节点，将信息记录到MappedStatement对象，并登记到Configuration对象中
        for (Element element : selects) {
            MappedStatement statement = new MappedStatement();
            String id = element.attribute("id").getData().toString();
            String resultType = element.attribute("resultType").getData().toString();
            //读取sql语句信息
            String sql = element.getData().toString();

            String sourceId = namespace + "." + id;
            //给MappedStatement对象赋值
            statement.setSourceId(sourceId);
            statement.setNamespace(namespace);
            statement.setResultType(resultType);
            statement.setSql(sql);
            configuration.getStatementMap().put(sourceId, statement);
        }

    }

    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
