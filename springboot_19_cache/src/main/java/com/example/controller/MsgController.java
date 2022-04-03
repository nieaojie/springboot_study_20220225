package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.MsgService;

@RestController
@RequestMapping(value = "/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @GetMapping("/getCode")
    public String getCode(@RequestParam String tel) {
        return msgService.get(tel);
    }

    @PostMapping("/check")
    public boolean check(@RequestBody Map<String, String> map) {
        return msgService.check(map.get("tel"), map.get("code"));
    }
}
