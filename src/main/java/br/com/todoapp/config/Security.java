package br.com.todoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan(basePackages = "br.com.todoapp")
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("AuthDetailsService")
    private UserDetailsService userSecurityService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
         .authorizeRequests()
         .antMatchers("/").permitAll()
         .antMatchers("/about").permitAll()
         .antMatchers("/users/access/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/users/access/signin")
                .permitAll()
                .defaultSuccessUrl("/users/dashboard")
                //@TODO CREATE TOASTER FOR THIS ERROR PARAMETER
                .failureUrl("/users/access?error=true")
           .and()
                .logout()
                .logoutUrl("/users/access/logout")
         .and().csrf();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
