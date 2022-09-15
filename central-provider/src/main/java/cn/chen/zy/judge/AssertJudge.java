package cn.chen.zy.judge;

import cn.chen.zy.ex.AssertJudgeException;

public class AssertJudge {

    private AssertJudge() {
    }

    public static void isTrueAutomatic(boolean correctness, String message) {
        if (correctness) {
            throw new AssertJudgeException(message);
        }
    }

    public static void isTrueManual(boolean correctness, RuntimeException ex) {
        if (correctness) {
            throw ex;
        }
    }

    public static void isNull(){

    }

}
