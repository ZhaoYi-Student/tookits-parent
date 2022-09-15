package cn.chen.zy.parse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class GsonUtil {

    public static final Gson GSON = new Gson();

    private GsonUtil() {
    }

    public static <T> String toJson(T target) {
        return GSON.toJson(target);
    }

    public static <T> T fromJson(String json, Class<T> targetClazz) {
        return GSON.fromJson(json, targetClazz);
    }

    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> targetKeyClazz, Class<V> targetValueClazz) {
        return GSON.fromJson(json, new TypeToken<Map<K, V>>() {
        }.getType());
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> targetClazz) {
        return GSON.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    public static <T> T fromJson(String json, TypeToken<T> typeToken) {
        return GSON.fromJson(json, typeToken.getType());
    }

}
