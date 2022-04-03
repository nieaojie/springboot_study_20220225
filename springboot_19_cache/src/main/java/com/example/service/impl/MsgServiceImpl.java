package com.example.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.service.MsgService;

@Service
public class MsgServiceImpl implements MsgService {

    private Map<String, String> cache = new HashMap<>();

    @Override
    public String get(String tel) {
        String code = tel.substring(tel.length() - 6);
        cache.put(tel, code);
        return code;
    }

    @Override
    public boolean check(String tel, String code) {
        String queryCode = cache.get(tel);
        if (code.equals(queryCode)) {
            cache.remove(tel);
            return true;
        }
        return false;
    }
}
