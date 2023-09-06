package com.sparklemotion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationEntryPoint authenticationEntryPoint;

    private static final  String LOGOUT_URL = "/logout";
    private static final  String AUTH_URL = "/authenticate";

    private final UserDetailsService userDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() throws Exception{
        return new JwtAuthenticationEntryPoint();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        String API_DOCS_PATH = "/v3/api-docs/**";
        String SWAGGER_UI_PATH = "/swagger-ui/**";
        String SWAGGER_UI_HTML_PATH = "/swagger-ui.html";
        String ACTIONS_PATH = "/api/v1/actions";
        String USER_PATH = "/api/v1/user";
        String AUTHENTICATE_PATH = "/api/v1/authenticate";


        httpSecurity.cors().and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(
                        AUTHENTICATE_PATH,
                        USER_PATH,
                        ACTIONS_PATH,
                        SWAGGER_UI_HTML_PATH,
                        SWAGGER_UI_PATH,
                        API_DOCS_PATH)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .logout()
                .logoutSuccessUrl(AUTH_URL)
                .logoutUrl(LOGOUT_URL)
                .permitAll();

    }

}
