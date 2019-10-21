package com.karlmarxindustries.herospotter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
                .antMatchers("/css/**","/js/**", "/", "/index", "/fragments/**", "/locations", "/powers", "/supers", "/sightings", "/organizations").access("permitAll()")
                .antMatchers(HttpMethod.GET, "/deleteOrganization").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/deleteSighting").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/deleteSuper").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/deleteLocation").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/deletePower").hasRole("ADMIN")
                .antMatchers( "/editLocation", "/editPowers", "/editOrganization", "/editSighting", "/editSuper").hasRole("ADMIN").and().
//                .antMatchers(HttpMethod.POST, "/**").permitAll().and().
               // .requestCache().requestCache(new NullRequestCache()).and().
                formLogin()
                .loginPage("/login").failureUrl("/login-error").and().cors().and().
                csrf().disable();
//

    }
//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers(HttpMethod.POST, "/**");
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//
}