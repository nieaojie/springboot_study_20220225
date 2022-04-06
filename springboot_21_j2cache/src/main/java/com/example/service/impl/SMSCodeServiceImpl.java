package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.oschina.j2cache.CacheChannel;

import com.example.domain.SMSCode;
import com.example.service.SMSCodeService;
import com.example.utils.CodeUtils;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtils codeUtils;
    //1.获取CacheChannel
    @Autowired
    private CacheChannel cacheChannel;

    @Override
    public String sendCodeToSMS(String tel) {
        String code = codeUtils.generateCode(tel);
        //2.使用
        cacheChannel.set("smsCode", tel, code);
        return code;
    }

    @Override
    public boolean check(SMSCode smsCode) {
        String code = cacheChannel.get("smsCode", smsCode.getTel()).asString();
        return smsCode.getCode().equals(code);
    }
}
