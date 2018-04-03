package br.com.todoapp.models.converters;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserAuth extends User {

    private br.com.todoapp.models.domains.User appUser;


    public UserAuth(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserAuth(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserAuth(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, br.com.todoapp.models.domains.User appUser) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.appUser = appUser;
    }


    public br.com.todoapp.models.domains.User getAppUser() {
        return appUser;
    }

    public void setAppUser(br.com.todoapp.models.domains.User appUser) {
        this.appUser = appUser;
    }
}
