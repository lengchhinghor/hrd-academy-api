package org.ksga.springboot.springsecuritydemo.security.handler;

import org.ksga.springboot.springsecuritydemo.model.auth.ERole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        // If he has role, admin let him to go page dashboard
        response.setStatus(HttpServletResponse.SC_OK);
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (ERole.ROLE_ADMIN.name().equals(auth.getAuthority())) {
                response.sendRedirect("/dashboard");
                return;
            }
        }
        String attemptedURI = (String) request.getSession().getAttribute("ATTEMPTED_URI");
        if (StringUtils.hasText(attemptedURI)) {
            response.sendRedirect(attemptedURI);
            return;
        }
        response.sendRedirect("/");
    }
}