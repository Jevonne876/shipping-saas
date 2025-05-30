package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.CustomUserDetails;
import com.shipping.saas.shippingSaas.domain.dto.AuthRequest;
import com.shipping.saas.shippingSaas.domain.dto.AuthResponse;
import com.shipping.saas.shippingSaas.jwt.JwtUtil;
import com.shipping.saas.shippingSaas.service.impl.ClientUserService;
import com.shipping.saas.shippingSaas.service.impl.PlatformUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;


    private final PlatformUserService platformUserService;

    private final ClientUserService clientUserService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        CustomUserDetails userDetails = null;

        try {
            userDetails = (CustomUserDetails) platformUserService.loadUserByUsername(authRequest.getUsername());
        } catch (UsernameNotFoundException ex) {
            try {
                userDetails = (CustomUserDetails) clientUserService.loadUserByUsername(authRequest.getUsername());
            } catch (UsernameNotFoundException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        }

        // Manually check password
        if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String jwt = jwtUtil.generateToken(
            userDetails.getUsername(),
            userDetails.getAuthorities().toString(),
            userDetails.getUserType()
        );

        return ResponseEntity.ok(new AuthResponse(jwt));
    }


}
