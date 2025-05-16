package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.dto.AuthRequest;
import com.shipping.saas.shippingSaas.domain.dto.AuthResponse;
import com.shipping.saas.shippingSaas.jwt.JwtUtil;
import com.shipping.saas.shippingSaas.record.ErrorResponse;
import com.shipping.saas.shippingSaas.service.impl.PlatformUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;


    private final PlatformUserService platformUserService;


    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, PlatformUserService platformUserService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.platformUserService = platformUserService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponse(
                    HttpStatus.UNAUTHORIZED.value(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    "Invalid username or password",
                    request.getRequestURI(),
                    Instant.now()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    "Login failed: " + e.getMessage(),
                    request.getRequestURI(),
                    Instant.now()
                )
            );
        }

        final UserDetails userDetails = platformUserService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }


}
