package cn.chen.zy.adapter;

import cn.chen.zy.parse.GsonUtil;
import cn.chen.zy.util.DateUtil;

import java.io.PrintStream;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SimpleConsoleAdapter implements ConsoleAdapter {

    private PrintStream printStream;
    private Class<?> clazz;

    private static final String PLACE_HOLDER = "{}";

    @Override
    public void log(String msg, Object... args) {
        int index = msg.indexOf(PLACE_HOLDER);
        if (index < 0) {
            getPrintStream().println(msg);
        }
        // 类名
        final String clName = this.clazz.getTypeName();
        // 方法名
        final StackTraceElement[] stackTrace = new Exception().getStackTrace();
        final String methodName = getByClassName(stackTrace);
        // 时间
        final String datetime = DateUtil.dateFormat(new Date(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss:S ");
        final StringBuffer sb = new StringBuffer();
        String parse = parse(msg, args, 0);
        sb
                .append(datetime)
                .append(clName)
                .append("$")
                .append(methodName)
                .append(" \\ ")
                .append(parse);
        getPrintStream().println(sb);
    }

    private String getByClassName(StackTraceElement[] stackTrace) {
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean flag = stackTraceElement.getClassName().equals(this.clazz.getTypeName());
            if (flag) {
                return stackTraceElement.getMethodName();
            }
        }
        return "";
    }

    private String parse(String msg, Object[] args, int i) {
        int index = msg.indexOf(PLACE_HOLDER);
        if (index < 0) {
            return msg;
        }
        final String var1 = msg.substring(0, index);
        final String var2 = msg.substring(index + 2);
        final String value = i >= args.length ? "" : parseValue(args[i]);
        return var1 + value + parse(var2, args, ++i);
    }

    private String parseValue(Object arg) {
        if (arg instanceof String sArg) {
            return sArg;
        } else if (arg instanceof Map<?, ?> argMap) {
            return GsonUtil.toJson(argMap);
        } else if (arg instanceof Collection<?> argList) {
            return GsonUtil.toJson(argList);
        } else {
            return arg.toString();
        }
    }

    @Override
    public ConsoleAdapter setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
        return this;
    }

    @Override
    public void setClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    public PrintStream getPrintStream() {
        return printStream == null ? System.out : printStream;
    }
}
