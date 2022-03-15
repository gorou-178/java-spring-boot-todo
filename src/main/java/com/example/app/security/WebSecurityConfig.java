package com.example.app.security;

import com.example.app.service.TodoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TodoUserService todoUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/h2-console/**", "/user/**", "/login*", "/logout*").permitAll()
          .antMatchers("/todos/**").hasRole("USER")
        .and().formLogin()
          .loginPage("/login").permitAll()
          .loginProcessingUrl("/sign_in")
          .usernameParameter("username")
          .passwordParameter("password")
          .defaultSuccessUrl("/todos/")
        .and()
          .logout()
          .logoutUrl("/logout")
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID")
          .logoutSuccessHandler(logoutSuccessHandler())
          .permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(todoUserService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutHandler();
    }
}
