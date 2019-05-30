package com.arefia.lamm.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arefia.lamm.login.service.iUserDetailService;
import com.arefia.lamm.utility.iPasswordEncoder;

@Configuration
@EnableWebSecurity
public class securityConfigure extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
	    return new iUserDetailService();
	};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new iPasswordEncoder();
        return encoder;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                   .authorizeRequests()
                   .antMatchers("/error", "/deny", "/callback", "/envcheck", "/unsupported", "/zohocallback", "/zohotest")
                   .permitAll()
                   .and()
                   .authorizeRequests()
                   .antMatchers("/*", "/")
                   .hasRole("USER")
                   .anyRequest().permitAll()
                   .and()
                   .formLogin()
                   .loginPage("/login")
                   .permitAll()
                   .defaultSuccessUrl("/", true)
                   .and()
                   .logout()
                   .logoutUrl("/logout") 
                   .invalidateHttpSession(true)
                   .clearAuthentication(true)
                   .logoutSuccessUrl("/login?logout")
                   .permitAll();;
    }
}