package br.com.todoapp.models.services;

import br.com.todoapp.models.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("AuthDetailsService")
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userFound = userService.searchByEmail(email);

        if(userFound == null) throw  new UsernameNotFoundException("Usuario nao econtrado");

        return new org.springframework.security.core.userdetails.User(userFound.getEmail(), userFound.getPassword(), getAuthorities(userFound.getPermission().toString()));
    }

    private Collection<? extends  GrantedAuthority> getAuthorities(String role){

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority(role));
        return  authorities;
    }
}
