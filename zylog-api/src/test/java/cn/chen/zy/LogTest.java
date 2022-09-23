package cn.chen.zy;

import cn.chen.zy.enumation.LevelEnum;
import cn.chen.zy.factory.ZyLog;
import cn.chen.zy.factory.ZyLogFactory;
import cn.chen.zy.parse.GsonUtil;
import org.junit.Test;

import java.util.ArrayList;

public class LogTest {

    private final ZyLog log = ZyLogFactory.getZyLogFactory(LogTest.class);

    @Test
    public void infoTest() {
        ArrayList<String> result = new ArrayList<>();
        result.add("abc");
        result.add("123");
        LevelEnum trace = LevelEnum.TRACE;
        log.debug("result is : {} & trace level is : {}", result, trace);
        log.info("result is : {} & trace level is : {}", result, GsonUtil.toJson(trace));
    }

    @Test
    public void errorTest() {
        ArrayList<String> result = new ArrayList<>();
        result.add("abc");
        result.add("123");
        LevelEnum trace = LevelEnum.TRACE;
        log.error("result is : {} & trace level is : {}", result, trace);
    }

}
