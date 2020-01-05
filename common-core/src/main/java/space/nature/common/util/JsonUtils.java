/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

public abstract class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final ObjectReader objectReader = objectMapper.reader();

    private static final ObjectWriter objectWriter = objectMapper.writer();

    /**
     * 将对象序列化为字符串
     *
     * @param obj 需被序列化的对象
     * @return
     * @throws JsonProcessingException
     */
    public static String write(Object obj) throws JsonProcessingException {
        return objectWriter.writeValueAsString(obj);
    }

    /**
     * 将字符串反序列化为对象
     *
     * @param json  字符串
     * @param clazz 对象的类类型
     * @param <T>   类型泛型
     * @return
     * @throws IOException
     */
    public static <T> T read(String json, Class<T> clazz) throws IOException {
        return objectReader.readValue(json);
    }

}
