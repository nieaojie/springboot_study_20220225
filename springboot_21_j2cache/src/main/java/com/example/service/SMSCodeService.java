package com.example.service;

import com.example.domain.SMSCode;

public interface SMSCodeService {
    public String sendCodeToSMS(String tel);

    public boolean check(SMSCode smsCode);
}
