package cn.chen.zy.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceReader {

    /**
     * 读取配置文件
     *
     * @param propertiesName 配置文件名称
     * @return Properties
     */
    public static Properties readProperties(String propertiesName) {
        Properties properties = new Properties();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesName);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
