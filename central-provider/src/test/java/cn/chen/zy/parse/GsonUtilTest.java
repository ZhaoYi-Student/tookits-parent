package cn.chen.zy.parse;


import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GsonUtilTest {

    private final String json = "{\"name\":\"G10.2 Service\",\"ext\":\"rar\",\"file\":\"http://127.0.0.1/files/downloads/sid/1001\",\"filemd5\":\"83b9343eda6be35d7fed158dee0839c8\"}";

    private final String jsonList = "[1,2,3,4,5]";


    @Test
    public void toJsonTest() {
        String jsonListMap = "[{\"name\":\"G10.2 Service\",\"ext\":\"rar\",\"file\":\"http://127.0.0.1/files/downloads/sid/1001\",\"filemd5\":\"83b9343eda6be35d7fed158dee0839c8\"},{\"name\":\"ph10.2service\",\"ext\":\"doc\",\"file\":\"http://127.0.0.1/files/downloads/sid/1002\",\"filemd5\":\"ff13f7f55f7154f6984d9a26d8a317f9\"}]";
        List<Map<String, String>> mapList = GsonUtil.fromJsonToListMap(jsonListMap, String.class, String.class);
        String s = GsonUtil.toJson(mapList);
        System.out.println(s);
    }

    @Test
    public void fromJsonObjectTest() {
        JsonObject jsonObject = GsonUtil.fromJson(json, JsonObject.class);
        System.out.println(jsonObject.get("name").getAsString());
    }

    @Test
    public void fromJsonToMapTest() {
        Map<String, String> map = GsonUtil.fromJsonToMap(json, String.class, String.class);
        System.out.println(map.get("name"));
    }

    @Test
    public void fromJsonToListTest() {
        List<Integer> list = GsonUtil.fromJsonToList(jsonList, Integer.class);
        System.out.println(list.get(0));
    }

    @Test
    public void fromJsonTest() {
        List<Integer> list = GsonUtil.fromJson(jsonList, new TypeToken<>() {
        });
        System.out.println(list.get(0));
    }

}