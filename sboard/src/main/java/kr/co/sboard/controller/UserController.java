package kr.co.sboard.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@AllArgsConstructor
public class UserController {

    @GetMapping(value = "/user/login")
    public String login(){
        return "/user/login";
    }

    @GetMapping(value = "/user/register")
    public String register(){
        return "/user/register";
    }

    @GetMapping(value = "/user/terms")
    public String terms(){
        return "/user/terms";
    }



}
