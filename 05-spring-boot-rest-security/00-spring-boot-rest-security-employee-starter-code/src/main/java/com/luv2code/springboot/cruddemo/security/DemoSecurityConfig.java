package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define custom query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_id, pw, active FROM members WHERE user_id=?"
        );

        // define custom query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT user_id, role FROM roles WHERE user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // user HTTP Basic Auth
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs
        // that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }


//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class DemoSecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails joao = User.builder()
//                .username("joao")
//                .password("{noop}teste123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails maria = User.builder()
//                .username("maria")
//                .password("{noop}teste123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails suzana = User.builder()
//                .username("suzana")
//                .password("{noop}teste123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(joao, maria, suzana);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
//        );
//
//        // user HTTP Basic Auth
//        http.httpBasic(Customizer.withDefaults());
//
//        // disable Cross Site Request Forgery (CSRF)
//        // in general, not required for stateless REST APIs
//        // that use POST, PUT, DELETE and/or PATCH
//        http.csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
//}
}
