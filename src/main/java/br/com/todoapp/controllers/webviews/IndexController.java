package br.com.todoapp.controllers.webviews;

import br.com.todoapp.models.services.UserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    public UserAuthenticatedService userAuthenticatedService;

    @RequestMapping("/")
    public String index(){

        if(userAuthenticatedService.getUser().isPresent())
             return "redirect:/users/dashboard";

       return "index";
    }

}

