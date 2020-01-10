/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.util;

import java.util.Objects;

public abstract class PasswordAnalyzer {

    /**
     * 大写字母 小写字母 数字
     *
     * @param password 密码
     * @return
     */
    public static int strength(String password) {
        password.chars().map(ascii -> {
            if (Character.isDigit(ascii)) {
                return 1;
            }
            return 0;
        });
        return 0;
    }

    /**
     * 分析
     *
     * @param ascii ascii码对应的整数
     * @return
     */
    private static int analyzeCharType(int ascii) {
        return 0;
    }

    /**
     * 分析登录密码并返回结果
     *
     * @param pwd 登录密码
     * @return
     */
    public static AnalyzeResult analyzeLoginPassword(String pwd) {
        Objects.requireNonNull(pwd, "密码不能为空");
        AnalyzeResult result = new AnalyzeResult();
        int len = pwd.length();
        result.length = len;
        int mode = 1;
        for (int i = 0; i < len; i++) {
            int ascii = pwd.charAt(i);
            if (isArabicNumber(ascii)) {
                mode |= 1;
                continue;
            }
            if (isLatinLetter(ascii)) {
                mode |= 2;
                continue;
            }
            if (isOtherPermitted(ascii)) {
                mode |= 4;
                continue;
            }
            return result;
        }
        result.noIllegalCharacter = true;
        result.strength = calcPasswordStrength(mode);
        return result;
    }

    /**
     * 是否为阿拉伯数字
     *
     * @param ascii 字符对应的ascii码
     * @return 阿拉伯数字为真
     */
    private static boolean isArabicNumber(int ascii) {
        return 47 < ascii && ascii < 58;
    }

    /**
     * 是否为拉丁字母
     *
     * @param ascii 字符对应的ascii码
     * @return 拉丁数字为真
     */
    private static boolean isLatinLetter(int ascii) {
        return 64 < ascii && ascii < 91 || 96 < ascii && ascii < 123;
    }

    /**
     * 是否为其他允许的符号
     *
     * @param ascii 字符对应的ascii码
     * @return 是否为其它可允许的符号
     */
    private static boolean isOtherPermitted(int ascii) {
        return 31 < ascii && ascii < 48
                || 58 < ascii && ascii < 64
                || 91 < ascii && ascii < 97
                || 122 < ascii && ascii < 127;
    }

    /**
     * 计算密码强度
     *
     * @param mode 上述方法算出的密码各字符对应ASCII码的与或值之和
     * @return 密码强度
     */
    private static PasswordStrengthEnum calcPasswordStrength(int mode) {
        if (mode == 1 || mode == 2 || mode == 4) {
            return PasswordStrengthEnum.LOW;
        }
        if (mode == 3 || mode == 5 || mode == 6) {
            return PasswordStrengthEnum.MIDDLE;
        }
        return PasswordStrengthEnum.HIGH;
    }

    /**
     * 密码强度枚举
     */
    public enum PasswordStrengthEnum {
        LOW, MIDDLE, HIGH
    }

    /**
     * 密码构成分析结果
     */
    public static class AnalyzeResult {

        /**
         * 密码长度
         */
        private int length;

        /**
         * 是否无非法合法
         */
        private boolean noIllegalCharacter;

        /**
         * 如果合法则密码强度如何
         */
        private PasswordStrengthEnum strength;

        public int getLength() {
            return length;
        }

        public boolean isNoIllegalCharacter() {
            return noIllegalCharacter;
        }

        public PasswordStrengthEnum getStrength() {
            return strength;
        }
    }

    public static void main(String[] args) {
        AnalyzeResult analyzeResult = analyzeLoginPassword("123a@");
        System.out.println("长度：" + analyzeResult.getLength() + "，无非法字符：" + analyzeResult.noIllegalCharacter + "，密码强度：" + analyzeResult.getStrength());
    }
}
