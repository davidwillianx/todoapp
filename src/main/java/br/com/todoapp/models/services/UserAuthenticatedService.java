package br.com.todoapp.models.services;

import br.com.todoapp.models.converters.UserAuth;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuthenticatedService {

    private UserAuth user;


    public Optional<UserAuth> getUser(){
        SecurityContext context = SecurityContextHolder.getContext();

        //@TODO HARDCODED JUST CHECK IT OUT HOW TO SOLVE IN DIFF WAY
        boolean isUserAnonymous = context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

        if(!isUserAnonymous){
            user =  (UserAuth) context.getAuthentication().getPrincipal();
            return Optional.of(user);
        }

        return Optional.empty();

    }

}
