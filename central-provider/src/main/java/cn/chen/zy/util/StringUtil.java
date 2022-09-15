package cn.chen.zy.util;

import cn.chen.zy.ex.StringReplaceException;
import cn.chen.zy.judge.AssertJudge;

public class StringUtil {

    private StringUtil() {

    }

    /**
     * String str = "   ";
     * <p>
     * StringUtil.isBlank(str);
     * print result is true
     *
     * @param target target String
     * @return boolean
     */
    public static boolean isBlank(String target) {
        if (null == target || target.length() == 0) return true;
        for (int i = 0; i < target.length(); i++) {
            char isSpace = target.charAt(i);
            if (!Character.isWhitespace(isSpace)) {
                return false;
            }
        }
        return true;
    }

    /**
     * String str = "   ";
     * <p>
     * StringUtil.isNotBlank(str);
     * print result is false
     *
     * @param target target String
     * @return boolean
     */
    public static boolean isNotBlank(String target) {
        return !isBlank(target);
    }

    /**
     * replace all by array
     * <p>
     * String str = "aa&bb^cc";
     * StringUtil.replace(str,"&","","^","");
     * print result is : "aabbcc"
     *
     * @param target target String
     * @param args   params
     * @return String
     */
    public static String replace(String target, String... args) {
        AssertJudge.isTrueManual(args.length % 2 != 0
                , new StringReplaceException("args error . args length must be divisible by 2 !"));
        for (int i = 0; i < args.length; i += 2) {
            target = target.replace(args[i], args[i + 1]);
        }
        return target;
    }

}
