package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.User;

@Controller
public class UserController {

    @RequestMapping(value = "users", method = RequestMethod.POST)
    @ResponseBody
    public String save() {
        return "user save";
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        return "user delete id=" + id;
    }

    @RequestMapping(value = "users", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody User user) {
        return "user update username=" + user.getUsername() + " password" + user.getPassword();
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable Integer id) {
        return "user find id=" + id;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ResponseBody
    public String getAll() {
        return "user getAll";
    }
}
