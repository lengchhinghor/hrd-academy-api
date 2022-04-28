package org.ksga.springboot.springsecuritydemo.config;

import org.ksga.springboot.springsecuritydemo.security.handler.CustomAuthenticationEntryPoint;
import org.ksga.springboot.springsecuritydemo.security.handler.CustomAuthenticationSuccessHandler;
import org.ksga.springboot.springsecuritydemo.security.handler.CustomLogoutSuccessHandler;
import org.ksga.springboot.springsecuritydemo.security.jwt.AuthEntryPointJwt;
import org.ksga.springboot.springsecuritydemo.security.jwt.AuthTokenFilter;
import org.ksga.springboot.springsecuritydemo.security.service.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class MultiHttpSecurityConfig extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Order(1)
    @Configuration
    @EnableGlobalMethodSecurity(
            prePostEnabled = true
    )
    public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthEntryPointJwt unauthorizedHandler;

        @Bean
        public AuthTokenFilter authenticationJwtTokenFilter() {
            return new AuthTokenFilter();
        }

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            System.out.println(passwordEncoder().encode("admin"));
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .cors()
                    .configurationSource(corsConfigurationSource())
                    .and()
                    .csrf()
                    .disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/auth/**")
                    .permitAll()
                    .antMatchers("/auth/login").permitAll()
                    .antMatchers("/api/v3/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated();

            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
            web.ignoring().antMatchers("/h2-console/**","/swagger-ui/**", "/v3/api-docs/**","/v2/api-docs",
                    "/swagger-ui.html", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**",
                    "/resources/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**",
                    "/images/**", "/scss/**", "/vendor/**", "/favicon.ico", "/auth/**", "/favicon.png",
                    "/v2/api-docs", "/configuration/ui", "/configuration/security",
                    "/webjars/**", "/swagger-resources/**", "/actuator", "/swagger-ui/**",
                    "/actuator/**", "/swagger-ui/index.html", "/swagger-ui/");
        }



        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("*"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
            return source;
        }
    }

    @Order(2)
    @Configuration
    public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        @Autowired
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/auth/login").permitAll()
                    .antMatchers("/auth/signup").permitAll()
                    .antMatchers("/dashboard/**").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .loginPage("/auth/login")
                    .permitAll()
                    .failureUrl("/auth/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(customAuthenticationSuccessHandler)
                    .and()
                    .logout()
                    .permitAll()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                    .and()
                    .rememberMe()
                    .tokenValiditySeconds(2592000)
                    .key("thisIsMySecret!")
                    .rememberMeParameter("remember")
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/h2-console/**",
                            "/swagger-ui.html", "/h2-console", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**",
                            "/resources/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**",
                            "/images/**", "/scss/**", "/vendor/**", "/favicon.ico", "/auth/**", "/favicon.png",
                            "/v2/api-docs", "/configuration/ui", "/configuration/security",
                            "/webjars/**", "/swagger-resources/**", "/actuator", "/swagger-ui/**",
                            "/actuator/**", "/swagger-ui/index.html", "/swagger-ui/");
        }
    }

}
