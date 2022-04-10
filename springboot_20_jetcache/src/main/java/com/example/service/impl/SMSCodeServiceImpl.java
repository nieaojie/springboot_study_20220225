package com.example.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.example.domain.SMSCode;
import com.example.service.SMSCodeService;
import com.example.utils.CodeUtils;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtils codeUtils;

    //2.使用远程缓存
    //area设置key的效果：sms_jetCache::1593655xxxx
//    @CreateCache(area = "sms", name = "jetCache::", expire = 10, timeUnit = TimeUnit.SECONDS)
//    private Cache<String, String> jetCache;

    //2.使用本地缓存
    //必须指定使用本地缓存cacheType = CacheType.LOCAL，因为当本地缓存和远程缓存同时配置的话，默认启用的是远程缓存
    //default_jetCacheLocal::1593655xxxx
    @CreateCache(name = "jetCacheLocal::", expire = 30, timeUnit = TimeUnit.SECONDS,
            cacheType = CacheType.LOCAL)
    private Cache<String, String> jetCache;

    @Override
    public String sendCodeToSMS(String tel) {
        String code = codeUtils.generateCode(tel);
        jetCache.put(tel, code);
        return code;
    }

    @Override
    public boolean check(SMSCode smsCode) {
        String code = jetCache.get(smsCode.getTel());
        return smsCode.getCode().equals(code);
    }

}
