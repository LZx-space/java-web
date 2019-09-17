/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class SmsManager {

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private static final String SMS_CACHE_NAME = "SMS:cache";

    private static final int CODE_CACHE_TTL = 2;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送短信验证码
     *
     * @param mobileNo 手机号
     * @return
     */
    public String sendMessage(String mobileNo) {
        String storageKey = UUID.randomUUID().toString().replace("-", "");
        String code = createCode();
        // TODO Send Message Code
        String[] pair = {mobileNo, code};
        redisTemplate.opsForValue().set(storageKey, pair, CODE_CACHE_TTL, TimeUnit.MINUTES);
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
    public ValidResult validCode(String storageKey, String mobileNo, String submitCode) {
        String[] pair = (String[]) redisTemplate.opsForValue().get(storageKey);
        if (pair == null) {
            return ValidResult.EXPIRE;
        }
        String storedNo = pair[0];
        String storedCode = pair[1];
        if (storedNo == null || storedCode == null) {
            return ValidResult.FAIL;
        }
        if (storedNo.equals(mobileNo) && storedCode.equals(submitCode)) {
            return ValidResult.SUCCESS;
        }
        return ValidResult.FAIL;
    }

    /**
     * 创建短信验证码
     *
     * @return
     */
    private String createCode() {
        int start = random.nextInt(9);
        String millis = String.valueOf(System.currentTimeMillis());
        String middle = millis.substring(millis.length() - 4);
        int end = random.nextInt(9);
        return start + middle + end;
    }

    /**
     * 验证结果
     */
    public enum ValidResult {

        SUCCESS, FAIL, EXPIRE

    }

}
