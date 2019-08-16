/*
 * Copyright (c) 2019, LZx
 */

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import space.nature.util.HttpClient;

import java.io.IOException;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) throws IOException {

        Pattern pattern = Pattern.compile("^(.*\\.[A-Za-z]+)$");
        System.out.println(pattern.matcher("http://f.ico").matches());
        long start = System.currentTimeMillis();
        try {
//        HttpAsyncClient.get("http://www.taobao.com");
            HttpClient.get("http://www.taobao.com");
        } finally {
            System.out.println(System.currentTimeMillis() - start);
        }
        Jackson2ObjectMapperBuilder.json().build().writeValueAsString(null);
    }
}
