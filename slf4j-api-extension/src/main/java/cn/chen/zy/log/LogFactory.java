package cn.chen.zy.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工厂
 */
public class LogFactory {

    /**
     * 获取日志工厂
     *
     * @param clazz 类型
     * @return logger
     */
    public static Logger getLogFactory(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

}
