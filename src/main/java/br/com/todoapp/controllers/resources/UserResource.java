package br.com.todoapp.controllers.resources;

import br.com.todoapp.models.domains.Permission;
import br.com.todoapp.models.domains.User;
import br.com.todoapp.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPermission(Permission.ROLE_ADMIN);
      return   userService.create(user);
    }




}
