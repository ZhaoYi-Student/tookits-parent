package cn.chen.zy.enumation;

import java.lang.reflect.Field;

public enum LevelEnum {

    TRACE(1, "TRACE"),
    DEBUG(2, "DEBUG"),
    INFO(3, "INFO"),
    WARN(4, "WARN"),
    ERROR(5, "ERROR");

    private final Integer order;
    private final String name;

    LevelEnum(Integer order, String name) {
        this.order = order;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getOrder() {
        return order;
    }

    public static LevelEnum get(String name) {
        Field[] fields = LevelEnum.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (LevelEnum.class.getTypeName().equals(field.getType().getTypeName())
                    && field.getName().equals(name)) {
                try {
                    return (LevelEnum) field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
