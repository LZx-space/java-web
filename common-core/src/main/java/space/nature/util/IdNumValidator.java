/*
 * Copyright (c) 2019, LZx
 */

package space.nature.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 证件号码验证器
 */
public abstract class IdNumValidator {

    /**
     * 前两位<br>
     * 11-15：京津冀晋蒙<br>
     * 21-23：辽吉黑<br>
     * 31-37：沪苏浙皖闽赣鲁<br>
     * 41-46：渝鄂湘粤桂琼<br>
     * 50-54：于川贵宁藏<br>
     * 61-65：陕甘宁新<br>
     * 81-83：港澳台<br>
     */
    private static final Pattern ID_CARD_NUMBER_18_PATTERN = Pattern.compile("^(11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|81|82|83)\\d{4}(19|20)\\d{2}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\\d)|(3[0-1]))\\d{3}[0-9Xx]$");

    /**
     * 规则参上
     */
    private static final Pattern ID_CARD_NUMBER_15_PATTERN = Pattern.compile("^(11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|81|82|83)\\d{6}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\\d)|(3[0-1]))\\d{2}[0-9Xx]$");

    /**
     * 是否为身份证号码
     *
     * @param idCardNum 待验证身份证号
     * @return 是则true反之false
     */
    public static boolean isIdCardNum(String idCardNum) {
        if (StringUtils.isBlank(idCardNum)) {
            throw new IllegalArgumentException("身份证号码不能为空");
        }
        int len = idCardNum.length();
        if (len == 18) {
            return ID_CARD_NUMBER_18_PATTERN.matcher(idCardNum).matches();
        } else if (len == 15) {
            return ID_CARD_NUMBER_15_PATTERN.matcher(idCardNum).matches();
        }
        return false;
    }

}
