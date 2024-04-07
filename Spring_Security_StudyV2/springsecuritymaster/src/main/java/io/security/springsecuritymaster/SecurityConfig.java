package io.security.springsecuritymaster;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("customParam=y");

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logoutSuccess").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        SavedRequest savedRequest = requestCache.getRequest(request, response);
                        String redirectUrl = savedRequest.getRedirectUrl();
                        response.sendRedirect(redirectUrl);
                    }
                })).requestCache(cache -> cache.requestCache(requestCache));


            //TODO : logout
//                .logout(logout -> logout
//                        .logoutUrl("/logoutProc")
//                        .logoutRequestMatcher(new AntPathRequestMatcher("logoutProc", "GET"))
//                        .logoutSuccessUrl("/logoutSuccess")
//                        .logoutSuccessHandler(new LogoutSuccessHandler() {
//                            @Override
//                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                                response.sendRedirect("/logoutSuccess");
//                            }
//                        })
//                        .deleteCookies("JSESSIONID", "remember-me")
//                        .invalidateHttpSession(true)
//                        .addLogoutHandler(new LogoutHandler() {
//                            @Override
//                            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//                                HttpSession session = request.getSession();
//                                session.invalidate();
//                                SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(null);
//                                SecurityContextHolder.getContextHolderStrategy().clearContext();
//                            }
//                        }).permitAll()
//                );


        //TODO : remember-Me
//                .rememberMe(rememberMe -> rememberMe
//                        .alwaysRemember(true)
//                        .tokenValiditySeconds(3600)
//                        .userDetailsService(userDetailsService())
//                        .rememberMeParameter("remember")
//                        .rememberMeCookieName("remember")
//                        .key("security")
//                )
//        ;
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return  new InMemoryUserDetailsManager(user);
    }
}
