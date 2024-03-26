package kr.co.sboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;

@Controller
public class MyController {

    @GetMapping("/my/setting")
    public String setting(){
        return "/my/setting";
    }

}
