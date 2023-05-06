package com.appiron.appleservices.common;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author hpg
 */
public abstract class GenerationConvert {

    protected final Map<String, Object> data = new HashMap<>();

    protected GenerationConvert() {

    }

    public Object put(String key, Object value) {
        return data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public final String toJson() {
        data.putAll(init());
        return JSON.toJSON(data);
    }

    private Map<String, Object> init() {
        Map<String, Object> temp = new HashMap<>();
        List<Field> fields = new ArrayList<>();
        Class<?> tempClass = this.getClass();
        while (tempClass != null) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }

        for (Field field : fields) {
            JsonProperty annotation = field.getAnnotation(JsonProperty.class);
            if (annotation != null) {
                String name = annotation.value();
                try {
                    field.setAccessible(true);
                    Object value = field.get(this);
                    if (Objects.nonNull(value)) {
                        temp.put(name, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return temp;
    }

    public Map<String, Object> toParams() {
        HashMap<String, Object> temp = new HashMap<>(data);
        temp.putAll(init());
        return temp;
    }

    /**
     * uri params
     *
     * @deprecated toParams
     */
    @Deprecated
    public String toUriParams() {
        StringBuilder stringBuffer = new StringBuilder();
        boolean isFirst = true;
        for (Field field : this.getClass().getDeclaredFields()) {
            JsonProperty annotation = field.getAnnotation(JsonProperty.class);
            if (annotation == null) {
                continue;
            }
            String name = annotation.value();
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value == null) {
                    continue;
                }
                Class<?> fieldType = field.getType();
                //todo valid other field type
                if (fieldType.isArray()) {
                    StringBuilder array = new StringBuilder();
                    Object[] list = (Object[]) value;
                    for (int i = 0; i < list.length; i++) {
                        if (i == 0) {
                            array.append(list[i]);
                        } else {
                            array.append(",").append(list[i]);
                        }
                    }
                    value = array.toString();
                }

                if (isFirst) {
                    stringBuffer.append(name).append("=").append(value);
                    isFirst = false;
                } else {
                    stringBuffer.append("&").append(name).append("=").append(value);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public String toString() {
        return toJson();
    }
}
