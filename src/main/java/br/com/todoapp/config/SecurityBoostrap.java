package br.com.todoapp.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Profile("DEV")
public class SecurityBoostrap  extends AbstractSecurityWebApplicationInitializer{

    public SecurityBoostrap() {
        super(Security.class);
    }
}
