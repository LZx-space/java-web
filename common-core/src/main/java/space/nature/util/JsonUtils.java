/*
 * Copyright (c) 2019, LZx
 */

package space.nature.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String fromObject(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

}
