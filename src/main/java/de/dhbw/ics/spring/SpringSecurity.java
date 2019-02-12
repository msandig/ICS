package de.dhbw.ics.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.requestMatchers().requestMatchers(new AntPathRequestMatcher("/service/get/**", HttpMethod.GET.toString()),
                new AntPathRequestMatcher("/service/get/**", HttpMethod.PUT.toString()),
                new AntPathRequestMatcher("/service/get/**", HttpMethod.DELETE.toString())).and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/services/**");
    }
}
