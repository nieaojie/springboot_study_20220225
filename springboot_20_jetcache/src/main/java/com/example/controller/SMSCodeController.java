package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.SMSCode;
import com.example.service.SMSCodeService;

@RestController
@RequestMapping(value = "/sms")
public class SMSCodeController {
    @Autowired
    private SMSCodeService smsCodeService;

    @GetMapping(value = "/sendCodeToSMS")
    public String sendCodeToSMS(@RequestParam(value = "tel") String tel) {
        return smsCodeService.sendCodeToSMS(tel);
    }

    @PostMapping("/check")
    public boolean check(@RequestBody SMSCode smsCode) {
        return smsCodeService.check(smsCode);
    }

}
