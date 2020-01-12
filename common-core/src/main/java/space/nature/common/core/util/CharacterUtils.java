/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.util;

public abstract class CharacterUtils {

    /**
     * 是否为数字
     *
     * @param ch 字符
     * @return
     */
    public static boolean isArabicNumber(Character ch) {
        int i = (int) ch;
        return 47 < i && i < 58;
    }

    /**
     * 是否为大写字母
     *
     * @param ch 字符
     * @return
     */
    public static boolean isUpperCaseLetter(Character ch) {
        int i = (int) ch;
        return 64 < i && i < 91;
    }

    /**
     * 是否为小写字母
     *
     * @param ch 字符
     * @return
     */
    public static boolean isLowerCaseLetter(Character ch) {
        int i = (int) ch;
        return 94 < i && i < 123;
    }

    public static void main(String[] args) {
        char c = "二".charAt(0);
        System.out.println(c);
        System.out.println(Character.isDigit(c) + " " + Character.isLetter(c));
        System.out.println((int) ("A".charAt(0)));
        System.out.println((int) ("Z".charAt(0)));
    }
}
