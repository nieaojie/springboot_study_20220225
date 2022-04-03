package com.example.service.impl;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.example.domain.SMSCode;
import com.example.service.SMSCodeService;
import com.example.utils.CodeUtils;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtils codeUtils;

//    @Override
//    @CachePut(value = "smsCode", key = "#tel")
//    public String sendCodeToSMS(String tel) {
//        return codeUtils.generateCode(tel);
//    }
//
//    @Override
//    public boolean check(SMSCode smsCode) {
//        return smsCode.getCode().equals(codeUtils.getCacheCode(smsCode.getTel()));
//    }

    @Autowired
    private MemcachedClient memcachedClient;

    @Override
    public String sendCodeToSMS(String tel) {
        String code = codeUtils.generateCode(tel);
        try {
            memcachedClient.set(tel, 0, code );
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public boolean check(SMSCode smsCode) {
        String code = null;
        try {
            code = memcachedClient.get(smsCode.getTel());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return smsCode.getCode().equals(code);
    }
}
