package br.com.todoapp.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityBoostrap  extends AbstractSecurityWebApplicationInitializer{

    public SecurityBoostrap() {
        super(Security.class);
    }
}
