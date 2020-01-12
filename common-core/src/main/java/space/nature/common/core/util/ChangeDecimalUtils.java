/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.util;

import java.util.ArrayList;
import java.util.List;

public class ChangeDecimalUtils {

    /**
     * 从10进制转换为其他进制
     *
     * @param positiveDecimal 正整数
     * @param scale           进制
     * @return 新进制下每位的数
     */
    public static List<Long> fromDecimalSystem(long positiveDecimal, int scale) {
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
