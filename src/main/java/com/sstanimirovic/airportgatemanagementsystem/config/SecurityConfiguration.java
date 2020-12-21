package com.sstanimirovic.airportgatemanagementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${admin.username}")
    private String username;

    @Value("${admin.username}")
    private String password;

    public static String[] URL_PATHS = new String[]{"/gate/public", "/gate/public*", "/gate/public/*", "/login"};

    public static String[] SWAGGER_URL_PATHS = new String[]{"/swagger-ui.html**", "/swagger-resources/**",
            "/v2/api-docs**", "/webjars/**"};

    public static String[] SECURED_URL_PATHS = new String[]{"/admin", "/assign", "/update"};

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser(username)
                .password(encoder.encode(password))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(SWAGGER_URL_PATHS).hasRole("ADMIN")
            .antMatchers(SECURED_URL_PATHS).hasRole("ADMIN")
            .antMatchers(URL_PATHS).permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .httpBasic();
    }
}
