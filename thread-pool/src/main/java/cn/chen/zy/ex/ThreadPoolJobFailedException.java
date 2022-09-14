package cn.chen.zy.ex;

public class ThreadPoolJobFailedException extends RuntimeException{

    public ThreadPoolJobFailedException(String message) {
        super(message);
    }
}
