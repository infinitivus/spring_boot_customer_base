package com.infinitivus.project.spring_rest_security_base.config;

import com.infinitivus.project.spring_rest_security_base.service.security_service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private MyUserDetailsService myUserDetailsService;

        @Autowired
        private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
//                    .antMatchers("/userDatas/**").hasRole("ADMIN")
//                    .antMatchers("/person/**").hasAnyRole("ADMIN","MANAGER")
//                    .antMatchers("/work/**").hasAnyRole("ADMIN","MASTER")
//                    .antMatchers("/part/**").hasAnyRole("ADMIN","MASTER")
//                    .antMatchers("/").hasRole("USER")
                    .antMatchers("/**").permitAll()
                    .and().httpBasic().realmName("MY APP REALM")
                    .authenticationEntryPoint(authenticationEntryPoint);
        }


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
        }
    }


//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests().anyRequest().authenticated()
//                .and().httpBasic();
//
//    }
//
//    //    This section has been replaced by the configurations in application.properties file
////    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authentication)
//            throws Exception {
//        authentication.inMemoryAuthentication()
//                .withUser("admin")
//                .password(encoder().encode("admin"))
//                .authorities("ROLE_USER");
//    }
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }

