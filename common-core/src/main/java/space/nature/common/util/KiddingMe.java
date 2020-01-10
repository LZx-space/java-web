/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class KiddingMe {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            luckyChance();
        }
    }

    // 幸运的机会来了
    public static void luckyChance() {
        // 蓝球
        int blueNum = new Random().nextInt(16) + 1;
        System.out.println("蓝球：\t" + blueNum);
        // 红球
        List<Integer> redNumArr = new ArrayList<>();
        Random random = new Random();
        Integer redNum = 0;
        do {
            redNum = random.nextInt(33) + 1;
            if (!redNumArr.contains(redNum)) {
                redNumArr.add(redNum);
            }
        } while (redNumArr.size() < 6);
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(2);
        String blueResult = redNumArr.stream()
                .sorted()
                .map(e -> {
                    return numberFormat.format(e);
                })
                .collect(Collectors.joining(",\t", "红球：\t", ""));
        System.out.println(blueResult);
        System.out.println("------------------------------------------------");
    }

}
