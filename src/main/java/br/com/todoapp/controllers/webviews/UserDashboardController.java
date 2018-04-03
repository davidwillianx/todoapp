package br.com.todoapp.controllers.webviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/dashboard")
public class UserDashboardController {

    @GetMapping
    public String dash(){
        return "users/dashboard";
    }
}
