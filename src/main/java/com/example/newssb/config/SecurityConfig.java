package com.example.newssb.config;

import com.example.newssb.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/home","/news","/category","register","/login","/oauth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()

                .oauth2Login()
                .loginPage("/login")
                .successHandler(googleOAuth2SuccessHandler)
                .and()

                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error= true")
                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()

                .exceptionHandling().accessDeniedPage("/403")
                .and()

                .csrf()
                .disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/resources/**",
                "/static/**",
                "/bootstrap/**",
                "/css/**",
                "/js/**",
                "/Content/**",
                "/Style.css",
                "webjars/**",
                "/jquery/**",
                "/popper/**"
        );
    }
}
