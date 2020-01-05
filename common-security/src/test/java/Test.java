/*
 * Copyright (c) 2019, LZx
 */

import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        Pattern compile = Pattern.compile("([0-9]+[^0-9]+)|([^0-9]+[0-9]+)");
        System.out.println(compile.matcher("123456").matches());
        System.out.println(compile.matcher("a12345").matches());
        System.out.println(compile.matcher("1aaaaa").matches());
        System.out.println(compile.matcher("aaa1aa").matches());
    }
}
