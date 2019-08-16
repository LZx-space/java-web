/*
 * Copyright (c) 2019, LZx
 */

package space.nature.security;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SmsManager {

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public String sendMessage(String mobileNo) {
        String storageKey = UUID.randomUUID().toString();
        String code = createCode();

        return storageKey;
    }

    /**
     * 验证短信验证码
     *
     * @param storageKey 数据存储的key
     * @param mobileNo   手机号
     * @param submitCode 用户提交的code
     * @return
     */
    public static boolean validCode(String storageKey, String mobileNo, String submitCode) {
        // TODO 
        return false;
    }

    private String createCode() {
        int start = random.nextInt(9);
        String millis = String.valueOf(System.currentTimeMillis());
        String middle = millis.substring(millis.length() - 4);
        int end = random.nextInt(9);
        return start + middle + end;
    }

}
