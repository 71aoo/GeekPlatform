package com.syclover.geekPlatform.config;

import com.syclover.geekPlatform.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author cueyu
 * @Date 2020/9/11
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/test")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/addUser");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存验证
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
                withUser("admin").password(new BCryptPasswordEncoder().encode("testpwd")).roles("USER","ADMIN");
        // 数据库验证
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

