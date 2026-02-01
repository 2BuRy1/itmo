package itmo.lab.web4.filters;

import org.springframework.beans.factory.annotation.Autowired;
import itmo.lab.web4.filters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class HttpFilter {

    @Autowired
    AuthenticationProvider authProvider;

    @Autowired
    JwtSecurityFilter jwtSecurityFilter;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors()
//                .and()
//                .httpBasic().disable()
//                .csrf().disable()
//                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
//                .authenticationProvider(authProvider);
//
//        return http.build();
//    }
}