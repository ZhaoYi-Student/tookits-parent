package cn.chen.zy.adapter;

import java.io.PrintStream;

public interface ConsoleAdapter {

    /**
     * 控制台输出
     *
     * @param msg  日志
     * @param args 参数
     */
    void log(String msg, Object... args);

    /**
     * 设置打印流
     *
     * @param printStream 打印流
     * @return ConsoleAdapter
     */
    ConsoleAdapter setPrintStream(PrintStream printStream);

    void setClass(Class<?> clazz);

}
