package cn.chen.zy.factory;

import cn.chen.zy.adapter.ConsoleAdapter;
import cn.chen.zy.enumation.LevelEnum;
import cn.chen.zy.file.ResourceReader;
import cn.chen.zy.judge.AssertJudge;

import java.io.PrintStream;
import java.util.Properties;

public class ZyLogFactory implements ZyLog {

    private final Class<?> clazz;
    private final String clazzName;
    private LevelEnum levelEnum;
    private ConsoleAdapter consoleAdapter;
    private String infoPath;
    private String errorPath;

    private ZyLogFactory(Class<?> clazz) {
        this.clazz = clazz;
        this.clazzName = clazz.getName();
        try {
            loadProperties();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        obtainAdapterProperties();
    }

    private void obtainAdapterProperties() {
        AssertJudge.isTrueManual(null == this.consoleAdapter, new RuntimeException("consoleAdapter must not be null !"));
        consoleAdapter.setClass(this.clazz);
    }

    private void loadProperties() throws ReflectiveOperationException {
        // 读取配置文件
        final String propertiesName = "zyLog.properties";
        Properties properties = ResourceReader.readProperties(propertiesName);
        // 日志级别
        final String level = properties.getProperty("zy.log.level");
        LevelEnum levelEnum = LevelEnum.get(level);
        // 打印类
        final String className = properties.getProperty("zy.log.adapter");
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.getConstructor().newInstance();
        AssertJudge.isTrueManual(!(obj instanceof ConsoleAdapter), new RuntimeException(clazz.getName() + " cannot convert to ConsoleAdapter"));
        // 普通日志输出文件路径
        final String infoPath = properties.getProperty("zy.log.info.out");
        // 错误日志输出文件路径
        final String errorPath = properties.getProperty("zy.log.error.out");
        // 赋值
        this.levelEnum = levelEnum;
        this.consoleAdapter = (ConsoleAdapter) obj;
        this.infoPath = infoPath;
        this.errorPath = errorPath;
    }

    public static ZyLog getZyLogFactory(Class<?> clazz) {
        return new ZyLogFactory(clazz);
    }

    @Override
    public void trace(String msg, Object... args) {
        log(LevelEnum.TRACE, msg, args, null);
    }

    @Override
    public void debug(String msg, Object... args) {
        log(LevelEnum.DEBUG, msg, args, null);
    }

    @Override
    public void info(String msg, Object... args) {
        log(LevelEnum.INFO, msg, args, null);
    }

    @Override
    public void warn(String msg, Object... args) {
        log(LevelEnum.WARN, msg, args, null);
    }

    @Override
    public void error(String msg, Object... args) {
        log(LevelEnum.ERROR, msg, args, System.err);
    }

    /**
     * 输出日志
     *
     * @param levelEnum
     * @param msg
     * @param args
     */
    private void log(LevelEnum levelEnum, String msg, Object[] args, PrintStream printStream) {
        if (null == printStream) {
            printStream = System.out;
        }
        if (levelEnum.getOrder() >= this.levelEnum.getOrder()) {
            consoleAdapter.setPrintStream(printStream).log("LEVEL - [" + levelEnum.getName() + "] : " + msg, args);
        }
    }
}
