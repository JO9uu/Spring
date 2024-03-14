package kr.co.ch08.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.ch08.dto.UserDTO;
import kr.co.ch08.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor // AllArgsConstructor 과 비슷한 기능
@Controller
@Slf4j
public class User2Controller {

    private final UserService service;

    @GetMapping("/user2/login")
    public String login(){
        return "/user2/login";
    }

    @PostMapping("/user2/login")
    public String login(HttpSession session, UserDTO userDTO){

        UserDTO user = service.selectUser(userDTO);
        log.info(""+user);

        if(user != null){
            // 회원이 맞을 경우
            session.setAttribute("sessUser", user);
            return "redirect:/user2/success";

        }else{
            // 회원이 아닌 경우
            return "redirect:/user2/login?success=100";
        }
    }

    @GetMapping("/user2/success")
    public String success(){
        return "/user2/success";
    }

    @GetMapping("/user2/logout")
    public String logout(HttpSession session){

        session.removeAttribute("sessUser");
        session.invalidate();

        return "redirect:/user2/login";
    }

}
