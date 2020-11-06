package com.example.apidemo.util;

import java.lang.reflect.Field;
import java.util.Map;

public class UpdatePartialUtil {

    public static void update(Class type, Object who, Map<String, Object> what) {
        what.forEach((k, v) -> {
            try {
                Field field = type.getDeclaredField(k);
                field.setAccessible(true);
                field.set(who, v);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

}
