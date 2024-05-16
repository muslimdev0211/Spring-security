package com.muslimdev.dars.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.muslimdev.dars.security.ApplicationUsersRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{


        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) ->authz
                        .requestMatchers("/*", "/index", "/css", "/js/*")
                        .permitAll()
                        .requestMatchers("/api/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/management/**").hasAuthority(ApplicationUsersPermissions.COURSES_WRITE.getPermission())
                        .requestMatchers(HttpMethod.POST, "/api/management/**").hasAuthority(ApplicationUsersPermissions.COURSES_WRITE.getPermission())
                        .requestMatchers(HttpMethod.PUT, "/api/management/**").hasAuthority(ApplicationUsersPermissions.COURSES_WRITE.getPermission())
                        .requestMatchers(HttpMethod.GET, "/api/management/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
//                .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
//                .roles(ADMINTRAINEE.name())
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                annaSmithUser,
                lindaUser,
                tomUser
        );

    }
}
