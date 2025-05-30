package com.shipping.saas.shippingSaas.filter;

import com.shipping.saas.shippingSaas.jwt.JwtUtil;
import com.shipping.saas.shippingSaas.service.ClientUserService;
import com.shipping.saas.shippingSaas.service.PlatformUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    private final PlatformUserService platformUserService;

    private final ClientUserService clientUserService;
    ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        String userType = null;


        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            // No token — skip authentication and continue
            filterChain.doFilter(request, response);
            return;
        }


        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractEmail(jwt);
            userType = jwtUtil.extractUserType(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = switch (userType) {
                case "PlatformUser" -> platformUserService.loadUserByUsername(username);
                case "ClientUser" -> clientUserService.loadUserByUsername(username);
                default -> throw new IllegalStateException("Unknown user type: " + userType);
            };

            if (jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request, response);

    }
}
