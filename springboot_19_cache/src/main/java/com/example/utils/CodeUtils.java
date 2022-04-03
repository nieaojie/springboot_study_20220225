package com.example.utils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtils {

    private String patch[] = { "000000", "00000", "0000", "000", "00", "0", "" };

    public String generateCode(String tel) {
        int hash = tel.hashCode();
        int encryption = 20220330;
        long result = hash ^ encryption;
        long nowTime = System.currentTimeMillis();
        result = result ^ nowTime;
        long code = result % 1000000;
        code = code < 0 ? -code : code;
        String codeStr = code + "";
        int len = codeStr.length();
        return patch[len] + codeStr;
    }

    /**
     * 该方法用于获取缓存中的tel对应的code
     * @param tel
     * @return 若缓存中存在则将其返回，否则返回null
     */
    @Cacheable(value = "smsCode",key = "#tel")
    public String getCacheCode(String tel) {
        return null;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(new CodeUtils().generateCode("15936553896"));
        }
    }
}
