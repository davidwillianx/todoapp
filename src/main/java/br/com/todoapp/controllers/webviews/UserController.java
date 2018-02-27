package br.com.todoapp.controllers.webviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users/")
public class UserController {

    private ModelAndView view;


    @RequestMapping("/access/{accessAction}")
    public ModelAndView accsss(@PathVariable String accessAction){

        view  = new ModelAndView("users/access");
        view.addObject("action", accessAction);

        return view;
    }

    @RequestMapping("/access/register")
    public String register() {
        return "users/register";
    }
}
