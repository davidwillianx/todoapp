package br.com.todoapp.controllers.webviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users/")
public class UserController {

    @GetMapping("/access/signin")
    public String accsss(){
        return "users/access";
    }

    @GetMapping("/access/register")
    public String register() {
        return "users/register";
    }
}
