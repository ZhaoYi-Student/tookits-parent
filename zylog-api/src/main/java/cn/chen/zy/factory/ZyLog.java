package cn.chen.zy.factory;

public interface ZyLog {

    void trace(String msg, Object... args);

    void debug(String msg, Object... args);

    void info(String msg, Object... args);

    void warn(String msg, Object... args);

    void error(String msg, Object... args);

}
