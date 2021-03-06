package com.github.vladdow.server.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ATM1")
                .password("{noop}test")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                .and()
                .headers()
                    .frameOptions().disable()
                .and()
                .authorizeRequests()
                    .antMatchers("/server/**")
                    .authenticated()
                .and()
                .cors()
                    .disable();
    }

}
