package com.shipping.saas.shippingSaas.filter;

import com.shipping.saas.shippingSaas.jwt.JwtUtil;
import com.shipping.saas.shippingSaas.service.impl.ClientUserServiceImpl;
import com.shipping.saas.shippingSaas.service.impl.PlatformUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    private final PlatformUserService platformUserService;

    private final ClientUserServiceImpl clientUserService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // ✅ Skip filtering for login and auth endpoints
        String path = request.getServletPath();
        List<String> excludePaths = List.of("/login", "/auth/login", "/auth/password-reset");
        if (excludePaths.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        String username = jwtUtil.extractEmail(jwt);
        String userType = jwtUtil.extractUserType(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = switch (userType) {
                case "PLATFORM_USER"-> platformUserService.loadUserByUsername(username);
                case "CLIENT_USER" -> clientUserService.loadUserByUsername(username);
                default -> throw new IllegalStateException("Unknown user type: " + userType);
            };

            if (jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // ✅ Set request details
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // ✅ Set in security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }


}
