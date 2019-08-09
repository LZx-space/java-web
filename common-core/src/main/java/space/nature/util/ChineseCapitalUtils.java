/*
 * Copyright (c) 2019, LZx
 */

package space.nature.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public abstract class ChineseCapitalUtils {

    private static final String[] CAPITAL_ARRAY = new String[]{"零", "壹", "貳", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private static final String[] UNIT_ARRAY_DECIMAL = new String[]{"厘", "分", "角"};

    private static final String[] UNIT_ARRAY_MULTIPLIER = new String[]{"拾", "佰", "仟"};

    private static final String[] UNIT_ARRAY_CARRY = new String[]{"元", "万", "亿", "兆", "京", "垓", "秭", "穰", "沟", "涧", "正", "载", "极"};

    /**
     * 转换为中文金额<br>
     * <pre style="color:red;">
     * 1.中文进位为10的4次方
     * 2.进位间的按位数分别添加乘数中文:拾、佰、仟
     * 3.小数只有厘分角
     * </pre>
     *
     * @param amount 金额
     * @return 中文金额
     */
    public static String money(String amount) {
        BigDecimal bigDecimal = new BigDecimal(amount).setScale(3, RoundingMode.DOWN).movePointRight(3);
        StringBuilder sb = new StringBuilder();
        String[] digits = bigDecimal.toPlainString().split("");
        for (int len = digits.length, i = 0; i < len; i++) {
            String digit = digits[i];
            sb.append(CAPITAL_ARRAY[Integer.parseInt(digit)]);
            int currentPosition = len - i;
            if (currentPosition < 4) {
                String carry = UNIT_ARRAY_DECIMAL[currentPosition - 1];
                sb.append(carry);
            } else {
                int multiplierPosition = currentPosition % 4;
                boolean isCarryPosition = multiplierPosition == 0;
                if (!isCarryPosition) {
                    String multiplier = UNIT_ARRAY_MULTIPLIER[multiplierPosition - 1];
                    sb.append(multiplier);
                } else {
                    String carry = UNIT_ARRAY_CARRY[currentPosition / 4 - 1];
                    sb.append(carry);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 从10进制转换为其他进制
     *
     * @param positiveDecimal 正整数
     * @param scale           进制
     * @return 新进制下每位的数
     */
    public static List<Long> changeDecimalSystem(long positiveDecimal, int scale) {
        if (scale < 2) {
            throw new IllegalArgumentException("进制不能小于2");
        }
        if (positiveDecimal < 0) {
            throw new IllegalArgumentException("十进制数不能小于0");
        }
        List<Long> digitList = new ArrayList<>();
        while (positiveDecimal > 0) {
            digitList.add(positiveDecimal % scale);
            positiveDecimal = positiveDecimal / scale;
        }
        return digitList;
    }

}
